package gui.inGameScreen;

import content.Label;
import content.Screen;
import core.pojo.Player;
import visual.Shapes;

import java.awt.*;

public class PlayersGUI extends Screen {

    private final Player player1, player2;

    public PlayersGUI(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;

        Font font = new Font("Comic Sans MS", Font.BOLD, 70);

        Label label1 = new Label(player1.getName(), Shapes.OVAL);
        label1.setBounds(640, 0, 800, 200);
        label1.setFont(font);
        add(label1);

        Label label2 = new Label(player2.getName(), Shapes.OVAL);
        label2.setBounds(640, 540, 800, 200);
        label2.setFont(font);
        add(label2);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(Color.decode("#d0d4cf"));
        g2d.fillRect(0, 0, getWidth(), getHeight());

        int imageSize = getHeight() / 2;
        g2d.drawImage(player2.getImage(), 0, 0, imageSize, imageSize, null);
        g2d.drawImage(player1.getImage(), 0, imageSize, imageSize, imageSize, null);

        g2d.setColor(Color.BLACK);
        g2d.drawLine(0, imageSize, getWidth(), imageSize);
    }
}
