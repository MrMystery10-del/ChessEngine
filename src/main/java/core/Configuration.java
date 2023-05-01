package core;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Configuration {

    private static final Logger logger = Logger.getLogger(Configuration.class.getName());

    //client configurations
    public static boolean startWithNewProfile = false;
    public static String userConfigLocation = System.getProperty("user.home") + "/chessEngine";
    public static String profileFileName = "user.profile";

    //configuration toggle DB / File / Remote ?
    public static boolean useFileRepo = true;
    public static boolean useDataBaseRepo = false;


    /**
     * Processes commandline arguments
     *
     * @param commandLineOptions list of arguments places on commandline
     */
    public static void parseCommandLine(Properties commandLineOptions) throws IndexOutOfBoundsException {


        Map<String, String> commands = new HashMap<>();
        commandLineOptions.forEach((key, value) -> commands.put(key.toString(), value.toString()));
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
                    startWithNewProfile = true;
                    logger.info("new profile option given on command line");

                }
                case "profileDirectory" -> {
                    userConfigLocation = value;
                    logger.info("profile directory set on command line -> " + userConfigLocation);

                }
                case "profileFileName" -> {
                    //check on valid file name
                    var checker = "(?<seq>[a-zA-Z0-9_\\-\\(\\):])+(\\.+)+([a-zA-Z]{3,})";
                    if (value.matches(checker)) {
                        profileFileName = value;
                        logger.info("profile file name set to -> " + value);
                    } else {
                        logger.log(Level.SEVERE, "error in profile file name");
                    }

                }
                case "useDataBaseRepo" -> {
                    if (value.equals("true")) {
                        useDataBaseRepo = true;
                        useFileRepo = false;
                        logger.info("CLI switch activated to use database repo");
                    }

                }
                case "useFileRepo" -> {
                    if (value.equals("true")) {
                        useDataBaseRepo = false;
                        useFileRepo = true;
                        logger.info("CLI switch activated to file repo");
                    }
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
