package gui;

import content.Button;
import content.Screen;

import java.util.Locale;
import java.util.ResourceBundle;

public abstract class MainMenu extends Screen {

    /** Set up a new Screen with buttons */
    public MainMenu() {

        Locale locale_en_EN = new Locale("en", "EN");
        ResourceBundle resourceBundle = ResourceBundle.getBundle("gui.translations.bundle", locale_en_EN);

        Button multiplayer = new Button(resourceBundle.getString("multiplayer"));
        multiplayer.setBounds(460, 200, 1000, 200);
        multiplayer.addActionListener(event -> startedMultiplayer());
        add(multiplayer);

        Button ai = new Button(resourceBundle.getString("vsAi"));
        ai.setBounds(460, 600, 1000, 200);
        ai.addActionListener(event -> startedAI());
        add(ai);
    }

    /** This method gets executed when clicked on multiplayer button*/
    public abstract void startedMultiplayer();

    /** This method gets executed when clicked on against AI button*/
    public abstract void startedAI();
}
