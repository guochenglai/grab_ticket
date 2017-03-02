package net.gcl.ticket.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import net.gcl.ticket.factory.BeanFactory;
import net.gcl.ticket.http.TicketHttpClient;
import net.gcl.ticket.model.Passenger;
import net.gcl.ticket.conf.URLEntity;
import net.gcl.ticket.model.HttpHeaderEntity;
import net.gcl.ticket.model.enums.SeatType;
import net.gcl.ticket.util.IOUtil;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by guochenglai on 1/22/17.
 */
public class PassengerService {
    private TicketHttpClient ticketHttpClient = BeanFactory.getSingletonBean(TicketHttpClient.class.getName());
    private Logger logger = LoggerFactory.getLogger(PassengerService.class);


    public List<Passenger> queryPassenger(List<NameValuePair> paramList) {
        logger.info("query passenger info ....");
        HttpResponse passengerHttpResponse = ticketHttpClient.doPost(URLEntity.QUERY_PASSENGERS_URL, paramList, HttpHeaderEntity.queryPassengersHeader());
        String passengerInfo = IOUtil.readInputStream(passengerHttpResponse);
        JSONObject passengerJsonObject = JSON.parseObject(passengerInfo);
        JSONArray passengerJsonArray = passengerJsonObject.getJSONObject("data").getJSONArray("normal_passengers");
        if (passengerJsonArray == null) {
            logger.error("query passenger info error result is empty http response content is : {}", passengerInfo);
            return null;
        }

        List<Passenger> passengerList = new ArrayList<>();
        for (int i = 0; i < passengerJsonArray.size(); i++) {
            JSONObject passengerJson = passengerJsonArray.getJSONObject(i);
            Passenger passenger = new Passenger();
            passenger.setName(passengerJson.getString("passenger_name"));
            passenger.setType(passengerJson.getString("passenger_type"));
            passenger.setTypeName(passengerJson.getString("passenger_type_name"));
            passenger.setMobile(passengerJson.getString("mobile_no"));
            passenger.setEmail(passengerJson.getString("email"));
            passenger.setIdType(passengerJson.getString("passenger_id_type_code"));
            passenger.setIdTypeName(passengerJson.getString("passenger_id_type_name"));
            passenger.setIdNumber(passengerJson.getString("passenger_id_no"));
            passenger.setBirthday(passengerJson.getString("born_date"));

            passengerList.add(passenger);
        }

        return passengerList;
    }


}
