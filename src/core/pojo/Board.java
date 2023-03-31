package core.pojo;


import core.pojo.Pieces.*;

import java.awt.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Board {

    private static final Logger logger = Logger.getLogger(Board.class.getName());
    private byte[][] gameBoard;
    private Piece[][] pieceBoard;
    private Player playerOne;
    private Player playerTwo;

    public Board() {
        startNewBoard();
    }

    public void setPlayers(Player playerOne, Player playerTwo) {
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;

        if (playerTwo.isBot())
            logger.log(Level.INFO, "Player 2 set as bot");
    }

    /**
     * init a new board with pieces
     */
    public void startNewBoard() {

        this.gameBoard = new byte[][]{
                {-4, -2, -3, -5, -6, -3, -2, -4},
                {-1, -1, -1, -1, -1, -1, -1, -1},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {+1, +1, +1, +1, +1, +1, +1, +1},
                {+4, +2, +3, +5, +6, +3, +2, +4}
        };

        convertToPiece();
    }

    public Piece[][] getPieceBoard() {
        return pieceBoard;
    }

    //TODO -> backend insertion point ?
    public boolean getNpcMove() {
        return false;
    }


    public byte[][] getGameBoard() {
        //sorry, not getting the original for reading
        return gameBoard.clone();
    }

    /**
     * Convert backend Byte array to Oo piece array
     */
    public void convertToPiece() {

        if (pieceBoard == null) {
            pieceBoard = new Piece[8][8];
            logger.log(Level.INFO, "initializing pieceBoard");
        }
        logger.log(Level.FINEST, "converting byte array to actual pieces");

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                var number = gameBoard[i][j];

                switch (number) {
                    case -6 -> pieceBoard[i][j] = new King(new Position(i, j), Color.BLACK);
                    case -5 -> pieceBoard[i][j] = new Queen(new Position(i, j), Color.BLACK);
                    case -4 -> pieceBoard[i][j] = new Rook(new Position(i, j), Color.BLACK);
                    case -3 -> pieceBoard[i][j] = new Bishop(new Position(i, j), Color.BLACK);
                    case -2 -> pieceBoard[i][j] = new Knight(new Position(i, j), Color.BLACK);
                    case -1 -> pieceBoard[i][j] = new Pawn(new Position(i, j), Color.BLACK);
                    case 6 -> pieceBoard[i][j] = new King(new Position(i, j), Color.WHITE);
                    case 5 -> pieceBoard[i][j] = new Queen(new Position(i, j), Color.WHITE);
                    case 4 -> pieceBoard[i][j] = new Rook(new Position(i, j), Color.WHITE);
                    case 3 -> pieceBoard[i][j] = new Bishop(new Position(i, j), Color.WHITE);
                    case 2 -> pieceBoard[i][j] = new Knight(new Position(i, j), Color.WHITE);
                    case 1 -> pieceBoard[i][j] = new Pawn(new Position(i, j), Color.WHITE);
                }
            }

        }
    }


}