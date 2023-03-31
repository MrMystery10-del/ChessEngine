package gui.controllers;

import bots.util.Move;
import bots.util.Util;
import core.pojo.Board;
import gui.components.Block_Button;
import gui.constants.Piece_info;
import gui.inGameScreen.BoardGui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PieceController implements ActionListener {

    private static final Logger logger = Logger.getLogger(Board.class.getName());

    private Block_Button button;
    private Piece_info info;
    private Board board;
    private BoardGui boardGui;

    public PieceController(Block_Button button, Piece_info info, Board board, BoardGui boardgui) {
        this.boardGui = boardgui;
        this.button = button;
        this.info = info;
        this.board = board;
    }

    public void actionPerformed(ActionEvent event) {
        logger.log(Level.INFO, "Clicked on a field");

        List<Move> legalMoves = Util.getPossibleMoves(board.getGameBoard(), button.col, button.row);
        for (int i = 0; i < legalMoves.size(); i++) {
            int row = legalMoves.get(i).toRow();
            int col = legalMoves.get(i).toCol();
            boardGui.squares[col][row].setBackground(Color.BLUE);
        }
    }
}
