package core.constants;

import gui.controllers.BoardGuiController;

import java.util.Arrays;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.util.stream.Collectors.toMap;

public class Configuration {

    private static final Logger logger = Logger.getLogger(BoardGuiController.class.getName());
    public static boolean startWithNewProfile = false;
    public static String userConfigLocation = System.getProperty("user.home") + "/temp/chessEngine";

    /**
     * Processes commandline arguments
     *
     * @param commandLineOptions list of arguments places on commandline
     */
    public static void parseCommandLine(String[] commandLineOptions) throws IndexOutOfBoundsException {
        // replace \ with /
        // remove -*/ as initial letter,
        // add a secondary string if non-present
        // split to map

        Map<String, String> commands = Arrays.stream(commandLineOptions)
                .map(a -> a.replaceAll("\\\\", "/"))
                .map(a -> a.replaceAll("^[/*-]", ""))
                .map(a -> a.contains("=") ? a : a + "= ")
                .map(str -> str.split("="))
                .collect(toMap(str -> str[0], str -> str[1]));

        processCommands(commands);
    }

    /**
     * process the formatted list of arguments
     *
     * @param commands list of command line arguments
     */
    private static void processCommands(Map<String, String> commands) {
        commands.forEach((key, value) -> {
            switch (key) {
                case "newProfile" -> {
                    logger.info("new profile option given on command line");
                    startWithNewProfile = true;
                }
                case "profileDir" -> {
                    logger.info("profile directory set on command line -> " + value);
                    userConfigLocation = value;
                }
                case "error" -> {
                    String error = "Error in command line switch";
                    System.out.println(error);
                    logger.log(Level.SEVERE, error);
                }
            }
        });
    }
}
