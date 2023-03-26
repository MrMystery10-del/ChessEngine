package core;

import bots.nooby.Nooby;
import content.Frame;
import content.Screen;
import gui.InGameScreen;
import gui.MainMenu;
import manage.ImageManager;

import java.io.IOException;
import java.util.Arrays;
import java.util.function.Supplier;

// Main core of the application
public class Core {

    private final static Frame frame = new Frame("ChessEngine");

    // Application start
    public static void main(String[] args) throws IOException {

        ImageManager.loadImages();
        selectCurrentScreen(SCREENS.MAIN_MENU);




    }




    private static void testing() {
        final byte[][] board = {
                {4, 2, 3, 5, 6, 3, 2, 4},
                {1, 1, 1, 1, 1, 1, 1, 1},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {-1, -1, -1, -1, -1, -1, -1, -1},
                {-4, -2, -3, -5, -6, -3, -2, -4}
        };
        Arrays.stream(Nooby.playNewMove(board, false))
                .forEach(arr -> {
                    for (byte b : arr) {
                        System.out.print(b + " ");
                    }
                    System.out.println(); // print a new line after each array
                });
    }

    // Select current screen which will be displayed on the frame
    private static void selectCurrentScreen(SCREENS screens) {
        frame.setContentPane(screens.getContent().get());
    }

    // Constants of screens to select a screen
    private enum SCREENS {
        IN_GAME_SCREEN(() -> new InGameScreen() {

        }),
        MAIN_MENU(() -> new MainMenu() {

            @Override
            public void startedMultiplayer() {
                selectCurrentScreen(SCREENS.IN_GAME_SCREEN); // Switch to InGameScreen as placeholder
            }

            @Override
            public void startedAI() {
                selectCurrentScreen(SCREENS.IN_GAME_SCREEN); // Switch to InGameScreen as placeholder
            }
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
