package gui;

public class InGameScreen{}
/*
/**
 * Main screen for playing the chess game, displays the board and pieces
 */
/*
public abstract class InGameScreen extends Screen {

    //load current local language - english if not available (yet)
    private final ResourceBundle resourceBundle = ResourceBundle.getBundle("gui.translations.bundle", Locale.getDefault());

    private final BoardGui boardGui;
    private final PlayersGUI playersGUI;
    private final StateGUI stateGUI = new StateGUI();
    private final GameStateGui gameStateGui = new GameStateGui(resourceBundle);

    /**
     * Construct the screen
     */
    /*
    public InGameScreen(Bot bot) {
        //placeholders + inserts for testing
        Player player1 = new Player("some User", ImageManager.getPiece(Pieces.KING)); // Placeholder
        Player player2 = new Player(bot.getName(), bot.getPicture());
        player2.asBot(bot.getElo(), bot.getDescription());

        Board board = new Board();
        board.setPlayers(player1, player2);
        board.startNewBoard();




        //main board section
        boardGui = new BoardGui(0, 0, 800, 800, board, bot);
        new BoardGuiController(board, boardGui, gameStateGui);

        playersGUI = new PlayersGUI(player1, player2);
        playersGUI.setBounds(800, 0, 1120, 800);
        stateGUI.setBounds(0, 800, 1920, 280);
        gameStateGui.setBounds(800, 800, 1120, 100);

        add(boardGui);
        add(playersGUI);
        add(gameStateGui);
        add(stateGUI);
    }
}
*/