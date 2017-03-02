package net.gcl.ticket.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.gcl.ticket.dao.UserCookieDao;
import net.gcl.ticket.factory.BeanFactory;

import net.gcl.ticket.http.factory.HttpClientFactory;
import org.apache.commons.collections.CollectionUtils;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;

/**
 * Created by guochenglai on 2/16/17.
 */
public class UserCookieService {
    private Logger logger = LoggerFactory.getLogger(UserAccountService.class);
    private UserCookieDao userCookieDao = BeanFactory.getSingletonBean(UserCookieDao.class.getName());
    private HttpClientFactory httpClientFactory = BeanFactory.getSingletonBean(HttpClientFactory.class.getName());

    public void presisCookie() {
        clearAllCookieInfo();
        BasicCookieStore cookieStore = httpClientFactory.getCookieStore();
        List<Cookie> cookieList = cookieStore.getCookies();
        if (CollectionUtils.isNotEmpty(cookieList)) {

            Map<String, String> cookieMap = new HashMap<>();
            for (Cookie cookie : cookieList) {
                cookieMap.put(cookie.getName(), cookie.getValue());
            }
            logger.info("save cookie info :{} ", JSON.toJSONString(cookieMap));
            saveUserCookieInfo(JSON.toJSONString(cookieMap));
        }

    }

    public boolean saveUserCookieInfo(String cookieInfo) {
        logger.info("save user cookie info : {}", cookieInfo);
        return userCookieDao.saveUserCookieInfo(cookieInfo);
    }

    public Map<String,String> queryAllCookie() {
        Map<String, String> cookieMap = userCookieDao.queryAllCookie();
        logger.info("query all cookie info  is : {}", JSON.toJSONString(cookieMap));
        return cookieMap;

    }

    public boolean clearAllCookieInfo() {
        logger.info("clear all cookie info ");
        return userCookieDao.clearAllCookieInfo();
    }
}
