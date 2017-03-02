package net.gcl.ticket.ui;

import java.io.OutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import com.alibaba.fastjson.JSON;
import javafx.scene.control.*;
import net.gcl.ticket.log.JavaFxStreamAppender;
import net.gcl.ticket.log.TextAreaOutputStream;
import net.gcl.ticket.model.SubmitOrderInfo;
import org.apache.commons.lang.time.DateUtils;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Lists;

import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.cell.CheckBoxListCell;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.util.Callback;
import net.gcl.ticket.factory.BeanFactory;
import net.gcl.ticket.model.Passenger;
import net.gcl.ticket.model.SubmitOrderRequestParam;
import net.gcl.ticket.model.TrainInfo;
import net.gcl.ticket.model.enums.SeatType;
import net.gcl.ticket.service.LoginService;
import net.gcl.ticket.service.OrderSubmitService;
import net.gcl.ticket.service.PassengerService;
import net.gcl.ticket.service.TrainQueryService;
import net.gcl.ticket.util.DateUtil;

/**
 * Created by guochenglai on 2/17/17.
 */
public class MainController implements Initializable {
    private Logger logger = LoggerFactory.getLogger(MainController.class);
    private TrainQueryService trainQueryService = BeanFactory.getSingletonBean(TrainQueryService.class.getName());
    private LoginService loginService = BeanFactory.getSingletonBean(LoginService.class.getName());
    private PassengerService passengerService = BeanFactory.getSingletonBean(PassengerService.class.getName());
    private OrderSubmitService orderSubmitService = BeanFactory.getSingletonBean(OrderSubmitService.class.getName());

    @FXML
    private TextField fromStationTextField;
    @FXML
    private TextField toStationTextField;
    @FXML
    private TextField fromDateTextField;
    @FXML
    private ListView<Passenger> passengerListView;
    @FXML
    private ListView<SeatType> seatListView;
    @FXML
    private TableView<TrainInfo> trainListTableView;
    @FXML
    private TableColumn<TrainInfo, Boolean> checkBoxTableColumn;
    @FXML
    private TableColumn<TrainInfo, String> trainCodeTableColumn;
    @FXML
    private TableColumn<TrainInfo, String> fromStationTableColumn;
    @FXML
    private TableColumn<TrainInfo, String> startTimeTableColumn;
    @FXML
    private TableColumn<TrainInfo, String> endTimeTableColumn;
    @FXML
    private TableColumn<TrainInfo, String> toStationTableColumn;
    @FXML
    private TableColumn<TrainInfo, String> lishiTableColumn;
    @FXML
    private TableColumn<TrainInfo, String> businessSeatTableColumn;
    @FXML
    private TableColumn<TrainInfo, String> firstClassSeatTableColumn;
    @FXML
    private TableColumn<TrainInfo, String> secondClassSeatTableColumn;
    @FXML
    private TableColumn<TrainInfo, String> softSleeperTableColumn;
    @FXML
    private TableColumn<TrainInfo, String> hardSleeperTableColumn;
    @FXML
    private TableColumn<TrainInfo, String> hardSeatTableColumn;
    @FXML
    private Button submitOrderButton;
    @FXML
    private TextArea logTextArea;

    @FXML
    public void queryAllTrainList() {
        Date trainDate = DateUtil.parseStringToDate(fromDateTextField.getText());
        String fromStation = fromStationTextField.getText();
        String toStation = toStationTextField.getText();
        String purposeCode = "ADULT";//成人
        List<TrainInfo> canByTrainList = trainQueryService.queryAllTrain(trainDate, fromStation, toStation, purposeCode);

        ObservableList<TrainInfo> trainInfoObservableList = FXCollections.observableList(canByTrainList, new Callback<TrainInfo, Observable[]>() {
                    @Override
                    public Observable[] call(TrainInfo param) {
                        param.checkedProperty().addListener(new ChangeListener<Boolean>() {
                            @Override
                            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                                if (newValue) {
                                    SubmitOrderRequestParam.addTrainInfo(param);
                                } else {
                                    SubmitOrderRequestParam.removeTrainInfo(param);
                                }
                            }
                        });
                        return new Observable[0];
                    }
                }
        );


        checkBoxTableColumn.setCellValueFactory(cellData -> cellData.getValue().checkedProperty());
        checkBoxTableColumn.setCellFactory(CheckBoxTableCell.forTableColumn(checkBoxTableColumn));


        trainCodeTableColumn.setCellValueFactory(cellData -> cellData.getValue().trainCodeProperty());
        fromStationTableColumn.setCellValueFactory(cellData -> cellData.getValue().fromStationNameProperty());
        toStationTableColumn.setCellValueFactory(cellData -> cellData.getValue().toStationNameProperty());
        startTimeTableColumn.setCellValueFactory(cellDate -> cellDate.getValue().startTimeProperty());
        endTimeTableColumn.setCellValueFactory(cellDate -> cellDate.getValue().endTimeProperty());
        lishiTableColumn.setCellValueFactory(cellData -> cellData.getValue().lishiProperty());

        businessSeatTableColumn.setCellValueFactory(cellData -> cellData.getValue().businessSeatNumProperty());
        firstClassSeatTableColumn.setCellValueFactory(cellData -> cellData.getValue().firstClassSeatNumProperty());
        secondClassSeatTableColumn.setCellValueFactory(cellData -> cellData.getValue().secondClassSeatNumProperty());
        softSleeperTableColumn.setCellValueFactory(cellData -> cellData.getValue().softSleeperSeatNumProperty());
        hardSleeperTableColumn.setCellValueFactory(cellData -> cellData.getValue().hardSleeperSeatNumProperty());
        hardSeatTableColumn.setCellValueFactory(cellData -> cellData.getValue().hardSeatNumProperty());

        trainListTableView.setItems(trainInfoObservableList);
        trainListTableView.setEditable(true);

    }

    @FXML
    public void submitOrder() throws Exception{
//        submitOrderButton.setText("抢票中");
//        submitOrderButton.setDisable(true);
        List<TrainInfo> trainInfoList = SubmitOrderRequestParam.getTrainInfoList();
        List<Passenger> passengerList = SubmitOrderRequestParam.getPassengerIdList();
        List<SeatType> seatTypeList = SubmitOrderRequestParam.getSeatTypeList();
        String trainDate = fromDateTextField.getText();

        String fromStation = fromStationTextField.getText();
        String toStation = toStationTextField.getText();
        String tourFlag = "dc";
        String purposeCode = "ADULT";


        OutputStream outputStream = new TextAreaOutputStream(logTextArea);
        JavaFxStreamAppender.setStaticOutputStream(outputStream);

        SubmitOrderInfo submitOrderInfo = orderSubmitService.autoSubmitOrder(trainInfoList, passengerList, DateUtil.parseStringToDate(trainDate), tourFlag, purposeCode, fromStation, toStation, seatTypeList);
        logger.info("{}", JSON.toJSONString(submitOrderInfo));
        if (submitOrderInfo.isStatus()) {
            submitOrderButton.setText("抢票成功");
            submitOrderButton.setDisable(false);
        } else {
            submitOrderButton.setText("失败");
            submitOrderButton.setDisable(false);
        }

    }


    @FXML
    public void logout() {
        logger.info("logout 12306 ");
        //loginService.logout();
    }

    @FXML
    public void openWebsite() {
    }

    @FXML
    public void donate() {
    }

    @FXML
    public void about() {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        String fromTrainDate = DateUtil.formatDateToString(DateUtils.addDays(new Date(), 12));
        fromDateTextField.setText(fromTrainDate);
        fromStationTextField.setText("CSQ");
        toStationTextField.setText("BJP");


        List<NameValuePair> queryPassengerParamList = new ArrayList<>();
        queryPassengerParamList.add(new BasicNameValuePair("_json_att", ""));
        List<Passenger> passengerList = passengerService.queryPassenger(queryPassengerParamList);

        ObservableList<Passenger> uiPassengerObservableList = FXCollections.observableList(passengerList, new Callback<Passenger, Observable[]>() {
            @Override
            public Observable[] call(Passenger param) {
                param.checkedProperty().addListener(new ChangeListener<Boolean>() {
                    @Override
                    public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                        if (newValue) {
                            SubmitOrderRequestParam.addPassenger(param);
                        } else {
                            SubmitOrderRequestParam.removePassenger(param);
                        }
                    }
                });
                return new Observable[0];
            }
        });

        passengerListView.setCellFactory(CheckBoxListCell.forListView(item -> item.checkedProperty()));
        passengerListView.setItems(uiPassengerObservableList);

        ObservableList<SeatType> uiSeatTypeEnumObservableList = FXCollections.observableList(Lists.newArrayList(SeatType.values()), new Callback<SeatType, Observable[]>() {
            @Override
            public Observable[] call(SeatType param) {
                param.checkedProperty().addListener(new ChangeListener<Boolean>() {
                    @Override
                    public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                        if (newValue) {
                            SubmitOrderRequestParam.addSeatType(param.getValue());
                        } else {
                            SubmitOrderRequestParam.removeSeatType(param.getValue());
                        }
                    }
                });
                return new Observable[0];
            }
        });

        seatListView.setCellFactory(CheckBoxListCell.forListView(item -> item.checkedProperty()));
        seatListView.setItems(uiSeatTypeEnumObservableList);


    }
}
