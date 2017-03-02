package net.gcl.ticket.service;

import java.net.URLDecoder;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.gcl.ticket.model.*;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;

import net.gcl.ticket.conf.URLEntity;
import net.gcl.ticket.dama2.Dama2Client;
import net.gcl.ticket.factory.BeanFactory;
import net.gcl.ticket.http.TicketHttpClient;
import net.gcl.ticket.model.enums.SeatType;
import net.gcl.ticket.util.DateUtil;
import net.gcl.ticket.util.IOUtil;
import net.gcl.ticket.util.PassengerInfoWrapper;

/**
 * Created by guochenglai on 1/30/17.
 */
public class OrderSubmitService {
    private Logger logger = LoggerFactory.getLogger(OrderSubmitService.class);
    private TicketHttpClient ticketHttpClient = BeanFactory.getSingletonBean(TicketHttpClient.class.getName());
    private QRCodeService qrCodeService = BeanFactory.getSingletonBean(QRCodeService.class.getName());
    private Dama2Client dama2Client = BeanFactory.getSingletonBean(Dama2Client.class.getName());

    public SubmitOrderInfo autoSubmitOrder(List<TrainInfo> trainInfoList, List<Passenger> passengerList, Date trainDate, String tourFlag, String purposeCode, String fromStationName, String toStationName, List<SeatType> seatTypeList) {
        for (TrainInfo trainInfo : trainInfoList) {
            for (SeatType seatType : seatTypeList) {
                SubmitOrderInfo submitOrderInfo = autoSubmitOrder(trainInfo, passengerList, trainDate, tourFlag, purposeCode, fromStationName, toStationName, seatType);
                if (submitOrderInfo.isStatus()) {
                    return submitOrderInfo;
                }
            }
        }

        return null;
    }

    public SubmitOrderInfo autoSubmitOrder(TrainInfo trainInfo, List<Passenger> passengerList,Date trainDate,String tourFlag,String purposeCode,String fromStationName,String toStationName,SeatType seatType) {

        //submit order request
        SubmitOrderInfo submitOrderInfo = submitOrderRequest(trainInfo, trainDate, tourFlag, purposeCode, fromStationName, toStationName);
        if (!submitOrderInfo.isStatus()) {
            logger.error("submit order request cause error");
            return submitOrderInfo;
        }

        //init dc and dynamic js
        logger.info("init submit order request param ...");
        Map<String, String> globalSubmitOrderKeyMap = initDC();

        logger.info("assemble old passenger ticket str ...");
        String passengerTicketStr = PassengerInfoWrapper.generatePassengerTicketStr(seatType,passengerList);
        logger.info("assemble old passenger ticket str ...");
        String oldPassengerStr = PassengerInfoWrapper.generateOldPassengerStr(passengerList);


        //check order info
        CheckOrderInfoEntity checkOrderInfoEntity = checkOrderInfo(passengerTicketStr, oldPassengerStr, globalSubmitOrderKeyMap.get("REPEAT_SUBMIT_TOKEN"));
        if (!checkOrderInfoEntity.isSubmitStatus()) {
            logger.info("can not submit order because check order info incorrect ...");
            return new SubmitOrderInfo(checkOrderInfoEntity.isSubmitStatus(), checkOrderInfoEntity.getMessage());
        }

        //todo 这里确认在下单的时候，什么时候需要验证码
        String qrCode = "";
//        if (checkOrderInfoEntity.isIfShowPassCode()) {//表示下单的时候需要验证码
//            //get new qr code
//            byte[] qrContent = qrCodeService.queryPassengerQRCode();
//            qrCode = dama2Client.decodeQRCode(qrContent).getQrContent();
//        }

        //get queue count
        boolean getCountCount = getQueueCountResult(trainDate, trainInfo, seatType, globalSubmitOrderKeyMap);
        if (!getCountCount) {
            logger.error("get queue count error !!!");
            return null;
        }

        //confirm for train
        boolean submitTicketStatus = confirmForTrain(passengerTicketStr, oldPassengerStr, qrCode,globalSubmitOrderKeyMap, trainInfo);
        if (submitTicketStatus) {
            logger.info("submit order success ...");
        } else {
            logger.error("submit order failure ... ");
        }

        return null;
    }


    public boolean confirmForTrain(String passengerTicketStr, String oldPassengerStr, String qrCode, Map<String, String> globalSubmitOrderKeyMap, TrainInfo trainInfo) {
        List<NameValuePair> submitTicketRequestParam = new ArrayList<>();
        submitTicketRequestParam.add(new BasicNameValuePair("passengerTicketStr", passengerTicketStr));
        submitTicketRequestParam.add(new BasicNameValuePair("oldPassengerStr", oldPassengerStr));
        submitTicketRequestParam.add(new BasicNameValuePair("randCode", qrCode));
        submitTicketRequestParam.add(new BasicNameValuePair("purpose_codes", "00"));
        submitTicketRequestParam.add(new BasicNameValuePair("leftTicketStr", globalSubmitOrderKeyMap.get("leftTicketStr")));
        submitTicketRequestParam.add(new BasicNameValuePair("REPEAT_SUBMIT_TOKEN", globalSubmitOrderKeyMap.get("REPEAT_SUBMIT_TOKEN")));
        submitTicketRequestParam.add(new BasicNameValuePair("key_check_isChange", globalSubmitOrderKeyMap.get("key_check_isChange")));
        submitTicketRequestParam.add(new BasicNameValuePair("train_location", trainInfo.getTrainLocationCode()));
        submitTicketRequestParam.add(new BasicNameValuePair("choose_seats", ""));
        submitTicketRequestParam.add(new BasicNameValuePair("seatDetailType", "00"));
        submitTicketRequestParam.add(new BasicNameValuePair("dwAll", "N"));
        submitTicketRequestParam.add(new BasicNameValuePair("_json_att", ""));

        logger.info("submit ticket order ");
        HttpResponse submitTicketHttpResponse = ticketHttpClient.doPost(URLEntity.CONFIRM_SINGLE_URL, submitTicketRequestParam, HttpHeaderEntity.submitOrderHeader());
        String submitTicketHttpResponseContent = IOUtil.readInputStream(submitTicketHttpResponse);
        logger.info("submit ticket order response content is : {}", submitTicketHttpResponseContent);
        JSONObject submitTicketJsonObject = JSON.parseObject(submitTicketHttpResponseContent);
        return submitTicketJsonObject.getBoolean("status");
    }

    public boolean getQueueCountResult(Date trainDate,TrainInfo trainInfo,SeatType seatType,Map<String, String> globalSubmitOrderKeyMap ) {
        List<NameValuePair> getQueueCountParamList = new ArrayList<>();
        getQueueCountParamList.add(new BasicNameValuePair("train_date", trainDate + ""));
        getQueueCountParamList.add(new BasicNameValuePair("train_no", trainInfo.getTrainNum()));
        getQueueCountParamList.add(new BasicNameValuePair("stationTrainCode", trainInfo.getTrainCode()));
        getQueueCountParamList.add(new BasicNameValuePair("seatType", seatType.getValue()));
        getQueueCountParamList.add(new BasicNameValuePair("fromStationTelecode", trainInfo.getFromStationCode()));
        getQueueCountParamList.add(new BasicNameValuePair("toStationTelecode", trainInfo.getToStationCode()));
        getQueueCountParamList.add(new BasicNameValuePair("leftTicket", globalSubmitOrderKeyMap.get("leftTicketStr")));
        getQueueCountParamList.add(new BasicNameValuePair("purpose_codes", "00"));
        getQueueCountParamList.add(new BasicNameValuePair("train_location", trainInfo.getTrainLocationCode()));
        getQueueCountParamList.add(new BasicNameValuePair("_json_att",""));
        getQueueCountParamList.add(new BasicNameValuePair("REPEAT_SUBMIT_TOKEN", globalSubmitOrderKeyMap.get("REPEAT_SUBMIT_TOKEN")));
        HttpResponse getQueueCountHttpResonse = ticketHttpClient.doPost(URLEntity.GET_QUEUE_COUNT_URL, getQueueCountParamList, HttpHeaderEntity.getQueueCountHeader());
        String getQueueCountCount = IOUtil.readInputStream(getQueueCountHttpResonse);
        logger.info("get queue count response content is : {}", getQueueCountCount);
        JSONObject getQueueCountJsonObject = JSON.parseObject(getQueueCountCount);
        return  getQueueCountJsonObject.getBoolean("status");
    }

    public SubmitOrderInfo submitOrderRequest(TrainInfo trainInfo,Date trainDate,String tourFlag,String purposeCode,String fromStationName,String toStationName) {
        List<NameValuePair> submitOrderParamList = new ArrayList<>();
        String secretStr = "";
        try{
            secretStr = URLDecoder.decode(trainInfo.getSecretStr(), "UTF-8");
        }catch (Exception e){
            logger.error("decode secret str cause exception ",e);
        }
        submitOrderParamList.add(new BasicNameValuePair("secretStr", secretStr));
        submitOrderParamList.add(new BasicNameValuePair("train_date", DateUtil.formatDateToString(trainDate)));
        submitOrderParamList.add(new BasicNameValuePair("back_train_date", DateUtil.formatDateToString(new Date())));
        submitOrderParamList.add(new BasicNameValuePair("tour_flag", tourFlag));
        submitOrderParamList.add(new BasicNameValuePair("purpose_codes",purposeCode));
        submitOrderParamList.add(new BasicNameValuePair("query_from_station_name",fromStationName));
        submitOrderParamList.add(new BasicNameValuePair("query_to_station_name",toStationName));
        submitOrderParamList.add(new BasicNameValuePair("undefined",""));

        HttpResponse httpResponse = ticketHttpClient.doPost(URLEntity.SUBMIT_ORDER_REQUEST_URL, submitOrderParamList, HttpHeaderEntity.submitOrderRequestHttpHeader());
        String content = IOUtil.readInputStream(httpResponse);
        logger.info("submit order request response content is : {}",content);
        JSONObject submitOrderRequestJSONObject = JSON.parseObject(content);
        boolean flag = submitOrderRequestJSONObject.getBoolean("status");
        String message = submitOrderRequestJSONObject.getString("messages");
        SubmitOrderInfo submitOrderInfo = new SubmitOrderInfo(flag, message);
        return submitOrderInfo;

    }

    public CheckOrderInfoEntity checkOrderInfo(String passengerTicketStr, String oldPassengerStr, String repeatSubmitToken) {
        List<NameValuePair> checkOrderInfoParamList = new ArrayList<>();
        checkOrderInfoParamList.add(new BasicNameValuePair("cancel_flag","2"));
        checkOrderInfoParamList.add(new BasicNameValuePair("bed_level_order_num","000000000000000000000000000000"));
        checkOrderInfoParamList.add(new BasicNameValuePair("passengerTicketStr",passengerTicketStr));
        checkOrderInfoParamList.add(new BasicNameValuePair("oldPassengerStr",oldPassengerStr));
        checkOrderInfoParamList.add(new BasicNameValuePair("tour_flag","dc"));
        checkOrderInfoParamList.add(new BasicNameValuePair("randCode",""));
        checkOrderInfoParamList.add(new BasicNameValuePair("_json_att",""));
        checkOrderInfoParamList.add(new BasicNameValuePair("REPEAT_SUBMIT_TOKEN", repeatSubmitToken));

        HttpResponse checkOrderInfoHttpResponse = ticketHttpClient.doPost(URLEntity.CHECK_ORDER_INFO_URL, checkOrderInfoParamList, HttpHeaderEntity.checkOrderInfoHeader());
        String checkOrderInfoContent = IOUtil.readInputStream(checkOrderInfoHttpResponse);
        logger.info("check order info response content is : {}", checkOrderInfoContent);
        JSONObject checkOrderInfoJSONObject = JSON.parseObject(checkOrderInfoContent);

        String ifShowPassCode = checkOrderInfoJSONObject.getString("ifShowPassCode");

        boolean submitStatus = checkOrderInfoJSONObject.getJSONObject("data").getBoolean("submitStatus");
        String errMsg = checkOrderInfoJSONObject.getJSONObject("data").getString("errMsg");
        CheckOrderInfoEntity checkOrderInfoEntity = new CheckOrderInfoEntity();
        checkOrderInfoEntity.setSubmitStatus(submitStatus);
        checkOrderInfoEntity.setMessage(errMsg);

        if ("N".equals(ifShowPassCode)) {
            checkOrderInfoEntity.setIfShowPassCode(false);
        } else {
            checkOrderInfoEntity.setIfShowPassCode(true);
        }

        return checkOrderInfoEntity;
    }

    public Map<String,String> initDC() {
        List<NameValuePair> paramList = Lists.newArrayList(new BasicNameValuePair("_json_att", ""));
        Map<String, String> globalSubmitOrderKeyMap = new HashMap<>();
        HttpResponse initDCHttpResponse = ticketHttpClient.doPost(URLEntity.INIT_DC_URL, paramList, HttpHeaderEntity.confirmPassengerInitDCHeader());
        String initDCResponseContent = IOUtil.readInputStream(initDCHttpResponse);

        Pattern repeatSubmitTokenPattern= Pattern.compile("var globalRepeatSubmitToken = '(\\w+)'");
        Matcher repeatSubmitTokenMatcher = repeatSubmitTokenPattern.matcher(initDCResponseContent);
        if (repeatSubmitTokenMatcher.find()) {
            globalSubmitOrderKeyMap.put("REPEAT_SUBMIT_TOKEN", repeatSubmitTokenMatcher.group(1));
        }

        Pattern keyCheckIsChangePattern = Pattern.compile("'key_check_isChange':'(\\w+)'");
        Matcher keyCheckIsChangeMatcher = keyCheckIsChangePattern.matcher(initDCResponseContent);
        if (keyCheckIsChangeMatcher.find()) {
            globalSubmitOrderKeyMap.put("key_check_isChange", keyCheckIsChangeMatcher.group(1));
        }


        Pattern leftTicketStrPattern = Pattern.compile("'leftTicketStr':'(\\w+(%*\\w*)*)'");
        Matcher leftTicketStrMatcher = leftTicketStrPattern.matcher(initDCResponseContent);
        if (leftTicketStrMatcher.find()) {
            globalSubmitOrderKeyMap.put("leftTicketStr", leftTicketStrMatcher.group(1));
        }

        logger.info("pre order init dc param is : {}", JSON.toJSONString(globalSubmitOrderKeyMap));

        return globalSubmitOrderKeyMap;
    }

}
