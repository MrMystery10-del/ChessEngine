package com.knightclient.core.controllers;

import com.knightclient.core.Core;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class DashBoard {

    @FXML
    private Button BotMatch;

    public void switchScenes() throws Exception
    {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Pages/MatchPlace.fxml"));
        Stage window = Core.getStage();
        window.setScene(new Scene(root,800,500));
        window.hide();
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        window.setX((primScreenBounds.getWidth() - window.getWidth()) / 2);
        window.setY((primScreenBounds.getHeight() - window.getHeight()) / 2);
        window.show();
        window.setMaximized(true);
    }

    @FXML
    void startBotMatch(ActionEvent event) throws Exception{
        switchScenes();
    }

}
