package net.gcl.ticket.ui;

import java.net.URL;
import java.util.ResourceBundle;

import net.gcl.ticket.task.CheckUserLoginTask;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sun.javafx.robot.impl.FXRobotHelper;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import net.gcl.ticket.factory.BeanFactory;
import net.gcl.ticket.model.AccountInfo;
import net.gcl.ticket.service.LoginService;
import net.gcl.ticket.service.UserAccountService;

/**
 * Created by guochenglai on 2/17/17.
 */
public class LoginController implements Initializable {
    private Logger logger = LoggerFactory.getLogger(LoginController.class);
    private LoginService loginService = BeanFactory.getSingletonBean(LoginService.class.getName());
    private UserAccountService userAccountService = BeanFactory.getSingletonBean(UserAccountService.class.getName());
    private CheckUserLoginTask loginTask = BeanFactory.getSingletonBean(CheckUserLoginTask.class.getName());

    @FXML
    private TextField userName;
    @FXML
    private PasswordField password;

    @FXML
    public void login(ActionEvent event) throws Exception{

        logger.info("save user info to local db ");
        userAccountService.hashCode();
        userAccountService.saveAccountInfo(userName.getText(), password.getText());

        logger.info("auto login 12306 ");
        String message = loginService.autoLogin(userName.getText(), password.getText());
        if (StringUtils.isEmpty(message)) {//loginSuccess
            logger.info("login success ");
            ObservableList<Stage> stage = FXRobotHelper.getStages();
            Parent root = FXMLLoader.load(this.getClass().getResource("/fxml/MainPane.fxml"));

            Stage primaryStage = stage.get(0);
            primaryStage.setTitle("登陆成功");
            primaryStage.setScene(new Scene(root));

            //起一个离线任务刷用户登录状态，使用户保持一直登录
            loginTask.checkUserLogin();

        } else {//loginError
            logger.error("login error ...");
        }
    }

    @FXML
    public void clear(ActionEvent event) {
        userName.setText(null);
        password.setText(null);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        AccountInfo accountInfo = userAccountService.queryAccountInfo();
        if (accountInfo != null) {
            logger.info("query local saved user 12306 account info ");
            userName.setText(accountInfo.getAccount());
            password.setText(accountInfo.getPassword());
        }
    }



}
