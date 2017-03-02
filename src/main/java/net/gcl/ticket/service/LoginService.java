package net.gcl.ticket.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.common.collect.Lists;
import net.gcl.ticket.factory.BeanFactory;
import net.gcl.ticket.http.TicketHttpClient;
import net.gcl.ticket.http.factory.HttpClientFactory;
import net.gcl.ticket.model.DecodeQRInfo;
import net.gcl.ticket.conf.URLEntity;
import net.gcl.ticket.model.HttpHeaderEntity;
import net.gcl.ticket.util.IOUtil;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Splitter;
import com.google.common.collect.Maps;

/**
 * Created by guochenglai on 1/5/17.
 */
public class LoginService {
    private Logger logger = LoggerFactory.getLogger(LoginService.class);
    private TicketHttpClient ticketHttpClient = BeanFactory.getSingletonBean(TicketHttpClient.class.getName());
    private QRCodeService qrCodeService = BeanFactory.getSingletonBean(QRCodeService.class.getName());
    private static final int QR_CODE_RETRY_TIME = 1;
    private UserCookieService userCookieService = BeanFactory.getSingletonBean(UserCookieService.class.getName());
    private HttpClientFactory httpClientFactory = BeanFactory.getSingletonBean(HttpClientFactory.class.getName());

    public String autoLogin(String userName,String password) {
        //这里先查询本地数据库里面的cookie信息。如果登陆没有失效就不变进行二次登陆
        Map<String, String> cookieMap = userCookieService.queryAllCookie();
        if (MapUtils.isNotEmpty(cookieMap)) {
            BasicCookieStore cookieStore = httpClientFactory.getCookieStore();
            for (Map.Entry<String, String> cookieEntry : cookieMap.entrySet()) {
                BasicClientCookie basicClientCookie = new BasicClientCookie(cookieEntry.getKey(), cookieEntry.getValue());
                basicClientCookie.setDomain("kyfw.12306.cn");
                basicClientCookie.setPath("/");
                cookieStore.addCookie(basicClientCookie);

            }
            boolean userLogin = checkUserLogin();
            if (userLogin) { //如果检查到用户登录有效就直接不用登陆
                return null;
            }
        }

        //login init
        loginInit();

        // decode qr code
        DecodeQRInfo decodeQRInfo = null;
        int i = 0;
        boolean checkQrResult = false;
        while (i < QR_CODE_RETRY_TIME) {
            decodeQRInfo = qrCodeService.queryLoginQrContent();

             checkQrResult = qrCodeService.checkLoginQrCode(decodeQRInfo);
            if (checkQrResult) {
                break;
            } else {
                logger.error("check login qr code error retry time :{}" , i);
                decodeQRInfo = qrCodeService.queryLoginQrContent();
            }
            i++;
        }
        if (!checkQrResult) {
            throw new RuntimeException("登陆验证码校验未通过，请检查是否在其他地方进行了登陆");
        }

        //login
        return login(userName, password, decodeQRInfo);
    }



    //返回null表示登陆成功，否则表示登陆失败的提示信息
    public String login(String userName,String passWord,DecodeQRInfo decodeQRInfo){

        //登陆
        List<NameValuePair> loginParams = new ArrayList<>();
        loginParams.add(new BasicNameValuePair("loginUserDTO.user_name", userName));
        loginParams.add(new BasicNameValuePair("userDTO.password", passWord));
        loginParams.add(new BasicNameValuePair("randCode", decodeQRInfo.getQrContent()));

        logger.info(" login 12306 login param is : {}", JSON.toJSONString(loginParams));
        HttpResponse loginHttpResponse = ticketHttpClient.doPost(URLEntity.LOGIN_AYSN_SUGGEST_URL, loginParams, HttpHeaderEntity.loginHeader());

        Header[] headers = loginHttpResponse.getAllHeaders();

        Map<String, String> cookieMap = Maps.newHashMap();
        for (int i = 0; i < headers.length; i++) {
            if (headers[i].getName().equals("Set-Cookie")) {
                Map<String, String> map = Splitter.on(";").withKeyValueSeparator("=").split(headers[i].getValue());
                cookieMap.putAll(map);
            }
        }

        String loginHttpResponseContent = IOUtil.readInputStream(loginHttpResponse);
        logger.info("login result message is : {}", loginHttpResponse);

//        if (StringUtils.isEmpty(loginHttpResponseContent)) {//表示登陆成功
            logger.info("login success ");

            //user login
            List<NameValuePair> userLoginParam = Lists.newArrayList(new BasicNameValuePair("_json_att", ""));
            ticketHttpClient.doPost(URLEntity.CHECK_USER_LOGIN_URL, userLoginParam, HttpHeaderEntity.userLoginHeader());

            //init my 12306
            ticketHttpClient.doGet(URLEntity.INIT_MY_12306_URL, HttpHeaderEntity.initMy12306Header());

            //save cookie
            userCookieService.presisCookie();
            return null;
//        } else{
//            return "登陆失败请检查用户名或者密码是否正确";
//        }
    }

    public boolean checkUserLogin() {
        List<NameValuePair> checkUserParamList = Lists.newArrayList(new BasicNameValuePair("_json_att", ""));
        HttpResponse checkUserLoginHttpResonse = ticketHttpClient.doPost(URLEntity.CHECK_USER_LOGIN_URL, checkUserParamList, HttpHeaderEntity.checkUserLoginHttpHeader());
        String checkUserContent = IOUtil.readInputStream(checkUserLoginHttpResonse);
        logger.info("check user login response content is : {}", checkUserContent);
        JSONObject checkUserJsonObject = JSON.parseObject(checkUserContent);
        boolean loginStatus = checkUserJsonObject.getJSONObject("data").getBoolean("flag");
        return loginStatus;
    }


    private void loginInit() {
        try {
            logger.info("init login info  ...");
            HttpResponse httpResponse = ticketHttpClient.doGet(URLEntity.LOGIN_INIT_URL, HttpHeaderEntity.loginInitHeader());
            Header[] headers = httpResponse.getAllHeaders();

            Map<String, String> cookieMap = Maps.newHashMap();
            for (int i = 0; i < headers.length; i++) {
                if (headers[i].getName().equals("Set-Cookie")) {
                    Map<String, String> map = Splitter.on(";").withKeyValueSeparator("=").split(headers[i].getValue());
                    cookieMap.putAll(map);
                }
            }
            //获取dynamicJs
            String loginDynamicJs = "";
            String httpResponseContent = IOUtil.readInputStream(httpResponse);
            Pattern dynamicJsPattern = Pattern.compile("/otn/dynamicJs/(\\w+)");
            Matcher m_token = dynamicJsPattern.matcher(httpResponseContent);
            if (m_token.find()) {
                loginDynamicJs = m_token.group(1);
            } else {
                logger.error("httpClient init get loginDynamicJsUrl  fail for unknown reason, check it!");
            }

            //获取动态参数
            String dynamicJSRequestUrl = URLEntity.DYNAMIC_JS_URL + loginDynamicJs;
            ticketHttpClient.doGet(dynamicJSRequestUrl, HttpHeaderEntity.loginInitDynamicJsHeader());

        } catch (Exception e) {
            logger.error("init failure ...",e);
        }

    }

    public void logout() {
        //save cookie
        userCookieService.presisCookie();
        logger.info("logout 12306 ...");
        ticketHttpClient.doGet(URLEntity.LOGOUT_URL, HttpHeaderEntity.logOutHeader());
    }
}
