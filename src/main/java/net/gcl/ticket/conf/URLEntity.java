package net.gcl.ticket.conf;

/**
 * Created by guochenglai on 1/5/17.
 */
public class URLEntity {

    public final static String DAMA2_LOGIN_URL = "http://api.dama2.com:7777/app/login";

    //------------------   自动打码相关的URl  -------------------------
    public final static String DAMA2_QUERY_BANANCE_URL = "http://api.dama2.com:7777/app/getBalance";
    public final static String DAMA2_QUERY_QR_ID_URL = "http://api.dama2.com:7777/app/decode";
    public final static String DAMA2_QUERY_QR_ID_CONTENT_URL = "http://api.dama2.com:7777/app/getResult";
    public final static String DAMA2_PREAUTH_URL = "http://api.dama2.com:7777/app/preauth";
    //------------------   自动打码相关的URl  ----------------------


    //------------------  12306  dynamic js ------------------
    public final static String DYNAMIC_JS_URL = "https://kyfw.12306.cn/otn/dynamicJs/";
    //------------------  12306  dynamic js ------------------


    //------------------  12306登陆相关URL ------------------
    public final static String LOGIN_INIT_URL = "https://kyfw.12306.cn/otn/login/init";
    public final static String LOGIN_AYSN_SUGGEST_URL = "https://kyfw.12306.cn/otn/login/loginAysnSuggest";
    public static final String CHECK_USER_LOGIN_URL = "https://kyfw.12306.cn/otn/login/checkUser";
    public static final String LOGOUT_URL = "https://kyfw.12306.cn/otn/login/loginOut";
    //------------------  12306登陆相关URL ------------------


    //------------------  12306验证码URL ------------------
    public final static String PASSENGER_PASSCODE_URL = "https://kyfw.12306.cn/otn/passcodeNew/getPassCodeNew?module=passenger&rand=randp";
    public final static String LOGIN_PASSCODE_URL = "https://kyfw.12306.cn/otn/passcodeNew/getPassCodeNew?module=login&rand=sjrand";
    public final static String CHECK_PASSCODE_URL = "https://kyfw.12306.cn/otn/passcodeNew/checkRandCodeAnsyn";
    //------------------  12306验证码URL ------------------

    //------------------  12306查询乘客URL ------------------
    public static final String QUERY_PASSENGERS_URL = "https://kyfw.12306.cn/otn/confirmPassenger/getPassengerDTOs";
    //------------------  12306查询乘客URL ------------------

    //------------------  12306查询余票URL ------------------
    public final static String INIT_MY_12306_URL = "https://kyfw.12306.cn/otn/index/initMy12306";
    public final static String LEFT_TICKET_INIT_URL = "https://kyfw.12306.cn/otn/leftTicket/init";
    public final static String TICKET_QUERY_LOG_URL = "https://kyfw.12306.cn/otn/leftTicket/log";
    public final static String TICKET_QUERY_URL = "https://kyfw.12306.cn/otn/leftTicket/query";
    //------------------  12306查询余票URL ------------------


    //------------------  12306订单提交URL ------------------
    public final static String SUBMIT_ORDER_REQUEST_URL = "https://kyfw.12306.cn/otn/leftTicket/submitOrderRequest";
    public final static String INIT_DC_URL = "https://kyfw.12306.cn/otn/confirmPassenger/initDc";
    public final static String CHECK_ORDER_INFO_URL = "https://kyfw.12306.cn/otn/confirmPassenger/checkOrderInfo";
    public final static String GET_QUEUE_COUNT_URL = "https://kyfw.12306.cn/otn/confirmPassenger/getQueueCount";
    public final static String CONFIRM_SINGLE_URL = "https://kyfw.12306.cn/otn/confirmPassenger/confirmSingleForQueue";
    public final static String QUERY_ORDER_WAIT_URL = "https://kyfw.12306.cn/otn/confirmPassenger/queryOrderWaitTime";
    //------------------  12306订单提交URL ------------------

}
