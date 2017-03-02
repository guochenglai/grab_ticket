package net.gcl.ticket.model;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by guochenglai on 2/13/17.
 */
public class HttpHeaderEntity {
    public static Map<String,String> loginInitHeader() {
        Map<String, String> loginInitMap = new HashMap<>();
        loginInitMap.put("Host", "kyfw.12306.cn");
        loginInitMap.put("Connection", "keep-alive");
        loginInitMap.put("Cache-Control","max-age=0");
        loginInitMap.put("Upgrade-Insecure-Requests", "1");
        loginInitMap.put("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.87 Safari/537.36");
        loginInitMap.put("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
        loginInitMap.put("Referer", "https://kyfw.12306.cn/otn/leftTicket/init");
        loginInitMap.put("Accept-Encoding", "gzip, deflate, sdch, br");
        loginInitMap.put("Accept-Language", "zh-CN,zh;q=0.8,en;q=0.6");
        return loginInitMap;
    }

    public static Map<String,String> userLoginHeader() {
        Map<String, String> userLoginMap = new HashMap<>();
        userLoginMap.put("Host", "kyfw.12306.cn");
        userLoginMap.put("Connection", "keep-alive");
        userLoginMap.put("Cache-Control", "max-age=0");
        userLoginMap.put("Origin", "https://kyfw.12306.cn");
        userLoginMap.put("Upgrade-Insecure-Requests", "1");
        userLoginMap.put("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.87 Safari/537.36");
        userLoginMap.put("Content-Type", "application/x-www-form-urlencoded");
        userLoginMap.put("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
        userLoginMap.put("Referer", "https://kyfw.12306.cn/otn/login/init");
        userLoginMap.put("Accept-Encoding", "gzip, deflate, br");
        userLoginMap.put("Accept-Language", "zh-CN,zh;q=0.8,en;q=0.6");
        return userLoginMap;
    }

    public static Map<String,String> initMy12306Header() {
        Map<String, String> initMy12306Map = new HashMap<>();
        initMy12306Map.put("Host", "kyfw.12306.cn");
        initMy12306Map.put("Connection", "keep-alive");
        initMy12306Map.put("Cache-Control", "max-age=0");
        initMy12306Map.put("Upgrade-Insecure-Requests", "1");
        initMy12306Map.put("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.87 Safari/537.36");
        initMy12306Map.put("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
        initMy12306Map.put("Referer", "https://kyfw.12306.cn/otn/login/init");
        initMy12306Map.put("Accept-Encoding", "gzip, deflate, sdch, br");
        initMy12306Map.put("Accept-Language", "zh-CN,zh;q=0.8,en;q=0.6");
        return initMy12306Map;
    }

    public static Map<String,String> loginInitDynamicJsHeader() {
        Map<String, String> dynamicJsMap = new HashMap<>();
        dynamicJsMap.put("Host", "kyfw.12306.cn");
        dynamicJsMap.put("Connection", "keep-alive");
        dynamicJsMap.put("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.87 Safari/537.36");
        dynamicJsMap.put("Accept", "*/*");
        dynamicJsMap.put("Referer", "https://kyfw.12306.cn/otn/login/init");
        dynamicJsMap.put("Accept-Encoding", "gzip, deflate, sdch, br");
        dynamicJsMap.put("Accept-Language", "zh-CN,zh;q=0.8,en;q=0.6");
        return dynamicJsMap;
    }

    public static Map<String,String> initDCDynamicJsHeader() {
        Map<String, String> dynamicJsMap = new HashMap<>();
        dynamicJsMap.put("Host", "kyfw.12306.cn");
        dynamicJsMap.put("Connection", "keep-alive");
        dynamicJsMap.put("Content-Length", "10");
        dynamicJsMap.put("Cache-Control", "max-age=0");
        dynamicJsMap.put("Origin", "https://kyfw.12306.cn");
        dynamicJsMap.put("Upgrade-Insecure-Requests", "1");
        dynamicJsMap.put("ser-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.87 Safari/537.36");
        dynamicJsMap.put("Content-Type", "application/x-www-form-urlencoded");
        dynamicJsMap.put("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
        dynamicJsMap.put("Referer", "https://kyfw.12306.cn/otn/leftTicket/init");
        dynamicJsMap.put("Accept-Encoding", "gzip, deflate, br");
        dynamicJsMap.put("Accept-Language", "zh-CN,zh;q=0.8,en;q=0.6");
        return dynamicJsMap;
    }

    public static Map<String,String> loginPassCodeHeader() {
        Map<String, String> loginPassCode = new HashMap<>();
        loginPassCode.put("Host", "kyfw.12306.cn");
        loginPassCode.put("Connection", "keep-alive");
        loginPassCode.put("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.87 Safari/537.36");
        loginPassCode.put("Accept", "image/webp,image/*,*/*;q=0.8");
        loginPassCode.put("Referer", "https://kyfw.12306.cn/otn/login/init");
        loginPassCode.put("Accept-Encoding", "gzip, deflate, sdch, br");
        loginPassCode.put("Accept-Language", "zh-CN,zh;q=0.8,en;q=0.6");
        return loginPassCode;
    }


    public static Map<String,String> getPassengerQRCodeHeader() {
        Map<String, String> qrMap = new HashMap<>();
        qrMap.put("Host", "kyfw.12306.cn");
        qrMap.put("Connection", "keep-alive");
        qrMap.put("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.87 Safari/537.36");
        qrMap.put("Accept", "image/webp,image/*,*/*;q=0.8");
        qrMap.put("Referer", "https://kyfw.12306.cn/otn/leftTicket/init");
        qrMap.put("Accept-Encoding", "gzip, deflate, sdch, br");
        qrMap.put("Accept-Language", "zh-CN,zh;q=0.8,en;q=0.6");
        return qrMap;
    }

    public static Map<String,String> checkLoginPassCodeHeader() {
        Map<String, String> checkLoginPassCode = new HashMap<>();
        checkLoginPassCode.put("Host", "kyfw.12306.cn");
        checkLoginPassCode.put("Connection", "keep-alive");
        checkLoginPassCode.put("Accept", "*/*");
        checkLoginPassCode.put("Origin", "https://kyfw.12306.cn");
        checkLoginPassCode.put("X-Requested-With", "XMLHttpRequest");
        checkLoginPassCode.put("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.87 Safari/537.36");
        checkLoginPassCode.put("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        checkLoginPassCode.put("Referer", "https://kyfw.12306.cn/otn/login/init");
        checkLoginPassCode.put("Accept-Encoding", "gzip, deflate, br");
        checkLoginPassCode.put("Accept-Language", "zh-CN,zh;q=0.8,en;q=0.6");
        return checkLoginPassCode;
    }
    public static Map<String,String> loginHeader() {
        Map<String, String> loginMap = checkLoginPassCodeHeader();
        return loginMap;
    }

    public static Map<String,String> ticketQueryHeader() {
        Map<String, String> ticketQueryMap = new HashMap<>();
        ticketQueryMap.put("Host", "kyfw.12306.cn");
        ticketQueryMap.put("Connection", "keep-alive");
        ticketQueryMap.put("Cache-Control", "no-cache");
        ticketQueryMap.put("Accept", "*/*");
        ticketQueryMap.put("X-Requested-With", "XMLHttpRequest");
        ticketQueryMap.put("If-Modified-Since", "0");
        ticketQueryMap.put("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.87 Safari/537.36");
        ticketQueryMap.put("Referer", "https://kyfw.12306.cn/otn/leftTicket/init");
        ticketQueryMap.put("Accept-Encoding", "gzip, deflate, sdch, br");
        ticketQueryMap.put("Accept-Language", "zh-CN,zh;q=0.8,en;q=0.6");
        return ticketQueryMap;

    }

    public static Map<String,String> ticketLogQueryHeader() {
        Map<String, String> ticketQueryInitMap = ticketQueryInitHeader();
        return ticketQueryInitMap;
    }

    public static Map<String,String> ticketQueryInitHeader() {
        Map<String, String> ticketQueryInitMap = new HashMap<>();
        ticketQueryInitMap.put("Host", "kyfw.12306.cn");
        ticketQueryInitMap.put("Connection", "keep-alive");
        ticketQueryInitMap.put("Upgrade-Insecure-Requests", "1");
        ticketQueryInitMap.put("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.87 Safari/537.36");
        ticketQueryInitMap.put("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
        ticketQueryInitMap.put("Referer", "https://kyfw.12306.cn/otn/index/initMy12306");
        ticketQueryInitMap.put("Accept-Encoding", "gzip, deflate, sdch, br");
        ticketQueryInitMap.put("Accept-Language", "zh-CN,zh;q=0.8,en;q=0.6");
        return ticketQueryInitMap;
    }

    public static Map<String,String> leftTicketDynamicJsHeader() {
        Map<String, String> ticketQueryDynamicJsHeader = new HashMap<>();
        ticketQueryDynamicJsHeader.put("Host", "kyfw.12306.cn");
        ticketQueryDynamicJsHeader.put("Connection", "keep-alive");
        ticketQueryDynamicJsHeader.put("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.87 Safari/537.36");
        ticketQueryDynamicJsHeader.put("Accept", "*/*");
        ticketQueryDynamicJsHeader.put("Referer", "https://kyfw.12306.cn/otn/leftTicket/init");
        ticketQueryDynamicJsHeader.put("Accept-Encoding", "gzip, deflate, sdch, br");
        ticketQueryDynamicJsHeader.put("Accept-Language", "zh-CN,zh;q=0.8,en;q=0.6");
        return ticketQueryDynamicJsHeader;
    }

    public static Map<String,String> getQueueCountHeader() {
        Map<String, String> getQueueCountMap = new HashMap<>();
        getQueueCountMap.put("Host", "kyfw.12306.cn");
        getQueueCountMap.put("Connection", "keep-alive");
        getQueueCountMap.put("Accept", "application/json, text/javascript, */*; q=0.01");
        getQueueCountMap.put("Origin", "https://kyfw.12306.cn");
        getQueueCountMap.put("X-Requested-With", "XMLHttpRequest");
        getQueueCountMap.put("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.87 Safari/537.36");
        getQueueCountMap.put("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        getQueueCountMap.put("Referer", "https://kyfw.12306.cn/otn/confirmPassenger/initDc");
        getQueueCountMap.put("Accept-Encoding", "gzip, deflate, br");
        getQueueCountMap.put("Accept-Language", "zh-CN,zh;q=0.8,en;q=0.6");

        return getQueueCountMap;
    }

    public static Map<String,String> confirmPassengerInitDCHeader() {
        Map<String, String> confirmPassengerInitDCMap = new HashMap<>();
        confirmPassengerInitDCMap.put("Host", "kyfw.12306.cn");
        confirmPassengerInitDCMap.put("Connection", "keep-alive");
        confirmPassengerInitDCMap.put("Cache-Control", "max-age=0");
        confirmPassengerInitDCMap.put("Origin", "https://kyfw.12306.cn");
        confirmPassengerInitDCMap.put("Upgrade-Insecure-Requests", "1");
        confirmPassengerInitDCMap.put("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.87 Safari/537.36");
        confirmPassengerInitDCMap.put("Content-Type", "application/x-www-form-urlencoded");
        confirmPassengerInitDCMap.put("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
        confirmPassengerInitDCMap.put("Referer", "https://kyfw.12306.cn/otn/leftTicket/init?random=" + System.currentTimeMillis());
        confirmPassengerInitDCMap.put("Accept-Encoding", "gzip, deflate, br");
        confirmPassengerInitDCMap.put("Accept-Language", "zh-CN,zh;q=0.8,en;q=0.6");
        return confirmPassengerInitDCMap;
    }


    public static Map<String,String> checkUserLoginHttpHeader() {
        Map<String, String> checkUserLoginMap = new HashMap<>();
        checkUserLoginMap.put("Host", "kyfw.12306.cn");
        checkUserLoginMap.put("Connection", "keep-alive");
        checkUserLoginMap.put("Cache-Control", "no-cache");
        checkUserLoginMap.put("Origin", "https://kyfw.12306.cn");
        checkUserLoginMap.put("X-Requested-With", "XMLHttpRequest");
        checkUserLoginMap.put("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        checkUserLoginMap.put("Accept", "*/*");
        checkUserLoginMap.put("If-Modified-Since", "0");
        checkUserLoginMap.put("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.87 Safari/537.36");
        checkUserLoginMap.put("Referer", "https://kyfw.12306.cn/otn/leftTicket/init?random=1487063537072");
        checkUserLoginMap.put("Accept-Encoding", "gzip, deflate, br");
        checkUserLoginMap.put("Accept-Language", "zh-CN,zh;q=0.8,en;q=0.6");

        return checkUserLoginMap;
    }



    public static Map<String,String> queryPassengersHeader() {
        Map<String, String> queryPassengersMap = new HashMap<>();
        queryPassengersMap.put("Host", "kyfw.12306.cn");
        queryPassengersMap.put("Connection", "keep-alive");
        queryPassengersMap.put("Accept", "*/*");
        queryPassengersMap.put("Origin", "https://kyfw.12306.cn");
        queryPassengersMap.put("X-Requested-With", "XMLHttpRequest");
        queryPassengersMap.put("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.87 Safari/537.36");
        queryPassengersMap.put("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        queryPassengersMap.put("Referer", "https://kyfw.12306.cn/otn/confirmPassenger/initDc");
        queryPassengersMap.put("Accept-Encoding", "gzip, deflate, br");
        queryPassengersMap.put("Accept-Language", "zh-CN,zh;q=0.8,en;q=0.6");
        return queryPassengersMap;
    }

    public static Map<String,String> checkOrderInfoHeader() {
        Map<String, String> checkOrderInfoMap = getQueueCountHeader();
        return checkOrderInfoMap;
    }

    public static Map<String,String> submitOrderRequestHttpHeader() {
        Map<String, String> submitOrderRequestMap = new HashMap<>();
        submitOrderRequestMap.put("Host", "kyfw.12306.cn");
        submitOrderRequestMap.put("Connection", "keep-alive");
        submitOrderRequestMap.put("Accept", "*/*");
        submitOrderRequestMap.put("Origin", "https://kyfw.12306.cn");
        submitOrderRequestMap.put("X-Requested-With", "XMLHttpRequest");
        submitOrderRequestMap.put("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.87 Safari/537.36");
        submitOrderRequestMap.put("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        submitOrderRequestMap.put("Referer", "https://kyfw.12306.cn/otn/leftTicket/init?random=" + System.currentTimeMillis());
        submitOrderRequestMap.put("Accept-Encoding", "gzip, deflate, br");
        submitOrderRequestMap.put("Accept-Language", "zh-CN,zh;q=0.8,en;q=0.6");
        return submitOrderRequestMap;
    }

    public static Map<String,String> queryQueueCountHeader() {
        Map<String, String> queryQueueCountMap = checkOrderInfoHeader();
        return queryQueueCountMap;
    }

    public static Map<String,String> submitOrderHeader() {
        Map<String, String> queryQueueCountMap = checkOrderInfoHeader();
        return queryQueueCountMap;
    }


    public static Map<String,String> logOutHeader() {
        Map<String, String> loginOutHeader = new HashMap<>();
        loginOutHeader.put("Host", "kyfw.12306.cn");
        loginOutHeader.put("Connection", "keep-alive");
        loginOutHeader.put("Upgrade-Insecure-Requests", "1");
        loginOutHeader.put("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.87 Safari/537.36");
        loginOutHeader.put("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
        loginOutHeader.put("Accept-Encoding", "gzip, deflate, sdch, br");
        loginOutHeader.put("Accept-Language", "zh-CN,zh;q=0.8,en;q=0.6");
        return loginOutHeader;
    }

}
