package gui.controllers;

import core.pojo.Board;
import core.pojo.pieces.Bishop;
import core.pojo.Position;
import gui.inGameScreen.BoardGui;
import gui.inGameScreen.GameStateGui;
import manage.ImageManager;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BoardGuiController {

    private final Board board;
    private final BoardGui gui;
    private final GameStateGui gameStateGui;

    private static final Logger logger = Logger.getLogger(BoardGuiController.class.getName());

    public BoardGuiController(Board board, BoardGui gui, GameStateGui gameStateGui) {
        this.board = board;
        this.gui = gui;
        this.gameStateGui = gameStateGui;

        addGameStateControls();
        addMouseOverToBoard();
    }

    /**
     * update the chessboard gui with the details from the Piece board
     */
    public void updateBoard() {
        for (int j = 0; j < 8; j++)
            for (int i = 0; i < 8; i++) {

                var piece = board.getPieceBoard()[i][j];
                var update = gui.getSquares()[i][j];

                if (piece != null) {
                    logger.log(Level.FINEST, "board  " + i + "/" + j + " set to " + piece.getPiece());
                    update.setIcon(new ImageIcon(ImageManager.getPiece(piece.getPiece())));
                }
            }
    }


    /**
     * configure the controls on the gameStateDialog
     */
    private void addGameStateControls() {

        gameStateGui.getPlayButton().addActionListener(e -> {
            updateBoard();
            logger.log(Level.INFO, "Play button pushed");
        });

        gameStateGui.getLoadButton().setText("Force backend move");
        gameStateGui.getLoadButton().addActionListener(e-> {
            board.getNpcMove();
            logger.log(Level.INFO, "Asking the backend for a move TODO : REMOVE/ALTER");
        });




    }



    private void addMouseOverToBoard() {
        //todo implement on grid that has a piece

        /*Map<Position, Color> colors = new HashMap<>();
        Position boardGrid = new Position(3, 3);


        var button = gui.getSquares()[boardGrid.x()][boardGrid.y()];
        button.setRolloverEnabled(true);

        var piece = new Bishop(boardGrid, Color.WHITE);




        button.getModel().addChangeListener(new ChangeListener() {
            boolean needsRead = true;

            @Override
            public void stateChanged(ChangeEvent e) {

                if (needsRead) {
                    for (Position position : piece.getValidMoves()) {
                        //read and store old color
                        colors.put(position, gui.getSquares()[position.x()][position.y()].getBackground());

                    }
                }


                ButtonModel model = (ButtonModel) e.getSource();
                if (!model.isRollover()) {
                    logger.log(Level.INFO, "removing visual movement indication");
                    for (Position position : piece.getValidMoves()) {
                        //replace highlight with normal color
                        gui.getSquares()[position.x()][position.y()].setBackground(colors.get(position));
                    }
                }

                if (model.isRollover()) {
                    needsRead = false;
                    logger.log(Level.INFO, "processing visual movement indication");
                    for (Position position : piece.getValidMoves()) {
                        gui.getSquares()[position.x()][position.y()].setBackground(Color.CYAN);

                    }
                }


            }
        });*/


    }


}
