package gui.inGameScreen;

import content.Screen;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * This class displays the board, pieces and highlighting
 */
public class Board extends Screen {

    private final BufferedImage boardImage;

    public Board() {
        try {
            boardImage = ImageIO.read(getClass().getResourceAsStream("/images/board.png"));
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;

        g2d.drawImage(boardImage, 0, 0, getWidth(), getHeight(), null);
    }
}
