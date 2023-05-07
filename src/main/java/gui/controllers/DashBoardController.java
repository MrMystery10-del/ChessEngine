package gui.controllers;


import core.Configuration;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class DashBoardController {

    @FXML
    private Button BotMatch;

    @FXML
    private Button profileShortcut;
    @FXML
    private Button dashBoardShortcut;
    @FXML
    private Button loginShortcut;
    @FXML
    private Button signupShortcut;


    public void switchScenes(String fxmlName) throws Exception {
        String location = "/fxml/Pages/" + fxmlName;
        if (!location.endsWith(".fxml")) {
            location += ".fxml";
        }


        Parent root = FXMLLoader.load(getClass().getResource(location), Configuration.resourceBundle);
        //Stage window = Core.getStage();  single window client
        Stage window = new Stage();  // multi window client

        window.setScene(new Scene(root, 1024, 800));
        window.hide();
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        window.setX((primScreenBounds.getWidth() - window.getWidth()) / 2);
        window.setY((primScreenBounds.getHeight() - window.getHeight()) / 2);
        window.show();
        window.setMaximized(false);
    }

    @FXML
    void startBotMatch(ActionEvent event) throws Exception {
        switchScenes("MatchPlace");

    }

    @FXML
    void swapToProfilePage(ActionEvent event) throws Exception {
        switchScenes("ProfilePage");

    }

    @FXML
    public void swapToDashboardPage(ActionEvent actionEvent) throws Exception {
        switchScenes("Start");
    }


    @FXML
    public void swapToLoginPage(ActionEvent actionEvent) throws Exception {
        switchScenes("Login");
    }

    @FXML
    public void swapToSignupPage(ActionEvent actionEvent) throws Exception {
        switchScenes("Signup");
    }


}
