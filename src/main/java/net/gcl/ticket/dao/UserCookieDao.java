package net.gcl.ticket.dao;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import net.gcl.ticket.conf.SysConf;
import net.gcl.ticket.factory.BeanFactory;
import net.gcl.ticket.model.AccountInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Map;

/**
 * Created by guochenglai on 2/16/17.
 */
public class UserCookieDao {
    private static Logger logger = LoggerFactory.getLogger(UserAccountDao.class);
    private BaseDao baseDao = BeanFactory.getSingletonBean(BaseDao.class.getName());

    static {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (Exception e) {
            logger.error("load sqlite jdbc cause exception ", e);
        }
    }

    public boolean saveUserCookieInfo(String cookieInfo) {
        String sql = "INSERT INTO user_cookie(cookie) VALUES('" + cookieInfo + "');";
        return baseDao.executeUpdate(sql);
    }

    public Map<String,String> queryAllCookie() {
        String sql = "SELECT * FROM user_cookie LIMIT 1;";
        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:"+ SysConf.DB_PATH); PreparedStatement preparedStatement = connection.prepareStatement(sql); ResultSet resultSet = preparedStatement.executeQuery();) {
            if (resultSet.next()) {
                String cookie = resultSet.getString("cookie");
                Map<String,String> cookieMap = JSON.parseObject(cookie,new TypeReference<Map<String, String>>() {});
                return cookieMap;
            }
            logger.info("save  user info  success ");
        } catch (Exception e) {
            logger.error("", e);
        }
        return null;

    }

    public boolean clearAllCookieInfo() {
        String sql = "DELETE FROM user_cookie";
        return baseDao.executeUpdate(sql);
    }

}
