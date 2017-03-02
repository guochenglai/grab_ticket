package net.gcl.ticket.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.gcl.ticket.factory.BeanFactory;
import net.gcl.ticket.http.TicketHttpClient;
import net.gcl.ticket.model.HttpHeaderEntity;
import net.gcl.ticket.model.TrainInfo;
import net.gcl.ticket.conf.URLEntity;
import net.gcl.ticket.util.DateUtil;
import net.gcl.ticket.util.IOUtil;
import net.gcl.ticket.util.TrainInfoWrapperUtil;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.Consts;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;

/**
 * Created by guochenglai on 1/26/17.
 */
public class TrainQueryService {
    private Logger logger = LoggerFactory.getLogger(TrainQueryService.class);
    private TicketHttpClient ticketHttpClient = BeanFactory.getSingletonBean(TicketHttpClient.class.getName());
    private QRCodeService qrCodeService = BeanFactory.getSingletonBean(QRCodeService.class.getName());

    public List<TrainInfo> queryAllTrain(Date trainDate, String fromStation, String toStation, String purposeCode) {
        List<NameValuePair> queryLeftTicketParamList = new ArrayList<>();
        queryLeftTicketParamList.add(new BasicNameValuePair("leftTicketDTO.train_date", DateUtil.formatDateToString(trainDate)));
        queryLeftTicketParamList.add(new BasicNameValuePair("leftTicketDTO.from_station", fromStation));
        queryLeftTicketParamList.add(new BasicNameValuePair("leftTicketDTO.to_station", toStation));
        queryLeftTicketParamList.add(new BasicNameValuePair("purpose_codes", purposeCode));

        //init left ticket ...
        leftTicketInit();

        //query pass code new
        qrCodeService.queryPassengerQRCode();

        //query train log ...
        queryTrainLog(queryLeftTicketParamList);


        //query left ticket train ...
        String paramsUrl = URLEncodedUtils.format(queryLeftTicketParamList, Consts.UTF_8);
        String requestUrl = URLEntity.TICKET_QUERY_URL + "?" + paramsUrl;
        HttpResponse httpResponse = ticketHttpClient.doGet(requestUrl, HttpHeaderEntity.ticketQueryHeader());

        String trainInfo = IOUtil.readInputStream(httpResponse);
        JSONObject trainInfoJsonObject = JSON.parseObject(trainInfo);

        List<TrainInfo> allTrainList = TrainInfoWrapperUtil.wrapperTrainList(trainInfoJsonObject);


        List<TrainInfo> effectiveBuyTrainList = new ArrayList<>();
        for (TrainInfo train : allTrainList) {
            if ("0".equals(train.getTrainFlag())) {
                effectiveBuyTrainList.add(train);
            }
        }

        return effectiveBuyTrainList;
    }

    public void queryTrainLog(List<NameValuePair> paramList) {
        String paramsUrl = URLEncodedUtils.format(paramList, Consts.UTF_8);
        String requestUrl = URLEntity.TICKET_QUERY_LOG_URL + "?" + paramsUrl;
        HttpResponse ticketLogQueryHttpResponse = ticketHttpClient.doGet(requestUrl, HttpHeaderEntity.ticketLogQueryHeader());
        IOUtil.readInputStream(ticketLogQueryHttpResponse);
    }

    public List<TrainInfo> queryCanBuyTrainList(Date trainDate, String fromStation, String toStation, String purposeCode) {
        List<TrainInfo> allTrainList = queryAllTrain(trainDate,fromStation,toStation,purposeCode);
        if (CollectionUtils.isEmpty(allTrainList)) {
            return Lists.newArrayList();
        }
        List<TrainInfo> canBuyTrainList = new ArrayList<>();
        for (TrainInfo trainInfo : allTrainList) {
            if ("Y".equals(trainInfo.getCanWebBuy())) {
                canBuyTrainList.add(trainInfo);
            }
        }
        return canBuyTrainList;
    }

    public List<TrainInfo> queryHardSleeperCanByTrainList(Date trainDate, String fromStation, String toStation, String purposeCode) {
        logger.info("query hard sleeper can by train list ...");

        List<TrainInfo> canBuyTrainList = queryCanBuyTrainList(trainDate,fromStation,toStation,purposeCode);

        List<TrainInfo> hardSleeperTrainList = new ArrayList<>();
        if (CollectionUtils.isEmpty(canBuyTrainList)) {
            logger.error("there is no can by train list ..");
            return hardSleeperTrainList;
        }

        for (TrainInfo trainInfo : canBuyTrainList) {
            String hardSleeperSeatNum = trainInfo.getHardSleeperSeatNum();
            if ("有".equals(hardSleeperSeatNum)){
                hardSleeperTrainList.add(trainInfo);
            }else if (StringUtils.isNotEmpty(hardSleeperSeatNum) && hardSleeperSeatNum.matches("\\d+") && Integer.parseInt(hardSleeperSeatNum) > 0) {
                hardSleeperTrainList.add(trainInfo);
            }
        }

        if (CollectionUtils.isNotEmpty(hardSleeperTrainList)) {
            logger.info("query can buy train list success size is : {}", hardSleeperTrainList.size());
        } else {
            logger.error("there is no hard sleeper can by train list ");
        }
        return hardSleeperTrainList;
    }


    private void leftTicketInit() {
        // left ticket init
        HttpResponse ticketInitHttpResponse = ticketHttpClient.doGet(URLEntity.LEFT_TICKET_INIT_URL, HttpHeaderEntity.ticketQueryInitHeader());
        String ticketInitContent = IOUtil.readInputStream(ticketInitHttpResponse);
        Pattern ticketInitDynamicJsPattern = Pattern.compile("/otn/dynamicJs/(\\w+)");
        Matcher ticketInitDynamicJsMacher = ticketInitDynamicJsPattern.matcher(ticketInitContent);
        String ticketInitDynamicJsFileName = "";
        if (ticketInitDynamicJsMacher.find()) {
            ticketInitDynamicJsFileName = ticketInitDynamicJsMacher.group(1);
        }

        //left ticket dynamic js
        String ticketDynamicJsFile = URLEntity.DYNAMIC_JS_URL + ticketInitDynamicJsFileName;
        ticketHttpClient.doGet(ticketDynamicJsFile, HttpHeaderEntity.leftTicketDynamicJsHeader());

    }
}
