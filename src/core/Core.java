package core;

import bots.low_bots_0_500.Bob;
import content.Frame;
import content.Screen;
import gui.InGameScreen;
import gui.MainMenu;
import gui.controllers.ProfileController;
import gui.profileScreen.ProfileMenu;
import manage.ImageManager;
import persistence.Persistence;

import javax.swing.*;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.function.Supplier;
import java.util.logging.Logger;

import static java.lang.System.exit;

// Main core of the application
public class Core {

    private static final Logger logger = Logger.getLogger(Core.class.getName());
    public final static Frame frame = new Frame("ChessEngine");

    // Application start
    public static void main(String[] args) throws IOException {

        try {
            Configuration.parseCommandLine(args);
        } catch (IndexOutOfBoundsException exception) {
            cliError();
        }


        ImageManager.loadImages();
        selectCurrentScreen(SCREENS.MAIN_MENU);

    }

    // Select current screen which will be displayed on the frame
    private static void selectCurrentScreen(SCREENS screens) {
        frame.setContentPane(screens.getContent().get());
    }

    // Constants of screens to select a screen
    private enum SCREENS {
        IN_GAME_SCREEN(() -> new InGameScreen(new Bob()) {

        }), MAIN_MENU(() -> new MainMenu() {

            @Override
            public void viewProfile() {
                startProfileMenu();
            }

            @Override
            public void startedMultiplayer() {
                selectCurrentScreen(SCREENS.IN_GAME_SCREEN);
            }

            @Override
            public void startedAI() {
                selectCurrentScreen(SCREENS.IN_GAME_SCREEN);
            }
        });


        final Supplier<Screen> content;

        SCREENS(Supplier<Screen> content) {
            this.content = content;
        }

        public Supplier<Screen> getContent() {
            return content;
        }
    }

    private static void cliError() {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("gui.translations.bundle", Locale.getDefault());
        JOptionPane.showMessageDialog(new JFrame(), resourceBundle.getString("cliError"), "Error",
                JOptionPane.ERROR_MESSAGE);
        logger.log(java.util.logging.Level.SEVERE, "Command line error has happened");
        exit(-1);
    }

    /**
     * Starts the profile menu and couples with the persistence layer
     */
    private static void startProfileMenu(){
        Persistence persistence=Persistence.getInstance();
        ProfileMenu gui = new ProfileMenu();
        new ProfileController(persistence,gui);
    }
}
