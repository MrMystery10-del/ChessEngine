package com.knightclient.core.bots.low_bots_0_500;

import com.knightclient.core.bots.Bot;
import com.knightclient.core.bots.util.Move;
import com.knightclient.core.bots.util.Util;
import com.knightclient.core.manage.Bots;
import com.knightclient.core.manage.ImageManager;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Bob implements Bot {

    private static final int elo = 50;
    private static final String name = "Bob";
    private static final String description = """
            Ahoy there, me mateys!
            I be Bob, the newest member of the ChessAI school crew.
            Arrr, me algorithm be a bit scuffed, so don't expect me to be hoisting any championship trophies just yet.
            Ye see, when it comes to takin' out me opponents,
            I be a bit of a ruthless scallywag - I'll take down any piece that me beady eyes deem valuable,
            even if it means sacrificin' one of me own. But fear not, me hearties,
            for me blunders be just as likely to work in yer favor as they be to sink me ship.
            With a bit of hard work and some grog, I'll be ready to take on any scallywag who dares cross me path!
            """;
    private static final String algorithm = "Value Capture Algorithm";
    private static final BufferedImage image = ImageManager.getBotPicture(Bots.BOB);

    public Move playNewMove(byte[][] board, boolean isWhiteTurn) {
        // Initialize a list of possible moves
        List<Move> possibleMoves = new ArrayList<>();

        // Loop through all the pieces on the board
        for (int i = 0; i < board.length; i++)
            for (int j = 0; j < board[i].length; j++) {
                byte piece = board[i][j];
                if ((piece > 0 && isWhiteTurn) || (piece < 0 && !isWhiteTurn)) {
                    // This is one of our pieces, so check its possible moves
                    List<Move> moves = Util.getPossibleMoves(board, i, j);
                    possibleMoves.addAll(moves);
                }
            }

        // If there are no possible moves, we've either checkmated or stalemated the opponent
        if (possibleMoves.isEmpty())
            return null;

        Collections.shuffle(possibleMoves);

        // Sort the list of possible moves in descending order of the value of the captured piece (if any)
        possibleMoves.sort((m1, m2) -> Integer.compare(Math.abs(m2.capturedPiece()), Math.abs(m1.capturedPiece())));

        // Select the move with the highest value capture (if any), or else just the first move in the list
        return possibleMoves.get(0);
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