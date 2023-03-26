package gui.controllers;

import core.pojo.Board;
import gui.inGameScreen.BoardGui;

public class BoardGuiController {

    private Board board;
    private BoardGui gui;

    byte[][] Board;
    public BoardGuiController(Board board, BoardGui gui) {
        this.board = board;
        this.gui = gui;
        Board = board.getGameBoard();
        Draw();
    }

    public void Draw()
    {
        for(int i = 0;i<8;i++)
        {
            for(int j = 0;j<8;j++)
            {
                board[i][j]=
            }
        }
    }

}
