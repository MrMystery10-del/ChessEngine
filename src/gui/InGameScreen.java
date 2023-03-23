package gui;

import content.Screen;
import core.pojo.Player;
import gui.inGameScreen.Board;
import gui.inGameScreen.GameStateGui;
import gui.inGameScreen.PlayersGUI;
import gui.inGameScreen.StateGUI;
import manage.ImageManager;
import manage.Pieces;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Main screen for playing the chess game, displays the board and pieces
 */
public abstract class InGameScreen extends Screen {

    //load current local language - english if not available (yet)
    ResourceBundle resourceBundle = ResourceBundle.getBundle("gui.translations.bundle", Locale.getDefault());

    private Board board = new Board();
    private PlayersGUI playersGUI;
    private StateGUI stateGUI = new StateGUI();
    private Player player1 = new Player("some User", ImageManager.getPiece(Pieces.KING)); // Placeholder
    private Player player2 = new Player("some AI", ImageManager.getPiece(Pieces.KING_BLACK));; // Placeholder

    private GameStateGui gameStateGui = new GameStateGui(resourceBundle);

    /**
     * Construct the screen
     */
    public InGameScreen() {
        playersGUI = new PlayersGUI(player1, player2);

        board.setBounds(0, 0, 800, 800);

        playersGUI.setBounds(800, 0, 1120, 800);

        stateGUI.setBounds(0, 800, 1920, 280);

        gameStateGui.setBounds(800,800,1120,100);



        add(board);
        add(playersGUI);
        add(gameStateGui);
        add(stateGUI);


    }
}
