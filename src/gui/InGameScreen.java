package gui;

import content.Screen;
import core.pojo.Player;
import gui.inGameScreen.Board;
import gui.inGameScreen.PlayersGUI;
import gui.inGameScreen.StateGUI;
import manage.ImageManager;
import manage.Pieces;

/**
 * Main screen for playing the chess game, displays the board and pieces
 */
public abstract class InGameScreen extends Screen {

    private Board board = new Board();
    private PlayersGUI playersGUI;
    private StateGUI stateGUI = new StateGUI();
    private Player player1 = new Player("some User", ImageManager.getPiece(Pieces.KING)); // Placeholder
    private Player player2 = new Player("some AI", ImageManager.getPiece(Pieces.KING_BLACK));; // Placeholder

    /**
     * Construct the screen
     */
    public InGameScreen() {
        playersGUI = new PlayersGUI(player1, player2);

        board.setBounds(0, 0, 800, 800);

        playersGUI.setBounds(800, 0, 1120, 800);

        stateGUI.setBounds(0, 800, 1920, 280);

        add(board);
        add(playersGUI);
        add(stateGUI);
    }
}
