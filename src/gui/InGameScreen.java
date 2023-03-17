package gui;

import content.Screen;
import content.Style;
import content.Styles;

import java.awt.*;

/** Main screen for playing the chess game, displays the board and pieces */
public abstract class InGameScreen extends Screen {

    /** Construct the screen */
    public InGameScreen(){
        super(new Style(Styles.MEADOW));
    }

    // Root for painting the gui
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;

        // Here write code to draw the board image
    }
}
