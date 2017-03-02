package net.gcl.ticket.test.service;

import com.alibaba.fastjson.JSON;
import net.gcl.ticket.factory.BeanFactory;
import net.gcl.ticket.model.AccountInfo;
import net.gcl.ticket.model.Passenger;
import net.gcl.ticket.service.LoginService;
import net.gcl.ticket.service.PassengerService;
import net.gcl.ticket.service.UserAccountService;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by guochenglai on 1/25/17.
 */
public class PassengerServiceTest {
    private LoginService loginService = BeanFactory.getSingletonBean(LoginService.class.getName());
    private PassengerService passengerService = BeanFactory.getSingletonBean(PassengerService.class.getName());
    private UserAccountService accountService = BeanFactory.getSingletonBean(UserAccountService.class.getName());
    private Logger logger = LoggerFactory.getLogger(PassengerServiceTest.class);

    @Test
    public void testQueryPassengerInfo() {
        AccountInfo accountInfo = accountService.queryAccountInfo();
        String message = loginService.autoLogin(accountInfo.getAccount(), accountInfo.getPassword());
        if (StringUtils.isEmpty(message)) {
            logger.info("login success ");
            List<Passenger> passengerList = passengerService.queryPassenger(null);
            logger.info("==={}", JSON.toJSONString(passengerList));

        } else {
            logger.info("{}", message);
        }

        loginService.logout();


    }


}
