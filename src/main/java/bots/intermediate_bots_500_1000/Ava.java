package bots.intermediate_bots_500_1000;

import bots.Bot;
import bots.util.Move;
import bots.util.Util;
import gui.inGameScreen.BoardGui;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SuppressWarnings("unused")
public class Ava implements Bot {

    private static final int elo = 500;
    private static final String name = "Ava";
    private static final String description = """
            Hi there, I'm Ava and I'm determined to become a chess master and teach at the Chess Academy.
            I love capturing pieces, but controlling the center of the board is my real passion.
            My best friend Bob may be a pirate, but he's got nothing on me when it comes to chess.
            I'm all about the community and the thrill of the match. Come find me at the Chess Academy,
            and let's play a game or two. It'll be fun!
            """;
    private static final String algorithm = "Center-priority-algorithm";
    private static final BufferedImage image = null;

    @Override
    public Move playNewMove(byte[][] board, boolean isWhiteTurn, BoardGui buttonBoard) {
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

        possibleMoves.sort((m1, m2) -> {
            // Calculate Manhattan distance of m1 and m2 to center
            int distance1 = Math.abs(m1.toRow() - 3) + Math.abs(m1.toCol() - 3);
            int distance2 = Math.abs(m2.toRow() - 3) + Math.abs(m2.toCol() - 3);

            int startPos1 = Math.abs(m1.fromRow() - 3) + Math.abs(m1.fromCol() - 3);
            int startPos2 = Math.abs(m2.fromRow() - 3) + Math.abs(m2.fromCol() - 3);

            // Calculate the weighted value of the captured piece for m1 and m2
            double value1 = m1.capturedPiece() * 2;
            double value2 = m2.capturedPiece() * 2;

            // Calculate the weighted value of the distance to center for m1 and m2
            double distValue1 = startPos1 - distance1;
            double distValue2 = startPos2 - distance2;

            // Calculate the total weighted value for m1 and m2
            double totalValue1 = value1 + distValue1;
            double totalValue2 = value2 + distValue2;

            // Compare the total weighted values and return the result
            return Double.compare(totalValue2, totalValue1);
        });

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
