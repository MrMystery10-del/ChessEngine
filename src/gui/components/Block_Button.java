package gui.components;

import javax.swing.*;
import java.awt.*;

public class Block_Button extends JButton {
    private int row;
    private int col;
    private Color color;

    public Block_Button(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public void setColor(Color color){
        this.color = color;
    }

    public Color getColor() {
        return color;
    }
}
