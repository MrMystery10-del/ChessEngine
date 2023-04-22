package gui.controllers;

import bots.Bot;
import bots.util.Move;
import bots.util.Util;
import core.pojo.Board;
import gui.components.Block_Button;
import gui.constants.MoveCounter;
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

public record PieceController(Block_Button button, Board board, BoardGui boardGui,
                              PieceInfo info, MoveCounter counter, Bot bot) implements ActionListener {


    //@Checks if the value is negative or positive
    public boolean getSide(byte i) {
        boolean side = false;
        if (i > 0) {
            side = true;
        }
        return side;
    }

    public void resetValues() {
        //returns original value in info
        info.setHighlightedButton(new ArrayList<>());
        info.setColorOfButtons(new ArrayList<>());
    }

    public void requestBotsMove() {
        //gets bots move
        Move move = bot.playNewMove(board.getGameBoard(), false, boardGui);

        //gets icon of from move position

        Icon icon2 = boardGui.getSquares()[move.fromCol()][move.fromRow()].getIcon();

        board.updateBoard(Util.applyMove(board.getGameBoard(), move));
        //updates move

        //sets icon
        boardGui.getSquares()[move.toCol()][move.toRow()].setIcon(icon2);
        boardGui.getSquares()[move.fromCol()][move.fromRow()].setIcon(null);

        // checks to see if the piece moved was a king, and if the move was a "castle"
        if(board.getGameBoard()[move.toCol()][move.toRow()] == Math.abs(6) &&
                                                Math.abs(move.fromCol() - move.toCol()) == 2){
            int sign = -1;
            // Queen side castle
            if(move.toCol() == 2){
                Icon icon1 = boardGui.getSquares()[0][move.fromRow()].getIcon();

                boardGui.getSquares()[3][move.fromRow()].setIcon(icon1);
                boardGui.getSquares()[0][move.fromRow()].setIcon(null);

                board.setValue(3, move.fromRow(), (byte)(4*sign));
                board.setValue(0, move.fromRow(), (byte)0);
            // King side castle
            } else if (move.toCol() == 6) {
                Icon icon1 = boardGui.getSquares()[7][move.toRow()].getIcon();

                boardGui.getSquares()[5][move.fromRow()].setIcon(icon1);
                boardGui.getSquares()[7][move.fromRow()].setIcon(null);

                board.setValue(5, move.fromRow(), (byte)(4*sign));
                board.setValue(7, move.fromRow(), (byte)0);
            }
        }
    }

    public void highlightButtons(List<Move> possibleMoves) {
        List<Block_Button> highlightedButtons = new ArrayList<>();
        List<Color> colors = new ArrayList<>();

        //check all the possible moves adds them to info.highlightedButtons and highlights them in color
        /*to be able to revert them to original color we are also storing original colors in info.colors*/
        for (int i = 0; i < possibleMoves.size(); i++) {
            int row = possibleMoves.get(i).toRow();
            int col = possibleMoves.get(i).toCol();
            colors.add(boardGui.getSquares()[col][row].getBackground());
            highlightedButtons.add(boardGui.getSquares()[col][row]);
            boardGui.getSquares()[col][row].setBackground(Color.BLUE);
        }

        //then just sets the values to info's variables
        info.setColorOfButtons(colors);
        info.setHighlightedButton(highlightedButtons);
    }

    public void playMove() {
        Icon icon = info.getSelectedButton().getIcon();//gets icon of selected button's piece
        //gets the value in the gameBoard in board class and gets value of piece with buttons row and col values
        byte piece = board.getGameBoard()[info.getSelectedButton().getCol()][info.getSelectedButton().getRow()];
        //then it replaces the values in game board ,the position to move is set to 'piece'
        board.setValue(button.getRow(), button.getCol(), piece);
        //and the from position is set to 0
        board.setValue(info.getSelectedButton().getRow(), info.getSelectedButton().getCol(), (byte) 0);
        // Checks if the piece being moved is a king and if the move is a "castle"
        if(piece == 6 && Math.abs(button.getRow() - info.getSelectedButton().getRow()) == 2){
            Move newMove = new Move(info.getSelectedButton().getCol(), info.getSelectedButton().getRow(),
                    button.getCol(), button.getRow(), (byte)0);
            int sign = 1;

            // Queen side castle
            if(newMove.toCol() == 2){
                Icon icon1 = boardGui.getSquares()[0][newMove.toRow()].getIcon();

                boardGui.getSquares()[3][newMove.fromRow()].setIcon(icon1);
                boardGui.getSquares()[0][newMove.fromRow()].setIcon(null);

                board.setValue(3, newMove.fromRow(), (byte)(4*sign));
                board.setValue(0, newMove.fromRow(), (byte)0);
                // King side castle
            } else if (newMove.toCol() == 6) {
                Icon icon1 = boardGui.getSquares()[7][newMove.fromRow()].getIcon();

                boardGui.getSquares()[5][newMove.fromRow()].setIcon(icon1);
                boardGui.getSquares()[7][newMove.fromRow()].setIcon(null);

                board.setValue(5, newMove.fromRow(), (byte)(4*sign));
                board.setValue(7, newMove.fromRow(), (byte)0);
            }
        }
        //it sets 'to position' button's icon to the 'from position' button's icon
        button.setIcon(icon);
        //it then sets 'from position' button's which is stored in 'info' icon to null
        info.getSelectedButton().setIcon(null);
        //since move has been played it reverts the variable values in info
        info.setSelectedButton(null);
        //returns color of highlighted buttons from blue to original color stored in info.Colors
        for (int i = 0; i < info.getHighlightedButton().size(); i++) {
            info.getHighlightedButton().get(i).setBackground(info.getColorOfButtons().get(i));
        }
    }

    public void returnOriginalColor() {
        //returns highlighted buttons background to original color from the color list in PieceInfo
        for (int i = 0; i < info.getHighlightedButton().size(); i++) {
            info.getHighlightedButton().get(i).setBackground(info.getColorOfButtons().get(i));
        }
    }

    //true = black false = white
    public void actionPerformed(ActionEvent event) {
        //check if the button in grid's row and col value present in the board's game board is equal to players side
        if (getSide(board.getGameBoard()[button().getCol()][button().getRow()]) == board.getSide() && counter().isTurn() == board().getSide() || info.containsObj(button)) {
            if (info.getSelectedButton() == null) {
                //if selected button in constants.info is null then it sets constant to the pressed button
                List<Move> possibleMoves = Util.getPossibleMoves(board.getGameBoard(), button.getCol(), button().getRow());
                info.setSelectedButton(button);
                highlightButtons(possibleMoves);
            } else if (info.getHighlightedButton().contains(button)) {
                playMove();
                resetValues();
                counter.setTurn(!counter.isTurn());
                requestBotsMove();
                counter.setTurn(!counter.isTurn());
            } else {
                returnOriginalColor();
                //sets all variables to null so that another button can be pressed
                info.setSelectedButton(null);
                resetValues();
            }
        }
    }
}
