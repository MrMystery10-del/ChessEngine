package gui.inGameScreen;

import content.Screen;

import java.awt.*;

public class PlayersGUI extends Screen {

    public PlayersGUI() {

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.decode("#d0d4cf"));
        g.fillRect(0, 0, getWidth(), getHeight());
    }
}
