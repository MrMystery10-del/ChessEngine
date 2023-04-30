package com.knightclient.core.persistence.repos;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.knightclient.core.Configuration;
import com.knightclient.core.persistence.DTO.AchievementDto;
import com.knightclient.core.persistence.DTO.ProfileDto;
import com.knightclient.core.persistence.interfaces.ProfileInterface;

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
                logger.info("user configuration set to -> " + userConfigLocation);
                checkForProfileFile();
            } else {
                logger.severe("Invalid file path set");
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
    public ProfileDto readFromProfile() {
        checkForProfileFile();
        if(Configuration.startWithNewProfile){createNewProfile();}
        ProfileDto dto=new ProfileDto();
        ObjectMapper mapper = new ObjectMapper();
        try {
            dto=mapper.readValue( new File(userConfigLocation), ProfileDto.class);
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
         .addAchievement(new AchievementDto().setId(0).setTitle("new player"));
         writeToProfile(dto);

    }

    @Override
    public boolean writeToProfile(ProfileDto dto) {
        checkForProfileFile();

        ObjectMapper objectMapper = new ObjectMapper();

        try {
            objectMapper.writeValue(new File(userConfigLocation), dto);
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
        Path location = Paths.get(userConfigLocation);
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
        } catch (IOException e) {
            logger.severe("Error on profile file checking");
        }

    }
}