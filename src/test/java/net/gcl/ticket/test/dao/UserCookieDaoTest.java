package net.gcl.ticket.test.dao;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import net.gcl.ticket.conf.SysConf;
import net.gcl.ticket.dao.UserCookieDao;
import net.gcl.ticket.factory.BeanFactory;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by guochenglai on 2/16/17.
 */
public class UserCookieDaoTest {
    private Logger logger = LoggerFactory.getLogger(UserCookieDaoTest.class);
    private UserCookieDao userCookieDao = BeanFactory.getSingletonBean(UserCookieDao.class.getName());

    @Test
    public void testCreateTable() {
        String sql = "CREATE TABLE  user_cookie " +
                "(id INT PRIMARY KEY     ," +
                " cookie  TEXT    NOT NULL)";

        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:"+ SysConf.DB_PATH); PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.executeUpdate();
        } catch (Exception e) {
        }
    }

    @Test
    public void testSaveCookie() {
        userCookieDao.clearAllCookieInfo();
        Map<String, String> cookieMap = new HashMap<>();
        cookieMap.put("__NRF","");
        cookieMap.put("JSESSIONID","");
        cookieMap.put("BIGipServerotn","");
        cookieMap.put("current_captcha_type","");

        userCookieDao.saveUserCookieInfo(JSONObject.toJSONString(cookieMap));
    }

    @Test
    public void testClearAllCookie() {
        userCookieDao.clearAllCookieInfo();
    }

    @Test
    public void testQueryAllCookieInfo() {
        Map<String, String> cookieMap = userCookieDao.queryAllCookie();
        System.out.println("===================");
        System.out.println(JSON.toJSONString(cookieMap));
        System.out.println("===================");
    }
}

