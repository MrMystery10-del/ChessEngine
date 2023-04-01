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

        if (info.selected_button == null) {
            info.selected_button = button;
            for (int i = 0; i < legal.size(); i++) {
                int row = legal.get(i).toRow();
                int column = legal.get(i).toCol();
                info.highlighted_button.add(boardGui.squares[column][row]);
                info.button_colors.add(boardGui.squares[column][row].getBackground());
                boardGui.squares[column][row].setBackground(Color.BLUE);
            }
        } else if (info.highlighted_button.contains(button)) {
            int col = button.col;
            int row = button.row;
            int previous_value = board.gameBoard[col][row];
            int selectcol = info.selected_button.col;
            int selectrow = info.selected_button.row;
            board.gameBoard[col][row] = board.getGameBoard()[selectcol][selectrow];
            board.gameBoard[selectcol][selectrow] = (byte) previous_value;
            Icon icon = info.selected_button.getIcon();
            info.selected_button.setIcon(null);
            button.setIcon(icon);
            for (int i = 0; i < info.highlighted_button.size(); i++) {
                info.highlighted_button.get(i).setBackground(info.button_colors.get(i));
            }
            info.selected_button = null;
            info.highlighted_button = new ArrayList<>();
            info.button_colors = new ArrayList<>();
        } else if ((!info.highlighted_button.contains(button)) || board.getGameBoard()[button.col][button.row] == 0) {
            for (int i = 0; i < info.highlighted_button.size(); i++) {
                info.highlighted_button.get(i).setBackground(info.button_colors.get(i));
            }
            info.selected_button = null;
            info.highlighted_button = new ArrayList<>();
            info.button_colors = new ArrayList<>();
        }
    }
}
