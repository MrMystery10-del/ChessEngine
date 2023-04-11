package persistence.repos;

import core.Configuration;
import persistence.DTO.ProfileDto;
import persistence.interfaces.ProfileInterface;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.function.Supplier;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileRepo implements ProfileInterface {

    private static final Logger logger = Logger.getLogger(FileRepo.class.getName());
    private static FileRepo instance;
    private String userConfigLocation;


    //private constructor -> do NOT change access
    private FileRepo() {
        userConfigLocation = System.getProperty("user.home");
        if (!Configuration.userConfigLocation.isEmpty()) {
            //check if ends on forward slash
            if (Configuration.userConfigLocation.contains("/")) {
                if (!userConfigLocation.endsWith("/")) {
                    Configuration.userConfigLocation = Configuration.userConfigLocation + "/";
                }
                userConfigLocation = Configuration.userConfigLocation + Configuration.profileFileName;
                logger.info( "user configuration set to -> " + userConfigLocation);
            } else {
                logger.severe( "Invalid file path set");
                throw new IllegalArgumentException("User configuration path is not valid");
            }
        }
    }

    public static FileRepo getInstance() {
        if (instance == null) instance = new FileRepo();
        return instance;
    }

    /**
     * destroy the singleton ( use for testing )
     */
    public static void destroy() {
        instance = null;
    }

    @Override
    public Supplier<ProfileDto> readFromProfile() {
        //todo implement  ->  name, profile picture, id, email, elo, win rate, draw rate, achievements
        logger.info( "Not yet implemented yet -> FileRepo.readFromProfile()");
        return null;
    }

    @Override
    public boolean writeToProfile(ProfileDto dto) {
        //todo implement
        try {
            checkForProfileFile();
        } catch (IOException e) {
            logger.severe("Error on profile writing ->");
            logger.severe(e.getMessage());
        }
        logger.log(Level.INFO, "Not yet implemented yet -> FileRepo.writeToProfile()");
        return false;
    }

    /**
     * Detection and creation of the profile file
     * @throws IOException because things will fail
     */
    private void checkForProfileFile() throws IOException {
        Path location = Paths.get(userConfigLocation);
        if (Configuration.startWithNewProfile) {

            logger.info("CLI option activated to erase previous profile");

            if (Files.deleteIfExists(location)) {
                logger.log(Level.INFO, "Previous profile file erased");
            }
            logger.info("checking director of ->" + location.getParent());
            Files.createDirectories(location.getParent());
            Files.createFile(location);
        }

    }
}