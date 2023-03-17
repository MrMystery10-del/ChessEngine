package core;

import content.Frame;
import content.Screen;
import gui.InGameScreen;

import java.util.function.Supplier;

public class Core {

    private final static Frame frame = new Frame("ChessEngine");

    // Application start
    public static void main(String[] args) {
        selectCurrentScreen(SCREENS.IN_GAME_SCREEN);
    }

    // Select current screen which will be displayed on the frame
    private static void selectCurrentScreen(SCREENS screens) {
        frame.setContentPane(screens.getContent().get());
    }

    // Constants of screens to select a screen
    private enum SCREENS {
        IN_GAME_SCREEN(() -> new InGameScreen() {

        });

        final Supplier<Screen> content;

        public Supplier<Screen> getContent() {
            return content;
        }

        SCREENS(Supplier<Screen> content) {
            this.content = content;
        }
    }
}
