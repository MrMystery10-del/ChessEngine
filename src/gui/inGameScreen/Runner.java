package gui.inGameScreen;

import core.pojo.pieces.King;
import core.pojo.Position;

import java.awt.*;

public class Runner {

    public static void main(String[] args) {

       King king = new King(new Position(0,0), Color.WHITE);
        System.out.println(king.getPiece());
    }
}