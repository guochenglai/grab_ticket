package net.gcl.ticket.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import net.gcl.ticket.model.TrainInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by guochenglai on 2/16/17.
 */
public class TrainInfoWrapperUtil {
    public static List<TrainInfo> wrapperTrainList(JSONObject trainInfoJsonObject) {
        JSONArray trainList = trainInfoJsonObject.getJSONArray("data");

        List<TrainInfo> trainInfoList = new ArrayList<>();

        for (int i=0;i<trainList.size();i++) {
            JSONObject trainItem = trainList.getJSONObject(i);

            TrainInfo train = new TrainInfo();
            train.setSecretStr(trainItem.getString("secretStr"));
            train.setButtonTextInfo(trainItem.getString("buttonTextInfo"));

            JSONObject detailTrainInfo = trainItem.getJSONObject("queryLeftNewDTO");

            train.setTrainNum(detailTrainInfo.getString("train_no"));
            train.setTrainCode(detailTrainInfo.getString("station_train_code"));
            train.setTrainTypeCode(detailTrainInfo.getString("train_type_code"));
            train.setTrainSeatFeature(detailTrainInfo.getString("train_seat_feature"));
            train.setSeatTypes(detailTrainInfo.getString("seat_types"));
            train.setSeatFeature(detailTrainInfo.getString("seat_feature"));
            train.setYpEx(detailTrainInfo.getString("yp_ex"));
            train.setYpInfo(detailTrainInfo.getString("yp_info"));
            train.setTrainFlag(detailTrainInfo.getString("controlled_train_flag"));
            train.setTrainFlagMessage(detailTrainInfo.getString("controlled_train_message"));
            train.setCanWebBuy(detailTrainInfo.getString("canWebBuy"));
            train.setTrainLocationCode(detailTrainInfo.getString("location_code"));

            train.setStartCityCode(detailTrainInfo.getString("start_city_code"));
            train.setStartProvinceCode(detailTrainInfo.getString("start_province_code"));
            train.setStartStationName(detailTrainInfo.getString("start_station_name"));
            train.setStartStationCode(detailTrainInfo.getString("start_station_telecode"));
            train.setStartTime(detailTrainInfo.getString("start_time"));
            train.setStartTrainDate(detailTrainInfo.getString("start_train_date"));

            train.setEndCityCode(detailTrainInfo.getString("end_city_code"));
            train.setEndProvinceCode(detailTrainInfo.getString("end_province_code"));
            train.setEndStationName(detailTrainInfo.getString("end_station_name"));
            train.setEndStationCode(detailTrainInfo.getString("end_station_telecode"));
            train.setEndTime(detailTrainInfo.getString("arrive_time"));

            train.setFromStationName(detailTrainInfo.getString("from_station_name"));
            train.setFromStationNo(detailTrainInfo.getString("from_station_no"));
            train.setFromStationCode(detailTrainInfo.getString("from_station_telecode"));

            train.setToStationName(detailTrainInfo.getString("to_station_name"));
            train.setToStationNo(detailTrainInfo.getString("to_station_no"));
            train.setToStationCode(detailTrainInfo.getString("to_station_telecode"));

            train.setDayDifference(detailTrainInfo.getString("day_difference"));
            train.setLishi(detailTrainInfo.getString("lishi"));
            train.setLishiValue(detailTrainInfo.getString("lishiValue"));

            train.setBusinessSeatNum(detailTrainInfo.getString("swz_num"));
            train.setSuperSeatNum(detailTrainInfo.getString("tz_num"));
            train.setFirstClassSeatNum(detailTrainInfo.getString("zy_num"));
            train.setSecondClassSeatNum(detailTrainInfo.getString("ze_num"));
            train.setSoftSleeperSeatNum(detailTrainInfo.getString("rw_num"));
            train.setHardSleeperSeatNum(detailTrainInfo.getString("yw_num"));
            train.setHardSeatNum(detailTrainInfo.getString("yz_num"));

            trainInfoList.add(train);
        }

        return trainInfoList;
    }
}
