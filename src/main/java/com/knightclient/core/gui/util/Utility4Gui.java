package com.knightclient.core.gui.util;

import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

public class Utility4Gui {
    public static void makeBoard(GridPane chessBoard, int size){
        boolean needsBlack = true;
        for(int i=0; i<8; i++){
            for(int j=0; j<8; j++){
                Button square = new Button();
                square.setPrefHeight(size);
                square.setPrefWidth(size);
                square.setStyle(needsBlack ? "-fx-background-color: white":"-fx-background-color: black");
                chessBoard.add(square,i,j);
                needsBlack = !needsBlack;
            }
            needsBlack = !needsBlack;
        }
    }
}
