package net.gcl.ticket.service;

import com.alibaba.fastjson.JSON;
import net.gcl.ticket.dao.UserAccountDao;
import net.gcl.ticket.factory.BeanFactory;
import net.gcl.ticket.model.AccountInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by guochenglai on 2/16/17.
 */
public class UserAccountService {
    private UserAccountDao userAccountDao = BeanFactory.getSingletonBean(UserAccountDao.class.getName());
    private Logger logger = LoggerFactory.getLogger(UserAccountDao.class.getName());

    public boolean saveAccountInfo(String accountInfo, String password) {
        logger.info("save 12306 account info ");
        return userAccountDao.saveAccountInfo(accountInfo,password);
    }

    public AccountInfo queryAccountInfo() {
        AccountInfo accountInfo = userAccountDao.queryAccountInfo();
        logger.info("query 12306 account info :{} ", JSON.toJSONString(accountInfo));
        return accountInfo;
    }

    public boolean deleteAllAccountInfo() {
        logger.info("delete all 12306 account info ");
        return userAccountDao.deleteAllAccountInfo();
    }


}
