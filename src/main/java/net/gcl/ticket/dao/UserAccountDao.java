package net.gcl.ticket.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import net.gcl.ticket.conf.SysConf;
import net.gcl.ticket.factory.BeanFactory;
import net.gcl.ticket.model.AccountInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by guochenglai on 2/15/17.
 */
public class UserAccountDao {

    private static Logger logger = LoggerFactory.getLogger(UserAccountDao.class);
    private BaseDao baseDao = BeanFactory.getSingletonBean(BaseDao.class.getName());

    static {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (Exception e) {
            logger.error("load sqlite jdbc cause exception ", e);
        }
    }

    public boolean saveAccountInfo(String accountInfo, String password) {

        String sql = "INSERT INTO user_info(account,password) VALUES('" + accountInfo + "','" + password + "');";
        return baseDao.executeUpdate(sql);
    }

    public AccountInfo queryAccountInfo() {
        String sql = "SELECT * FROM user_info LIMIT 1;";
        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:" + SysConf.DB_PATH); PreparedStatement preparedStatement = connection.prepareStatement(sql); ResultSet resultSet = preparedStatement.executeQuery();) {
            if (resultSet.next()) {
                String account = resultSet.getString("account");
                String password = resultSet.getString("password");
                return new AccountInfo(account, password);
            }
            logger.info("save  user info  success ");
        } catch (Exception e) {
            logger.error("", e);
        }
        return null;
    }

    public boolean deleteAllAccountInfo() {
        String sql = "DELETE FROM user_info";
        return baseDao.executeUpdate(sql);
    }



}
