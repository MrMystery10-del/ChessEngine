package gui.inGameScreen;

import content.Screen;

import java.awt.*;

/**
 * This GUI part displays the time left and the taken pieces
 */
public class StateGUI extends Screen {

    public StateGUI() {

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.decode("#a1b0a0"));
        g.fillRect(0, 0, getWidth(), getHeight());
    }
}
