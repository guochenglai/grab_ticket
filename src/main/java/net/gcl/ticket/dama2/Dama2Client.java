package net.gcl.ticket.dama2;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import net.gcl.ticket.factory.BeanFactory;
import net.gcl.ticket.http.Dama2HttpClient;
import net.gcl.ticket.model.DecodeQRInfo;
import net.gcl.ticket.conf.URLEntity;
import net.gcl.ticket.util.ConfUtil;
import net.gcl.ticket.util.IOUtil;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * Created by chenglai.guo on 2017/1/7.
 * 打码兔平台自动打码
 * 打码兔函数wiki：http://wiki.dama2.com/index.php?n=ApiDoc.Http
 */
public class Dama2Client {
    private  Logger logger = LoggerFactory.getLogger(Dama2Client.class);
    private Dama2HttpClient dama2HttpClient = BeanFactory.getSingletonBean(Dama2HttpClient.class.getName());
    private String loginAuthKey = "";

    public Dama2Client() {
        init();
    }

    /**
     * 通过图片数据请求打码
     * type：验证码类型ID
     * timeout：超时秒数
     * 成功时返回对象的ret > 0，表示验证码ID，用于调用getResult、getResultUtilDone等函数
     * @param data：图片数据
     * @return
     */
    public DecodeQRInfo decodeQRCode(byte [] data) {
        Map<String, String> params = new LinkedHashMap<>();
        params.put("type", ConfUtil.getString("dama2.vcode.type.id"));
        params.put("auth", loginAuthKey);
        params.put("timeout", "30");
        String decodeIdContent = dama2HttpClient.doPostPic(URLEntity.DAMA2_QUERY_QR_ID_URL, params, data);
        JSONObject decodeIdJsonObject = JSONObject.parseObject(decodeIdContent);
        int ret = decodeIdJsonObject.getInteger("ret");
        if (ret != 0) {
            throw new RuntimeException("decode error :"+decodeIdJsonObject.getString("desc"));
        }
        Long qrcodeId = decodeIdJsonObject.getLong("id");
        String qrContent = doGetQRContentById(qrcodeId);
        qrContent = qrContent.replace("|", ",");

        DecodeQRInfo decodeQRInfo = new DecodeQRInfo(qrcodeId, qrContent);

        logger.info("decode qr code success content is : {}", JSON.toJSONString(decodeQRInfo));

        return decodeQRInfo;
    }

    public String doGetQRContentById(Long qrCodeId) {
        String qrcodeIdResultUrl = URLEntity.DAMA2_QUERY_QR_ID_CONTENT_URL + "?id=" + qrCodeId;
        List<NameValuePair> queryQrContentByIdParams = new ArrayList<>();
        queryQrContentByIdParams.add(new BasicNameValuePair("type", ConfUtil.getString("dama2.vcode.type.id")));
        queryQrContentByIdParams.add(new BasicNameValuePair("auth", loginAuthKey));
        queryQrContentByIdParams.add(new BasicNameValuePair("timeout", "30"));
        HttpResponse httpQRCodeContentResponse = dama2HttpClient.doPost(qrcodeIdResultUrl, queryQrContentByIdParams);
        String httpQRCodeContent = IOUtil.readInputStream(httpQRCodeContentResponse);
        JSONObject httpQRCodeJOSNObject = JSONObject.parseObject(httpQRCodeContent);
        int retCode = httpQRCodeJOSNObject.getShort("ret");
        if (retCode != 0) {
            throw new RuntimeException("" + httpQRCodeJOSNObject.getString("desc"));
        }
        String qrContent = httpQRCodeJOSNObject.getString("result");

        return qrContent;
    }

    public long queryBalance() {
        List<NameValuePair> queryQrContentByIdParams = new ArrayList<>();
        queryQrContentByIdParams.add(new BasicNameValuePair("type", ConfUtil.getString("dama2.vcode.type.id")));
        queryQrContentByIdParams.add(new BasicNameValuePair("auth", loginAuthKey));
        queryQrContentByIdParams.add(new BasicNameValuePair("timeout", "30"));
        HttpResponse balanceHttpResonse = dama2HttpClient.doPost(URLEntity.DAMA2_QUERY_BANANCE_URL, queryQrContentByIdParams);
        String balanceResult = IOUtil.readInputStream(balanceHttpResonse);
        JSONObject balanceJSONObject = JSON.parseObject(balanceResult);
        if (balanceJSONObject.getInteger("ret") != 0) {
            throw new RuntimeException("query balance cause exception " + balanceJSONObject.getString("desc"));
        }
        return balanceJSONObject.getLong("balance");
    }

    private  synchronized void init() {
        logger.info("init dama2 client ...");
        //拿到打码兔给的一次软件请求的唯一标识
        String preAuthContent = dama2HttpClient.doGetContent(URLEntity.DAMA2_PREAUTH_URL);
        if (StringUtils.isEmpty(preAuthContent)) {
            logger.error("query dama2 preAuth key error result is empty");
            throw new RuntimeException("query dama2 preAuth key error result is empty");
        }
        JSONObject preAuthJSONObject = JSONObject.parseObject(preAuthContent);
        int code = preAuthJSONObject.getInteger("ret");
        if (code != 0) {
            logger.error("query dama2 preAuth key error result message is : {}", preAuthJSONObject.getString("desc"));
            throw new RuntimeException("query dama2 preAuth key error result message is : " + preAuthJSONObject.getString("desc"));
        }
        String preAuthKey = preAuthJSONObject.getString("auth");

        //登陆打码兔软件
        String encryptContent = EncryptUtil.encryptDamaLoginParam(preAuthKey, ConfUtil.getString("dama2.software.key"), ConfUtil.getString("dama2.username"), ConfUtil.getString("dama2.password"));
        String dama2LoginUrl = URLEntity.DAMA2_LOGIN_URL + "?appID=" + ConfUtil.getInteger("dama2.software.id") + "&encinfo=" + encryptContent;
        String loginContent = dama2HttpClient.doGetContent(dama2LoginUrl);
        JSONObject loginJSONObject = JSON.parseObject(loginContent);
        int loginCode = loginJSONObject.getInteger("ret");
        if (loginCode != 0) {
            logger.error("login dama2 error login message is : {}", loginJSONObject.getString("desc"));
            throw new RuntimeException("login dama2 error login message is : " + loginJSONObject.getString("desc"));
        }
        String loginAuthKey = loginJSONObject.getString("auth");
        this.loginAuthKey = loginAuthKey;
    }

}
