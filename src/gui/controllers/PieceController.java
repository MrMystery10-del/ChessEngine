package gui.controllers;

import bots.util.Move;
import bots.util.Util;
import core.pojo.Board;
import gui.Components.Block_Button;
import gui.Constants.Piece_info;
import gui.inGameScreen.BoardGui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class PieceController implements ActionListener
{

    Block_Button button;

    Piece_info info;

    Board board;

    BoardGui boardGui;
    public PieceController(Block_Button button, Piece_info info, Board board, BoardGui boardgui)
    {
        this.boardGui=boardgui;
        this.button = button;
        this.info=info;
        this.board = board;
    }
    public void actionPerformed(ActionEvent e)
    {
        List<Move> legal = Util.getPossibleMoves(board.getGameBoard(),button.col, button.row);
        System.out.println(legal);
        for(int i =0;i<legal.size();i++)
        {
            int row  = legal.get(i).toRow();
            int column = legal.get(i).toCol();
            boardGui.squares[column][row].setBackground(Color.BLUE);
        }
    }
}
