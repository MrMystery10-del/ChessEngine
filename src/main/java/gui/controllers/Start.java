package gui.controllers;


import core.Core;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.util.Timer;
import java.util.TimerTask;

public class Start {

    @FXML
    ImageView logo;

    public Start() {
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    try {
                        switchScenes();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
            }
        };
        Timer timer = new Timer();
        timer.schedule(task, 1000);
    }

    public void switchScenes() throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Pages/Login.fxml"));
        Stage window = Core.getStage();

        window.setScene(new Scene(root, 800, 500));
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        window.setX((primScreenBounds.getWidth() - window.getWidth()) / 2);
        window.setY((primScreenBounds.getHeight() - window.getHeight()) / 2);
    }
}