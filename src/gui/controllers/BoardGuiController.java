package gui.controllers;

import core.pojo.Board;
import gui.inGameScreen.BoardGui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;

public class BoardGuiController {

    private Board board;
    private BoardGui gui;
    byte[][] Board;
    JButton[][] buttons;

    String[] Address = new String[12];
    ImageIcon[] White = new ImageIcon[6];
    ImageIcon[] Black = new ImageIcon[6];

    private void setAddress()
    {
        String base = "/images/pieces/";
        String extension = ".png";
        String pieces[] = {"pawn","knight","bishop","rook","queen","king"};
        for(int i = 0;i< Address.length;i++)
        {
            if(i<6)
            {
                Address[i]=base+pieces[i]+extension;
            }
            else
            {
                Address[i]=base+pieces[i-6]+"_BLACK"+extension;
            }
        }
    }
    private static ImageIcon resizeIcon(ImageIcon icon, int resizedWidth, int resizedHeight) {
        Image img = icon.getImage();
        Image resizedImage = img.getScaledInstance(resizedWidth-25, resizedHeight-25,  java.awt.Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImage);
    }

    public ImageIcon getResized(String address)
    {
        ImageIcon icon= null;
        try
        {
            icon = resizeIcon(new ImageIcon(ImageIO.read(getClass().getResourceAsStream(address))),buttons[0][0].getHeight(),buttons[0][0].getHeight());
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        return icon;
    }
    private void setWhite()
    {
        for(int i = 1;i < White.length+1;i++)
        {
            switch (i) {
                case 1 -> White[i - 1] = getResized(Address[0]);
                case 2 -> White[i - 1] = getResized(Address[1]);
                case 3 -> White[i - 1] = getResized(Address[2]);
                case 4 -> White[i - 1] = getResized(Address[3]);
                case 5 -> White[i - 1] = getResized(Address[4]);
                case 6 -> White[i - 1] = getResized(Address[5]);
            }
        }
    }

    private void setBlack()
    {
        for(int i = 1;i < Black.length+1;i++)
        {
            switch (i) {
                case 1 -> Black[i - 1] = getResized(Address[6]);
                case 2 -> Black[i - 1] = getResized(Address[7]);
                case 3 -> Black[i - 1] = getResized(Address[8]);
                case 4 -> Black[i - 1] = getResized(Address[9]);
                case 5 -> Black[i - 1] = getResized(Address[10]);
                case 6 -> Black[i - 1] = getResized(Address[11]);
            }
        }
    }
    public BoardGuiController(Board board, BoardGui gui) {
        this.board = board;
        this.gui = gui;
        buttons = gui.squares;
        Board = board.getGameBoard();
        setAddress();
        setBlack();
        setWhite();
        Draw();
    }

    public void Draw()
    {
        for(int i = 0;i<8;i++)
        {
            for(int j = 0;j<8;j++)
            {
                if(Board[i][j]!=0)
                {
                    if(Board[i][j]>0)
                    {
                        switch (Board[i][j]) {
                            case (1) -> buttons[j][i].setIcon(White[0]);
                            case (2) -> buttons[j][i].setIcon(White[1]);
                            case (3) -> buttons[j][i].setIcon(White[2]);
                            case (4) -> buttons[j][i].setIcon(White[3]);
                            case (5) -> buttons[j][i].setIcon(White[4]);
                            case (6) -> buttons[j][i].setIcon(White[5]);
                        }
                    }
                    else
                    {
                        switch (Board[i][j]) {
                            case (-1) -> buttons[j][i].setIcon(Black[0]);
                            case (-2) -> buttons[j][i].setIcon(Black[1]);
                            case (-3) -> buttons[j][i].setIcon(Black[2]);
                            case (-4) -> buttons[j][i].setIcon(Black[3]);
                            case (-5) -> buttons[j][i].setIcon(Black[4]);
                            case (-6) -> buttons[j][i].setIcon(Black[5]);
                        }
                        }
                    }
                }
            }
        }
    }
