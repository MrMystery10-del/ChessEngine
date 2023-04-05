package gui.controllers;

import bots.util.Move;
import bots.util.Util;
import core.pojo.Board;
import gui.components.Block_Button;
import gui.inGameScreen.BoardGui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public record PieceController(Block_Button button, Board board, BoardGui boardGui) implements ActionListener {

    public void actionPerformed(ActionEvent event) {
        if(board.getGameBoard()[button.getCol()][button.getRow()] == 0)
            return;

        List<Move> possibleMoves = Util.getPossibleMoves(board.getGameBoard(), button.getCol(), button().getRow());

        boardGui.setSelected(button.getRow(), button.getCol(), possibleMoves.toArray(new Move[0]));
    }
}
