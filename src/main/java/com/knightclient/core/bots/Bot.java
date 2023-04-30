package com.knightclient.core.bots;


import com.knightclient.core.bots.util.Move;

import java.awt.image.BufferedImage;

public interface Bot {

    Move playNewMove(byte[][] board, boolean turn);

    /**
     * @return the elo of the bot
     */
    int getElo();

    /**
     * @return name of the bot
     */
    String getName();

    /**
     * @return imagine description/bio of the bot as a small storyline
     */
    String getDescription();

    /**
     * @return name of the used algorithm for the bot
     */
    String getUsedAlgorithm();

    /**
     * @return name of the picture of the bot
     */
    BufferedImage getPicture();
}
