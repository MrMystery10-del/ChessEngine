package persistence.repos;

import com.fasterxml.jackson.databind.ObjectMapper;
import core.Configuration;
import persistence.DTO.AchievementDto;
import persistence.DTO.ProfileDto;
import persistence.interfaces.ProfileInterface;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileRepo implements ProfileInterface {

    private static final Logger logger = Logger.getLogger(FileRepo.class.getName());
    private static FileRepo instance;


    // private constructor -> do NOT change access
    private FileRepo() {
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
    public ProfileDto readFromProfile() {

        checkForProfileFile();
        if (Configuration.startWithNewProfile) {
            createNewProfile();
        }

        ProfileDto dto = new ProfileDto();
        ObjectMapper mapper = new ObjectMapper();

        try {
            dto = mapper.readValue(new File(Configuration.userConfigLocation + Configuration.profileFileName), ProfileDto.class);
        } catch (IOException e) {
            logger.severe("Failure while reading from profile");
            logger.severe(e.getMessage());
        }
        return dto;
    }

    /**
     * Create new profile with default data
     */
    private void createNewProfile() {
        ProfileDto dto = new ProfileDto();
        dto.setUserName("new user")
                .setUserId(0)
                .setElo(0)
                .setDraw(0)
                .setEmailAddress("none@kn.own")
                .setLosses(0)
                .addAchievement(new AchievementDto().setId(0).setTitle("new player"))
                .setImage("not set yet")
                .setVersion(0);


        writeToProfile(dto);
    }

    @Override
    public boolean writeToProfile(ProfileDto dto) {
        checkForProfileFile();
        ObjectMapper objectMapper = new ObjectMapper();

        try {

            objectMapper.writeValue(new File(Configuration.userConfigLocation + Configuration.profileFileName), dto);
        } catch (IOException e) {
            logger.severe("Failure to write to profile file");
            logger.severe(e.getMessage());
            return false;
        }

        //logger.log(Level.INFO, "Not yet implemented yet -> FileRepo.writeToProfile()");
        return true;
    }

    /**
     * Detection and creation of the profile file
     */
    private void checkForProfileFile() {

        Path location = Paths.get(Configuration.userConfigLocation + "/" + Configuration.profileFileName);
        try {
            if (Configuration.startWithNewProfile) {
                logger.info("CLI option activated to erase previous profile");

                if (Files.deleteIfExists(location)) {
                    logger.log(Level.INFO, "Previous profile file erased");
                }

                logger.info("checking directory of ->" + location.getParent());
                Files.createDirectories(location.getParent());
                Files.createFile(location);
            }
            if (!Files.exists(Path.of(Configuration.userConfigLocation + Configuration.profileFileName))) {
                logger.info("No configuration file found -> creating");
                createNewProfile();
            }
        } catch (IOException e) {
            logger.severe("Error on profile file checking");
        }
    }
}