package net.gcl.ticket.test.service;

import com.google.common.collect.Lists;
import net.gcl.ticket.factory.BeanFactory;
import net.gcl.ticket.http.factory.HttpClientFactory;
import net.gcl.ticket.model.AccountInfo;
import net.gcl.ticket.model.TrainInfo;
import net.gcl.ticket.model.enums.SeatType;
import net.gcl.ticket.service.LoginService;
import net.gcl.ticket.service.OrderSubmitService;
import net.gcl.ticket.service.TrainQueryService;
import net.gcl.ticket.service.UserAccountService;
import net.gcl.ticket.util.DateUtil;
import org.apache.commons.lang.time.DateUtils;
import org.apache.http.NameValuePair;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.message.BasicNameValuePair;
import org.junit.Test;

import java.util.*;

/**
 * Created by guochenglai on 2/10/17.
 */
public class OrderSubmitServiceTest {
    private OrderSubmitService orderSubmitService = BeanFactory.getSingletonBean(OrderSubmitService.class.getName());
    private TrainQueryService trainQueryService = BeanFactory.getSingletonBean(TrainQueryService.class.getName());
    private LoginService loginService = BeanFactory.getSingletonBean(LoginService.class.getName());
    private UserAccountService accountService = BeanFactory.getSingletonBean(UserAccountService.class.getName());

    @Test
    public void testAutoSubmitOrder() {
        Date trainDate = DateUtils.addDays(new Date(), 15);
        String fromStation = "CSQ";
        String fromStationName = "长沙";
        String toStation = "BJP";
        String toStationName = "北京";
        String purposeCode = "ADULT";//成人
        String tourFlag = "dc";//单程
        SeatType seatType = SeatType.HARD_SLEEPER;


        AccountInfo accountInfo = accountService.queryAccountInfo();

        loginService.autoLogin(accountInfo.getAccount(), accountInfo.getPassword());

        List<TrainInfo> canByHardSleeperTrainList = trainQueryService.queryHardSleeperCanByTrainList(trainDate,fromStation,toStation,purposeCode);

        TrainInfo trainInfo = canByHardSleeperTrainList.get(0);


//        orderSubmitService.autoSubmitOrder(trainInfo, ticketPersonIdList, trainDate, tourFlag, purposeCode, fromStationName, toStationName, seatType);

        loginService.logout();
    }
}
