package com.knightclient.core;

import com.knightclient.core.constants.Configuration;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.util.logging.Logger;
import javax.swing.*;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

import static java.lang.System.exit;

public class Core extends Application {

    private static final Logger logger = Logger.getLogger(Core.class.getName());
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
        try {
            Configuration.parseCommandLine(args);
        } catch (IndexOutOfBoundsException exception) {
            cliError();
        }
        launch();
    }

    private static void cliError() {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("gui.translations.bundle", Locale.getDefault());
        JOptionPane.showMessageDialog(new JFrame(), resourceBundle.getString("cliError"), "Error",
                JOptionPane.ERROR_MESSAGE);
        logger.log(java.util.logging.Level.SEVERE, "Command line error has happened");
        exit(-1);
    }
}