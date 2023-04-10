package gui.controllers;

import bots.util.Move;
import bots.util.Util;
import core.pojo.Board;
import gui.components.Block_Button;
import gui.constants.PieceInfo;
import gui.inGameScreen.BoardGui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Controls piece on board, getting selected piece and moves
 */
public record PieceController(Block_Button button, Board board, BoardGui boardGui, PieceInfo info)
        implements ActionListener {

    public void actionPerformed(ActionEvent event) {
        if (info.getSelectedButton() == null) {
            //if selected button is null then it sets constant to the pressed button
            List<Move> possibleMoves = Util.getPossibleMoves(board.getGameBoard(), button.getCol(), button().getRow());
            info.setSelectedButton(button);
            List<Block_Button> highlightedButtons = new ArrayList<>();
            List<Color> colors = new ArrayList<>();

            for (int i = 0; i < possibleMoves.size(); i++) {
                int row = possibleMoves.get(i).toRow();
                int col = possibleMoves.get(i).toCol();
                colors.add(boardGui.getSquares()[col][row].getBackground());
                highlightedButtons.add(boardGui.getSquares()[col][row]);
                boardGui.getSquares()[col][row].setBackground(Color.BLUE);
            }

            info.setColorOfButtons(colors);
            info.setHighlightedButton(highlightedButtons);
        } else if (info.getHighlightedButton().contains(button)) {
            Icon icon = info.getSelectedButton().getIcon();
            byte piece = board.getGameBoard()[info.getSelectedButton().getCol()][info.getSelectedButton().getRow()];
            board.setValue(button.getRow(), button.getCol(), piece);
            board.setValue(info.getSelectedButton().getRow(), info.getSelectedButton().getCol(), (byte) 0);
            button.setIcon(icon);
            info.getSelectedButton().setIcon(null);
            info.setSelectedButton(null);
            for (int i = 0; i < info.getHighlightedButton().size(); i++) {
                info.getHighlightedButton().get(i).setBackground(info.getColorOfButtons().get(i));
            }
            info.setHighlightedButton(new ArrayList<>());
            info.setColorOfButtons(new ArrayList<>());
        } else {
            for (int i = 0; i < info.getHighlightedButton().size(); i++) {
                info.getHighlightedButton().get(i).setBackground(info.getColorOfButtons().get(i));
            }
            info.setSelectedButton(null);
            info.setHighlightedButton(new ArrayList<>());
            info.setColorOfButtons(new ArrayList<>());
        }
    }
}
