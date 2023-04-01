package gui;

import bots.Bot;
import content.Button;
import content.Label;
import content.*;
import core.Core;
import visual.Shapes;

import javax.swing.*;
import java.awt.*;

public abstract class AIProfilePopup extends JDialog {

    private final Bot bot;

    /**
     * Constructs a new Popup for viewing the bots profile
     */
    public AIProfilePopup(Bot bot, int width, int height) {
        super(Core.frame, bot.getName(), false);
        this.bot = bot;

        setSize(width, height);
        setLocationRelativeTo(null);

        setContentPane(getContent());
    }

    /**
     * @return content inside the JDialog to display the bot profile in a nice looking way
     */
    private Screen getContent() {
        Screen content = new Screen(new Style(Styles.ROMAN));

        Label picture = new Label();
        picture.setIcon(new ImageIcon(bot.getPicture()));
        picture.setBounds(100, 100, 500, 300);

        Label name = new Label();
        name.setText(bot.getName());
        name.setFont(new Font("Arial", Font.BOLD, 70));
        name.setBounds(700, 100, 1000, 200);

        Label elo = new Label();
        elo.setText("" + bot.getElo());
        elo.setFont(new Font(Font.MONOSPACED, Font.BOLD, 100));
        elo.setBounds(700, 300, 1000, 200);

        Label algo = new Label();
        algo.setText("Used algorithm: " + bot.getUsedAlgorithm());
        algo.setFont(new Font("Arial", Font.BOLD, 40));
        algo.setBounds(700, 500, 1000, 200);

        Button startGame = new Button("Play against " + bot.getName(), Shapes.HEXAGON);
        startGame.setFont(new Font("Arial", Font.BOLD, 60));
        startGame.setBounds(650, 800, 1100, 200);

        Label description = new Label();
        description.setText(bot.getDescription());
        description.setFont(new Font("Arial", Font.BOLD, 50));
        description.setBounds(100, 500, 500, 500);

        content.add(picture, 1);
        content.add(name, 1);
        content.add(elo, 2);
        content.add(algo, 1);
        content.add(description, 1);
        content.add(startGame);

        return content;
    }
}
