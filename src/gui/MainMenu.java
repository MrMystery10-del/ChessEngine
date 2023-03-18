package gui;

import content.Button;
import content.Screen;

public abstract class MainMenu extends Screen {

    /** Set up a new Screen with buttons */
    public MainMenu() {
            Button multiplayer = new Button("Multiplayer");
            multiplayer.setBounds(460, 200, 1000, 200);
            multiplayer.addActionListener(event -> startedMultiplayer());
            add(multiplayer);

            Button ai = new Button("Against AI");
        ai.setBounds(460, 600, 1000, 200);
        ai.addActionListener(event -> startedAI());
        add(ai);
    }

    /** This method gets executed when clicked on multiplayer button*/
    public abstract void startedMultiplayer();

    /** This method gets executed when clicked on against AI button*/
    public abstract void startedAI();
}
