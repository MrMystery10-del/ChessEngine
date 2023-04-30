package com.knightclient.core.bots.high_bots_1000_2000;

import com.knightclient.core.bots.Bot;
import com.knightclient.core.bots.util.Move;

import java.awt.image.BufferedImage;

@SuppressWarnings("unused")
public class James implements Bot {

    private static final int elo = 1500;
    private static final String name = "James";
    private static final String description = "No description currently";
    private static final String algorithm = null;
    private static final BufferedImage image = null;

    @Override
    public Move playNewMove(byte[][] board, boolean turn) {
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
        return null;
    }
}
