package com.knightclient.core;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class Core extends Application {

    private static Stage primaryStage;

    @Override
    public void start(Stage stage) throws IOException {
        /*PS use fxml files only for gui construction*/
        //LOADING FXML FILES
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/Pages/DashBoard.fxml"));
        setPrimaryStage(stage);
        stage.setScene(new Scene(fxmlLoader.load()));
        //PROPERTIES
        stage.setResizable(true);
        //stage.initStyle(StageStyle.TRANSPARENT);
        stage.show();
        stage.setMaximized(true);
        //ARRANGING CENTER
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX((primScreenBounds.getWidth() - stage.getWidth()) / 2);
        stage.setY((primScreenBounds.getHeight() - stage.getHeight()) / 2);
    }

    public static Stage getStage() {
        return primaryStage;
    }

    public static void setPrimaryStage(Stage stage) {primaryStage=stage;}

    public static void main(String[] args) {
        launch();
    }
}