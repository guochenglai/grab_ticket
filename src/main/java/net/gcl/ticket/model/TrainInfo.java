package net.gcl.ticket.model;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by guochenglai on 1/26/17.
 */
public class TrainInfo {
    private SimpleBooleanProperty checked = new SimpleBooleanProperty(false);
    private StringProperty trainNum;//列车编号例如：800000K4720V
    private StringProperty trainCode;//车次名称例如：K472
    private StringProperty trainTypeCode;//
    private StringProperty trainSeatFeature;//
    private StringProperty seatFeature;//
    private StringProperty seatTypes;//
    private StringProperty secretStr;//隐藏码，每趟车都有一个隐藏码
    private StringProperty buttonTextInfo;//表示的预定那个按钮显示的汉字，当显示预定二字的时候表示该趟列车是可以预定的，否则会给出相应的提示
    private StringProperty ypEx;
    private StringProperty ypInfo;
    private StringProperty trainFlag;//0表示列车正常
    private StringProperty trainFlagMessage;
    private StringProperty canWebBuy;// Y/N
    private StringProperty trainLocationCode;// M1

    private StringProperty startCityCode;//始发站城市编号
    private StringProperty startProvinceCode;//始发站省份编号
    private StringProperty startStationName;//始发站名称
    private StringProperty startStationCode;//始发站编码
    private StringProperty startTime;//始发站发车时间
    private StringProperty startTrainDate;//始发站发车日期

    private StringProperty endCityCode;//终点站城市编号
    private StringProperty endProvinceCode;//终点站省份编号
    private StringProperty endStationName;//终点站名称
    private StringProperty endStationCode;//终点站编码
    private StringProperty endTime;//终点站发车时间  arrive_time

    private StringProperty fromStationName;//上车站名称
    private StringProperty fromStationNo;//上车站编号
    private StringProperty fromStationCode;//上车站编码

    private StringProperty toStationName;//下车站名称
    private StringProperty toStationNo;//下车站编号
    private StringProperty toStationCode;//下车站编码

    private StringProperty dayDifference;//列车发车时间和达到时间的天数之差，如果不隔天，就是0
    private StringProperty lishi;//列车一共会用多少小时
    private StringProperty lishiValue;//将上面的时间换算成分钟

    private StringProperty businessSeatNum;//商务座数量 swz_num
    private StringProperty superSeatNum;//特等座数量 tz_num
    private StringProperty firstClassSeatNum;//一等座数量 zy_num
    private StringProperty secondClassSeatNum;//二等座数量 ze_num
    private StringProperty highLevelSoftSleeperSeatNum;//高级软卧数量
    private StringProperty softSleeperSeatNum;//软卧数量 rw_num
    private StringProperty hardSleeperSeatNum;//硬卧数量 yw_num
    private StringProperty hardSeatNum;//硬座数量 yz_num

    public void setChecked(boolean checked) {
        this.checked = new SimpleBooleanProperty(checked);
    }

    public void setTrainNum(String trainNum) {
        this.trainNum = new SimpleStringProperty(trainNum);
    }

    public void setTrainCode(String trainCode) {
        this.trainCode = new SimpleStringProperty(trainCode);
    }

    public void setTrainTypeCode(String trainTypeCode) {
        this.trainTypeCode = new SimpleStringProperty(trainTypeCode);
    }

    public void setTrainSeatFeature(String trainSeatFeature) {
        this.trainSeatFeature = new SimpleStringProperty(trainSeatFeature);
    }

    public void setSeatFeature(String seatFeature) {
        this.seatFeature = new SimpleStringProperty(seatFeature);
    }

    public void setSeatTypes(String seatTypes) {
        this.seatTypes = new SimpleStringProperty(seatTypes);
    }

    public void setSecretStr(String secretStr) {
        this.secretStr = new SimpleStringProperty(secretStr);
    }

    public void setButtonTextInfo(String buttonTextInfo) {
        this.buttonTextInfo = new SimpleStringProperty(buttonTextInfo);
    }

    public void setYpEx(String ypEx) {
        this.ypEx = new SimpleStringProperty(ypEx);
    }

    public void setYpInfo(String ypInfo) {
        this.ypInfo = new SimpleStringProperty(ypInfo);
    }

    public void setTrainFlag(String trainFlag) {
        this.trainFlag = new SimpleStringProperty(trainFlag);
    }

    public void setTrainFlagMessage(String trainFlagMessage) {
        this.trainFlagMessage = new SimpleStringProperty(trainFlagMessage);
    }

    public void setCanWebBuy(String canWebBuy) {
        this.canWebBuy = new SimpleStringProperty(canWebBuy);
    }

    public void setTrainLocationCode(String trainLocationCode) {
        this.trainLocationCode = new SimpleStringProperty(trainLocationCode);
    }

    public void setStartCityCode(String startCityCode) {
        this.startCityCode = new SimpleStringProperty(startCityCode);
    }

    public void setStartProvinceCode(String startProvinceCode) {
        this.startProvinceCode = new SimpleStringProperty(startProvinceCode);
    }

    public void setStartStationName(String startStationName) {
        this.startStationName = new SimpleStringProperty(startStationName);
    }

    public void setStartStationCode(String startStationCode) {
        this.startStationCode = new SimpleStringProperty(startStationCode);
    }

    public void setStartTime(String startTime) {
        this.startTime = new SimpleStringProperty(startTime);
    }

    public void setStartTrainDate(String startTrainDate) {
        this.startTrainDate = new SimpleStringProperty(startTrainDate);
    }

    public void setEndCityCode(String endCityCode) {
        this.endCityCode = new SimpleStringProperty(endCityCode);
    }

    public void setEndProvinceCode(String endProvinceCode) {
        this.endProvinceCode = new SimpleStringProperty(endProvinceCode);
    }

    public void setEndStationName(String endStationName) {
        this.endStationName = new SimpleStringProperty(endStationName);
    }

    public void setEndStationCode(String endStationCode) {
        this.endStationCode = new SimpleStringProperty(endStationCode);
    }

    public void setEndTime(String endTime) {
        this.endTime = new SimpleStringProperty(endTime);
    }

    public void setFromStationName(String fromStationName) {
        this.fromStationName = new SimpleStringProperty(fromStationName);
    }

    public void setFromStationNo(String fromStationNo) {
        this.fromStationNo = new SimpleStringProperty(fromStationNo);

    }

    public void setFromStationCode(String fromStationCode) {
        this.fromStationCode = new SimpleStringProperty(fromStationCode);
    }

    public void setToStationName(String toStationName) {
        this.toStationName = new SimpleStringProperty(toStationName);
    }

    public void setToStationNo(String toStationNo) {
        this.toStationNo = new SimpleStringProperty(toStationNo);
    }

    public void setToStationCode(String toStationCode) {
        this.toStationCode = new SimpleStringProperty(toStationCode);
    }

    public void setDayDifference(String dayDifference) {
        this.dayDifference = new SimpleStringProperty(dayDifference);
    }

    public void setLishi(String lishi) {
        this.lishi = new SimpleStringProperty(lishi);
    }

    public void setLishiValue(String lishiValue) {
        this.lishiValue = new SimpleStringProperty(lishiValue);
    }

    public void setBusinessSeatNum(String businessSeatNum) {
        this.businessSeatNum = new SimpleStringProperty(businessSeatNum);
    }

    public void setSuperSeatNum(String superSeatNum) {
        this.superSeatNum = new SimpleStringProperty(superSeatNum);
    }

    public void setFirstClassSeatNum(String firstClassSeatNum) {
        this.firstClassSeatNum = new SimpleStringProperty(firstClassSeatNum);
    }

    public void setSecondClassSeatNum(String secondClassSeatNum) {
        this.secondClassSeatNum = new SimpleStringProperty(secondClassSeatNum);
    }

    public void setHighLevelSoftSleeperSeatNum(String highLevelSoftSleeperSeatNum) {
        this.highLevelSoftSleeperSeatNum = new SimpleStringProperty(highLevelSoftSleeperSeatNum);
    }

    public void setSoftSleeperSeatNum(String softSleeperSeatNum) {
        this.softSleeperSeatNum = new SimpleStringProperty(softSleeperSeatNum);
    }

    public void setHardSleeperSeatNum(String hardSleeperSeatNum) {
        this.hardSleeperSeatNum = new SimpleStringProperty(hardSleeperSeatNum);
    }

    public void setHardSeatNum(String hardSeatNum) {
        this.hardSeatNum = new SimpleStringProperty(hardSeatNum);
    }

    public boolean isChecked() {
        return checked.get();
    }

    public SimpleBooleanProperty checkedProperty() {
        return checked;
    }

    public String getTrainNum() {
        return trainNum.get();
    }

    public StringProperty trainNumProperty() {
        return trainNum;
    }

    public String getTrainCode() {
        return trainCode.get();
    }

    public StringProperty trainCodeProperty() {
        return trainCode;
    }

    public String getTrainTypeCode() {
        return trainTypeCode.get();
    }

    public StringProperty trainTypeCodeProperty() {
        return trainTypeCode;
    }

    public String getTrainSeatFeature() {
        return trainSeatFeature.get();
    }

    public StringProperty trainSeatFeatureProperty() {
        return trainSeatFeature;
    }

    public String getSeatFeature() {
        return seatFeature.get();
    }

    public StringProperty seatFeatureProperty() {
        return seatFeature;
    }

    public String getSeatTypes() {
        return seatTypes.get();
    }

    public StringProperty seatTypesProperty() {
        return seatTypes;
    }

    public String getSecretStr() {
        return secretStr.get();
    }

    public StringProperty secretStrProperty() {
        return secretStr;
    }

    public String getButtonTextInfo() {
        return buttonTextInfo.get();
    }

    public StringProperty buttonTextInfoProperty() {
        return buttonTextInfo;
    }

    public String getYpEx() {
        return ypEx.get();
    }

    public StringProperty ypExProperty() {
        return ypEx;
    }

    public String getYpInfo() {
        return ypInfo.get();
    }

    public StringProperty ypInfoProperty() {
        return ypInfo;
    }

    public String getTrainFlag() {
        return trainFlag.get();
    }

    public StringProperty trainFlagProperty() {
        return trainFlag;
    }

    public String getTrainFlagMessage() {
        return trainFlagMessage.get();
    }

    public StringProperty trainFlagMessageProperty() {
        return trainFlagMessage;
    }

    public String getCanWebBuy() {
        return canWebBuy.get();
    }

    public StringProperty canWebBuyProperty() {
        return canWebBuy;
    }

    public String getTrainLocationCode() {
        return trainLocationCode.get();
    }

    public StringProperty trainLocationCodeProperty() {
        return trainLocationCode;
    }

    public String getStartCityCode() {
        return startCityCode.get();
    }

    public StringProperty startCityCodeProperty() {
        return startCityCode;
    }

    public String getStartProvinceCode() {
        return startProvinceCode.get();
    }

    public StringProperty startProvinceCodeProperty() {
        return startProvinceCode;
    }

    public String getStartStationName() {
        return startStationName.get();
    }

    public StringProperty startStationNameProperty() {
        return startStationName;
    }

    public String getStartStationCode() {
        return startStationCode.get();
    }

    public StringProperty startStationCodeProperty() {
        return startStationCode;
    }

    public String getStartTime() {
        return startTime.get();
    }

    public StringProperty startTimeProperty() {
        return startTime;
    }

    public String getStartTrainDate() {
        return startTrainDate.get();
    }

    public StringProperty startTrainDateProperty() {
        return startTrainDate;
    }

    public String getEndCityCode() {
        return endCityCode.get();
    }

    public StringProperty endCityCodeProperty() {
        return endCityCode;
    }

    public String getEndProvinceCode() {
        return endProvinceCode.get();
    }

    public StringProperty endProvinceCodeProperty() {
        return endProvinceCode;
    }

    public String getEndStationName() {
        return endStationName.get();
    }

    public StringProperty endStationNameProperty() {
        return endStationName;
    }

    public String getEndStationCode() {
        return endStationCode.get();
    }

    public StringProperty endStationCodeProperty() {
        return endStationCode;
    }

    public String getEndTime() {
        return endTime.get();
    }

    public StringProperty endTimeProperty() {
        return endTime;
    }

    public String getFromStationName() {
        return fromStationName.get();
    }

    public StringProperty fromStationNameProperty() {
        return fromStationName;
    }

    public String getFromStationNo() {
        return fromStationNo.get();
    }

    public StringProperty fromStationNoProperty() {
        return fromStationNo;
    }

    public String getFromStationCode() {
        return fromStationCode.get();
    }

    public StringProperty fromStationCodeProperty() {
        return fromStationCode;
    }

    public String getToStationName() {
        return toStationName.get();
    }

    public StringProperty toStationNameProperty() {
        return toStationName;
    }

    public String getToStationNo() {
        return toStationNo.get();
    }

    public StringProperty toStationNoProperty() {
        return toStationNo;
    }

    public String getToStationCode() {
        return toStationCode.get();
    }

    public StringProperty toStationCodeProperty() {
        return toStationCode;
    }

    public String getDayDifference() {
        return dayDifference.get();
    }

    public StringProperty dayDifferenceProperty() {
        return dayDifference;
    }

    public String getLishi() {
        return lishi.get();
    }

    public StringProperty lishiProperty() {
        return lishi;
    }

    public String getLishiValue() {
        return lishiValue.get();
    }

    public StringProperty lishiValueProperty() {
        return lishiValue;
    }

    public String getBusinessSeatNum() {
        return businessSeatNum.get();
    }

    public StringProperty businessSeatNumProperty() {
        return businessSeatNum;
    }

    public String getSuperSeatNum() {
        return superSeatNum.get();
    }

    public StringProperty superSeatNumProperty() {
        return superSeatNum;
    }

    public String getFirstClassSeatNum() {
        return firstClassSeatNum.get();
    }

    public StringProperty firstClassSeatNumProperty() {
        return firstClassSeatNum;
    }

    public String getSecondClassSeatNum() {
        return secondClassSeatNum.get();
    }

    public StringProperty secondClassSeatNumProperty() {
        return secondClassSeatNum;
    }

    public String getHighLevelSoftSleeperSeatNum() {
        return highLevelSoftSleeperSeatNum.get();
    }

    public StringProperty highLevelSoftSleeperSeatNumProperty() {
        return highLevelSoftSleeperSeatNum;
    }

    public String getSoftSleeperSeatNum() {
        return softSleeperSeatNum.get();
    }

    public StringProperty softSleeperSeatNumProperty() {
        return softSleeperSeatNum;
    }

    public String getHardSleeperSeatNum() {
        return hardSleeperSeatNum.get();
    }

    public StringProperty hardSleeperSeatNumProperty() {
        return hardSleeperSeatNum;
    }

    public String getHardSeatNum() {
        return hardSeatNum.get();
    }

    public StringProperty hardSeatNumProperty() {
        return hardSeatNum;
    }
}
