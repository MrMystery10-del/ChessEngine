package gui.controllers;

import bots.util.Move;
import bots.util.Util;
import core.pojo.Board;
import gui.components.Block_Button;
import gui.constants.Piece_info;
import gui.inGameScreen.BoardGui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class PieceController implements ActionListener {

    Block_Button button;

    Piece_info info;

    Board board;

    BoardGui boardGui;

    public PieceController(Block_Button button, Piece_info info, Board board, BoardGui boardgui) {
        this.boardGui = boardgui;
        this.button = button;
        this.info = info;
        this.board = board;
    }

    public void actionPerformed(ActionEvent e) {
        List<Move> legal = Util.getPossibleMoves(board.getGameBoard(), button.col, button.row);

        if (info.getSelected_button() == null) {
            info.setSelected_button(button);
            for (int i = 0; i < legal.size(); i++) {
                int row = legal.get(i).toRow();
                int column = legal.get(i).toCol();
                info.getHighlighted_button().add(boardGui.squares[column][row]);
                info.getButton_colors().add(boardGui.squares[column][row].getBackground());
                boardGui.squares[column][row].setBackground(Color.BLUE);
            }
        } else if (info.getHighlighted_button().contains(button)) {
            int col = button.col;
            int row = button.row;
            int previousValue = board.getGameBoard()[col][row];
            int selectCol = info.getSelected_button().col;
            int selectRow = info.getSelected_button().row;
            board.setGameBoardPiece(row,col,board.getGameBoard()[selectCol][selectRow]);
            board.setGameBoardPiece(selectRow,selectCol,(byte) previousValue);
            Icon icon = info.getSelected_button().getIcon();
            info.getSelected_button().setIcon(null);
            button.setIcon(icon);
            for (int i = 0; i < info.getHighlighted_button().size(); i++) {
                info.getHighlighted_button().get(i).setBackground(info.getButton_colors().get(i));
            }
            info.setSelected_button(null);
            info.setHighlighted_button(new ArrayList<>());
            info.setButton_colors(new ArrayList<>());
        } else if ((!info.getHighlighted_button().contains(button)) || board.getGameBoard()[button.col][button.row] == 0) {
            for (int i = 0; i < info.getHighlighted_button().size(); i++) {
                info.getHighlighted_button().get(i).setBackground(info.getButton_colors().get(i));
            }
            info.setSelected_button(null);
            info.setHighlighted_button(new ArrayList<>());
            info.setButton_colors(new ArrayList<>());
        }
    }
}
