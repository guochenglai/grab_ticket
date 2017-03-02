package net.gcl.ticket.dao;

import net.gcl.ticket.conf.SysConf;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

/**
 * Created by guochenglai on 2/16/17.
 */
public class BaseDao {
    private Logger logger = LoggerFactory.getLogger(BaseDao.class);

    public boolean executeUpdate(String sql) {
        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:"+ SysConf.DB_PATH); PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.executeUpdate();
            logger.info("execute sql  success ");
        } catch (Exception e) {
            logger.error("", e);
            return false;
        }
        return true;
    }
}
