package core.pojo;


import java.util.logging.Level;
import java.util.logging.Logger;

public class Board {

    private static final Logger logger = Logger.getLogger(Board.class.getName());
    private byte[][] gameBoard;
    private Player playerOne;
    private Player playerTwo;

    public Board() {
        startNewBoard();
    }

    public void setPlayers(Player playerOne, Player playerTwo) {
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
        //todo -> bots

        if (playerTwo.isBot())
            logger.log(Level.INFO, "Player 2 set as bot");
    }

    /**
     * init a new board with pieces
     */
    public void startNewBoard() {
        byte[][] newBoard = {
                {-4, -2, -3, -5, -6, -3, -2, -4},
                {-1, -1, -1, -1, -1, -1, -1, -1},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {+1, +1, +1, +1, +1, +1, +1, +1},
                {+4, +2, +3, +5, +6, +3, +2, +4}
        };
        this.gameBoard = newBoard;
    }

    public byte[][] getGameBoard() {
        //sorry, not getting the original for reading
        return gameBoard.clone();
    }
}