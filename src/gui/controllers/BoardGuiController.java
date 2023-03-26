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

    String[] Address = new String[12];//array for storing addresses of pieces
    ImageIcon[] White = new ImageIcon[6];//Stores resized images of white
    ImageIcon[] Black = new ImageIcon[6];//Stores resized images of black

    private void setAddress()//easier way to get location of all images
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

    //converts to Image , resizes it and returns btw 25 is customizable ,but 25 gives best result
    private static ImageIcon resizeIcon(ImageIcon icon, int resizedWidth, int resizedHeight) {
        Image img = icon.getImage();
        Image resizedImage = img.getScaledInstance(resizedWidth-25, resizedHeight-25,  java.awt.Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImage);
    }

    /*gets the file converts to bufferedImage resizes it and then returns ad Image Icon*/
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

    //set resized images to the white array
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

    //set resized images to the black array
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
        //initiates call of functions
        setAddress();
        setBlack();
        setWhite();
        SetPieces();
    }


    //Just sets Buttons Icon images
    public void SetPieces()
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
