package core.pojo;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Board {

    private static final Logger logger = Logger.getLogger(Board.class.getName());
    private byte[][] gameBoard;
    private Player playerOne;
    private Player playerTwo;

    private boolean side;

    // Constructor for board
    public Board() {
        startNewBoard();
    }

    // Sets the players
    public void setPlayers(Player playerOne, Player playerTwo) {
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;

        if (playerTwo.isBot())
            logger.log(Level.INFO, "Player 2 set as bot");
    }

    // Inititalizes the board with starting posistions for all the peices
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
        side = gameBoard[7][0] > 0;
        convertToPiece();
    }

    // Determines if it is the non-players move
    public boolean getNpcMove() {
        return false;
    }

    // Returns the game board
    public byte[][] getGameBoard() {
        return gameBoard;
    }

    // Converts the byte representations of the board to the piece objects
    public void convertToPiece() {

    }

    // Updates the board
    public void updateBoard(byte[][] newBoard) {
        gameBoard = newBoard;
    }

    // Sets the value of the sepecific position
    public void setValue(int row, int col, byte value) {
        gameBoard[row][col] = value;
    }

    // Returns the side color of the player
    public boolean getSide() {
        return side;
    }
}