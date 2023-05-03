package core;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import persistence.Persistence;

import javax.swing.*;
import java.io.IOException;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import static java.lang.System.exit;

// Main core of the application
public class Core extends Application {

    private static final Logger logger = Logger.getLogger(Core.class.getName());
    private static Stage primaryStage;

    // Application start
    public static void main(String[] args) throws IOException {
        Persistence.getInstance();
        logger.info("-- Main start -- ");

        //read application.properties -> cli overwrite or pom default
        java.io.InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("application.properties");
        Properties props = new Properties();
        props.load(inputStream);

        try {
            Configuration.parseCommandLine(props);
        } catch (IndexOutOfBoundsException exception) {
            cliError();
        }

        //start GUI
        launch();
    }

    public static Stage getStage() {
        return primaryStage;
    }

    public static void setPrimaryStage(Stage stage) {
        primaryStage = stage;
    }

    /*
        // Constants of screens to select a screen
        private enum SCREENS {
            IN_GAME_SCREEN(() -> new InGameScreen(new Ava()) {

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
    */
    private static void cliError() {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("gui.translations.bundle", Locale.getDefault());
        JOptionPane.showMessageDialog(new JFrame(), resourceBundle.getString("cliError"), "Error",
                JOptionPane.ERROR_MESSAGE);
        logger.log(java.util.logging.Level.SEVERE, "Command line error has happened");
        exit(-1);
    }

    @Override
    public void start(Stage stage) throws Exception {

        //Locale locale_en_EN = new Locale("en", "EN");
        Locale currentLocale = Locale.getDefault();
        ResourceBundle resourceBundle = ResourceBundle.getBundle("translations/bundle", currentLocale);
        logger.info("Translation bundle loaded");


        /*PS use fxml files only for gui construction*/
        //LOADING FXML FILES
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/Pages/DashBoard.fxml"), resourceBundle);
        setPrimaryStage(stage);
        stage.setScene(new Scene(fxmlLoader.load()));
        //PROPERTIES
        //todo -> true
        stage.setResizable(false);
        //stage.initStyle(StageStyle.TRANSPARENT);
        stage.show();
        stage.setMaximized(false);
        //ARRANGING CENTER
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX((primScreenBounds.getWidth() - stage.getWidth()) / 2);
        stage.setY((primScreenBounds.getHeight() - stage.getHeight()) / 2);


    }


}
