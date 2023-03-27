package gui;

import content.*;
import manage.ImageManager;

import javax.swing.*;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * This class represents the main menu on application start
 */
public abstract class MainMenu extends Screen {

    /**
     * Set up a new Screen with buttons
     */
    public MainMenu() {
        super(new Style(Styles.MODERN));

        Locale locale_en_EN = new Locale("en", "EN");
        ResourceBundle resourceBundle = ResourceBundle.getBundle("gui.translations.bundle", locale_en_EN);

        Button profile = new Button(resourceBundle.getString("profile"));
        profile.setBounds(0, 0, 300, 100);
        profile.addActionListener(event -> viewProfile());
        add(profile, 2);

        Label profilePicture = new Label();
        profilePicture.setBounds(300, -1, 102, 102);
        profilePicture.setIcon(new ImageIcon(ImageManager.getDefaultPP()));
        add(profilePicture, 2);

        Button multiplayer = new Button(resourceBundle.getString("multiplayer"));
        multiplayer.setBounds(460, 200, 1000, 200);
        multiplayer.addActionListener(event -> startedMultiplayer());
        add(multiplayer, 1);

        Button ai = new Button(resourceBundle.getString("vsAi"));
        ai.setBounds(460, 600, 1000, 200);
        ai.addActionListener(event -> startedAI());
        add(ai, 1);
    }

    /**
     * This method gets executed when clicked on profile button
     */
    public abstract void viewProfile();

    /**
     * This method gets executed when clicked on multiplayer button
     */
    public abstract void startedMultiplayer();

    /**
     * This method gets executed when clicked on against AI button
     */
    public abstract void startedAI();
}
