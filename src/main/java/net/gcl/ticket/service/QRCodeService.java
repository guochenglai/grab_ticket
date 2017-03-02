package net.gcl.ticket.service;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.io.ByteStreams;
import net.gcl.ticket.dama2.Dama2Client;
import net.gcl.ticket.factory.BeanFactory;
import net.gcl.ticket.http.TicketHttpClient;

import net.gcl.ticket.model.DecodeQRInfo;
import net.gcl.ticket.conf.URLEntity;
import net.gcl.ticket.model.HttpHeaderEntity;
import net.gcl.ticket.util.IOUtil;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by guochenglai on 1/8/17.
 */
public class QRCodeService {
    private Logger logger = LoggerFactory.getLogger(QRCodeService.class);
    private TicketHttpClient ticketHttpClient = BeanFactory.getSingletonBean(TicketHttpClient.class.getName());
    private Dama2Client dama2Client = BeanFactory.getSingletonBean(Dama2Client.class.getName());

    public byte[] queryLoginQRCode() {
        return queryQrCode(URLEntity.LOGIN_PASSCODE_URL, HttpHeaderEntity.loginPassCodeHeader());

    }

    public byte[] queryPassengerQRCode() {
        return queryQrCode(URLEntity.PASSENGER_PASSCODE_URL, HttpHeaderEntity.getPassengerQRCodeHeader());
    }

    public DecodeQRInfo queryLoginQrContent(){
        logger.info("query 12306  qr code ...");
        byte[] qrcodeContent = queryLoginQRCode();

        logger.info("decode 12306 qr code ....");
        DecodeQRInfo decodeQRInfo = dama2Client.decodeQRCode(qrcodeContent);
        return decodeQRInfo;
    }

    public boolean checkLoginQrCode(DecodeQRInfo decodeQRInfo) {
        //检查验证码
        logger.info("check qr code ... qr code content is : {}",decodeQRInfo.getQrContent());
        List<NameValuePair> checkQRCodeParams = new ArrayList<>();
        checkQRCodeParams.add(new BasicNameValuePair("randCode", decodeQRInfo.getQrContent()));

        checkQRCodeParams.add(new BasicNameValuePair("rand", "sjrand"));
        HttpResponse checkQRCodeHttpResponse = ticketHttpClient.doPost(URLEntity.CHECK_PASSCODE_URL, checkQRCodeParams, HttpHeaderEntity.checkLoginPassCodeHeader());

        String checkQRCodeHttpResponseContent = IOUtil.readInputStream(checkQRCodeHttpResponse);
        JSONObject checkQRCodeJsonObject = JSON.parseObject(checkQRCodeHttpResponseContent);
        logger.info("check qr code return content is : {}", checkQRCodeHttpResponseContent);
        String code = checkQRCodeJsonObject.getJSONObject("data").getString("result");
        if (!"1".equals(code)) {
            return false;
        }

        return true;
    }


    public byte[] queryQrCode(String qrcodeUrl, Map<String, String> qrCodeHeader) {
        HttpResponse httpResponse = ticketHttpClient.doGet(qrcodeUrl, qrCodeHeader);
        try (InputStream inputStream = httpResponse.getEntity().getContent()) {
            byte[] dataContent = ByteStreams.toByteArray(inputStream);
            logger.info("query 12306  qr code success ...");
            return dataContent;
        } catch (Exception e) {
            logger.error("download qrcode cause exception", e);
        }
        return null;
    }
}
