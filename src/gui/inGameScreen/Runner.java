package gui.inGameScreen;

import bots.nooby.Nooby;

import java.awt.*;
import java.util.*;
import java.util.List;

public class Runner {

    public static void main(String[] args) {

        //Locale locale_en_EN = new Locale("en", "EN");
        ResourceBundle resourceBundle = ResourceBundle.getBundle("gui.translations.bundle", Locale.getDefault());
        System.out.println(resourceBundle.getString("load"));
        System.out.println(Locale.getDefault());

        List<Nooby.Move> possibleMoves = new ArrayList<>();


        for (int i = 10; i > 0; i--) {
            Nooby.Move move =new Nooby.Move(5,6,7,8,(byte)i);
            possibleMoves.add(move);
        }

        System.out.println(possibleMoves);
        System.out.println(possibleMoves.get(0));
        System.out.println(possibleMoves.stream().max(Comparator.comparingInt(Nooby.Move::capturedPiece)).get());






    }
}