package gui.controllers;

import core.pojo.Board;
import gui.inGameScreen.BoardGui;

public class BoardGuiController {

    private Board board;
    private BoardGui gui;

    public BoardGuiController(Board board, BoardGui gui) {
        this.board = board;
        this.gui = gui;
    }



}
