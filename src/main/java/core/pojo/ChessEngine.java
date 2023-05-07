package core.pojo;


import persistence.Persistence;

import java.util.logging.Logger;

public class ChessEngine {

    private static ChessEngine instance;


    private static final Logger logger = Logger.getLogger(ChessEngine.class.getName());

    public Board board;
    public Player playerOne;
    public Player playerTwo;
    public Persistence persistence = Persistence.getInstance();


    private ChessEngine() {
        logger.finest("new ChessEngine Started");
        board = new Board();
    }

    public static ChessEngine getInstance() {
        if (instance == null) instance = new ChessEngine();
        return instance;
    }

    //human player
    public void setPlayerOne(Player player) {
        this.playerOne = player;
    }

    public void setPlayerTwo(Player player) {
        this.playerTwo = player;
        if (playerOne != null) {
            board.setPlayers(playerOne, playerTwo);
        }
    }


}
