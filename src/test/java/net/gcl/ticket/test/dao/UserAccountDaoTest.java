package net.gcl.ticket.test.dao;

import com.alibaba.fastjson.JSON;
import net.gcl.ticket.conf.SysConf;
import net.gcl.ticket.dao.UserAccountDao;
import net.gcl.ticket.factory.BeanFactory;
import net.gcl.ticket.model.AccountInfo;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

/**
 * Created by guochenglai on 2/16/17.
 */
public class UserAccountDaoTest {
    UserAccountDao userAccountDao = BeanFactory.getSingletonBean(UserAccountDao.class.getName());

    @Test
    public void testFindDB() {
        System.out.println("==========================================");
        System.out.println(SysConf.DB_PATH);
        System.out.println("==========================================");
    }

    @Test
    public void testCreateTable() {
        String sql = "CREATE TABLE  user_info " +
                "(id INT PRIMARY KEY     ," +
                " account  TEXT    NOT NULL, " +
                " password    TEXT     NOT NULL)";

        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:"+SysConf.DB_PATH); PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.executeUpdate();
        } catch (Exception e) {
        }
    }


    @Test
    public void testSave12306Account() {
        String account = "";
        String password = "";
        userAccountDao.saveAccountInfo(account, password);

    }

    @Test
    public void testQuery12306Account() {
        AccountInfo accountInfo = userAccountDao.queryAccountInfo();
        System.out.println("=======================");
        System.out.println(JSON.toJSONString(accountInfo));
        System.out.println("=======================");
    }

    @Test
    public void testDeleteAllAccount() {
        userAccountDao.deleteAllAccountInfo();
    }
}
