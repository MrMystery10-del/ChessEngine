package bots.insane_bots_2000_9999;

import bots.Bot;
import bots.util.Move;
import core.pojo.Board;
import gui.inGameScreen.BoardGui;

import java.awt.image.BufferedImage;

@SuppressWarnings("unused")
public class Markus implements Bot {

    private static final int elo = 2500;
    private static final String name = "Markus";
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