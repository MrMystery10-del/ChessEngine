package bots.intermediate_bots_500_1000;

import bots.Bot;
import bots.util.Move;
import gui.inGameScreen.BoardGui;

import java.awt.image.BufferedImage;

@SuppressWarnings("unused")
public class Ava implements Bot {

    private static final int elo = 500;
    private static final String name = "Ava";
    private static final String description = "No description currently";
    private static final String algorithm = null;
    private static final BufferedImage image = null;

    @Override
    public Move playNewMove(byte[][] board, boolean turn, BoardGui buttonBoard) {
        return null;
    }

    @Override
    public int getElo() {
        return elo;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public String getUsedAlgorithm() {
        return algorithm;
    }

    @Override
    public BufferedImage getPicture() {
        return image;
    }
}
