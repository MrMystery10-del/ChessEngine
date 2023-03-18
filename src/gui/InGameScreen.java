package gui;

import content.Screen;
import gui.inGameScreen.Board;
import gui.inGameScreen.PlayersGUI;
import gui.inGameScreen.StateGUI;

/**
 * Main screen for playing the chess game, displays the board and pieces
 */
public abstract class InGameScreen extends Screen {

    private Board board = new Board();
    private PlayersGUI playersGUI = new PlayersGUI();
    private StateGUI stateGUI = new StateGUI();

    /**
     * Construct the screen
     */
    public InGameScreen() {
        board.setBounds(0, 0, 800, 800);

        playersGUI.setBounds(800, 0, 1120, 800);

        stateGUI.setBounds(0, 800, 1920, 280);

        add(board);
        add(playersGUI);
        add(stateGUI);
    }
}
