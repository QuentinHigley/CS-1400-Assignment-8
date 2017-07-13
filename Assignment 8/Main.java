package sample;

import com.sun.org.apache.regexp.internal.RE;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.io.*;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Encrypt this!");
        primaryStage.setScene(new Scene(root, 500, 450));
        primaryStage.show();
    }










    public static void main(String[] args) {
        launch(args);
    }
}
