package gui.controllers;


import gui.util.Utility4Gui;
import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;

public class MatchPlace {
    @FXML
    GridPane chessBoard;

    public void initialize() {
        Utility4Gui util = new Utility4Gui();
        util.makeBoard(chessBoard, 80);
    }
}
