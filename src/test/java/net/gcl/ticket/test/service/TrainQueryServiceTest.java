package net.gcl.ticket.test.service;

import com.alibaba.fastjson.JSON;
import net.gcl.ticket.factory.BeanFactory;
import net.gcl.ticket.model.TrainInfo;
import net.gcl.ticket.service.TrainQueryService;
import net.gcl.ticket.util.DateUtil;
import org.apache.commons.lang.time.DateUtils;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by guochenglai on 1/26/17.
 */
public class TrainQueryServiceTest {
    private Logger logger = LoggerFactory.getLogger(TrainQueryServiceTest.class);
    private TrainQueryService trainQueryService = BeanFactory.getSingletonBean(TrainQueryService.class.getName());

    @Test
    public void testQueryAllTrain() {

        Date trainDate = DateUtils.addDays(new Date(), 15);
        String fromStation = "CSQ";
        String fromStationName = "长沙";
        String toStation = "BJP";
        String toStationName = "北京";
        String purposeode = "ADULT";


        List<TrainInfo> trainInfoList = trainQueryService.queryAllTrain(trainDate,fromStation,toStation,purposeode);

        logger.info("=============================");
        System.out.println(JSON.toJSONString(trainInfoList));
        logger.info("=============================");

    }

    @Test
    public void testQueryCanBuyTrainList() {
        Date trainDate = DateUtils.addDays(new Date(), 15);
        String fromStation = "CSQ";
        String fromStationName = "长沙";
        String toStation = "BJP";
        String toStationName = "北京";
        String purposeode = "ADULT";
        List<TrainInfo> trainInfoList = trainQueryService.queryCanBuyTrainList(trainDate,fromStation,toStation,purposeode);

        logger.info("=============================");
        System.out.println(JSON.toJSONString(trainInfoList));
        logger.info("=============================");
    }

    @Test
    public void testQueryHardSleeperCanBuyTrainList() {
        Date trainDate = DateUtils.addDays(new Date(), 15);
        String fromStation = "CSQ";
        String fromStationName = "长沙";
        String toStation = "BJP";
        String toStationName = "北京";
        String purposeode = "ADULT";

        List<TrainInfo> trainInfoList = trainQueryService.queryHardSleeperCanByTrainList(trainDate,fromStation,toStation,purposeode);

        logger.info("=============================");
        System.out.println(JSON.toJSONString(trainInfoList));
        logger.info("=============================");
    }



}
