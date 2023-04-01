package core;

import bots.low_bots_0_500.Bob;
import content.Frame;
import content.Screen;
import gui.AIProfilePopup;
import gui.InGameScreen;
import gui.MainMenu;
import gui.ProfileMenu;
import manage.ImageManager;

import java.io.IOException;
import java.util.function.Supplier;

// Main core of the application
public class Core {

    public final static Frame frame = new Frame("ChessEngine");

    // Application start
    public static void main(String[] args) throws IOException {
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
                selectCurrentScreen(SCREENS.PROFILE_MENU);
            }

            @Override
            public void startedMultiplayer() {
                selectCurrentScreen(SCREENS.IN_GAME_SCREEN);
            }

            @Override
            public void startedAI() {
                selectCurrentScreen(SCREENS.IN_GAME_SCREEN);
            }
        }), PROFILE_MENU(() -> new ProfileMenu() {

        });

        final Supplier<Screen> content;

        SCREENS(Supplier<Screen> content) {
            this.content = content;
        }

        public Supplier<Screen> getContent() {
            return content;
        }
    }
}
