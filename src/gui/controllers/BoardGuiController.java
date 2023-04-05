package gui.controllers;

import core.pojo.Board;
import core.pojo.pieces.Bishop;
import core.pojo.Position;
import gui.inGameScreen.BoardGui;
import gui.inGameScreen.GameStateGui;
import manage.ImageManager;
import manage.Pieces;

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
    }

    /**
     * update the chessboard gui with the details from the Piece board
     */
    public void updateBoard() {
        for (int j = 0; j < 8; j++)
            for (int i = 0; i < 8; i++) {
                var image = convertToPiece(board.getGameBoard())[i][j];
                var update = gui.getSquares()[i][j];
                if (image != null)
                    update.setIcon(new ImageIcon(ImageManager.getPiece(image)));
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

    private Pieces[][] convertToPiece(byte[][] board) {
        Pieces[][] convertedBoard = new Pieces[8][8];

        for (int x = 0; x < 8; x++)
            for (int y = 0; y < 8; y++)
                convertedBoard[x][y] = switch (board[x][y]) {
                    case 0 -> null;
                    case 1 -> Pieces.PAWN;
                    case 2 -> Pieces.KNIGHT;
                    case 3 -> Pieces.BISHOP;
                    case 4 -> Pieces.ROOK;
                    case 5 -> Pieces.QUEEN;
                    case 6 -> Pieces.KING;
                    case -1 -> Pieces.PAWN_BLACK;
                    case -2 -> Pieces.KNIGHT_BLACK;
                    case -3 -> Pieces.BISHOP_BLACK;
                    case -4 -> Pieces.ROOK_BLACK;
                    case -5 -> Pieces.QUEEN_BLACK;
                    case -6 -> Pieces.KING_BLACK;
                    default -> throw new IllegalStateException("Unexpected value: " + board[x][y]);
                };
        return convertedBoard;
    }
}
