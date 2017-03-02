package net.gcl.ticket.ui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Created by guochenglai on 2/16/17.
 */
public class Main extends Application {
    private Logger logger = LoggerFactory.getLogger(Main.class);

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(this.getClass().getResource("/fxml/LoginPane.fxml"));
        primaryStage.setTitle("抢票");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);

    }
}
