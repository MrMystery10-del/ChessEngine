package gui.controllers;


import core.pojo.ChessEngine;
import gui.util.Utility4Gui;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import utils.ImageManager;

public class MatchPlace {

    private ChessEngine model = ChessEngine.getInstance();

    @FXML
    GridPane chessBoard;
    @FXML
    private ImageView playerOneIcon;
    @FXML
    private Label playerOneName;
    @FXML
    private ImageView playerTwoIcon;
    @FXML
    private Label playerTwoName;

    public void initialize() {
        Utility4Gui.makeBoard(chessBoard, 80);


        playerOneIcon = new ImageView(new Image(String.valueOf(ImageManager.class.getResource("/images/bots/bob_picture.png"))));

        playerOneName.setText(model.playerOne.getName());


    }
}
