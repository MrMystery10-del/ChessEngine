package persistence.repos;

import core.Configuration;
import persistence.DTO.ProfileDto;
import persistence.interfaces.ProfileInterface;

import java.util.function.Supplier;
import java.util.logging.Level;
import java.util.logging.Logger;


public class FileRepo implements ProfileInterface {

    private static final Logger logger = Logger.getLogger(FileRepo.class.getName());
    private static FileRepo instance;
    private String userConfigLocation;

    //private constructor -> do NOT change access
    //checks the userConfigLocation for overrides and acts accordingly
    private FileRepo() {
        userConfigLocation=System.getProperty("user.home");
        if(!Configuration.userConfigLocation.isEmpty()){
            if(Configuration.userConfigLocation.contains("/")) {
                userConfigLocation = Configuration.userConfigLocation+Configuration.profileFileName;
                logger.log(Level.INFO, "user configuration set to -> " + userConfigLocation);
            }
            else
            {
                logger.log(Level.SEVERE, "Invalid file path set");
                throw new IllegalArgumentException("User configuration path is not valid");
            }
        }
    }

    public static FileRepo getInstance() {
        if (instance == null) {
            instance = new FileRepo();
        }
        return instance;

    }

    /**
     * destroy the singleton ( use for testing )
     */
    public static void destroy(){
        instance=null;
    }

    private void createConfigFile() {


    }
    
    

    

    @Override
    public Supplier<ProfileDto> readFromProfile() {
        //todo implement
        logger.log(Level.INFO,"Not yet implemented yet -> FileRepo.readFromProfile()");
        Supplier<ProfileDto> profileDtoSupplier = () -> new ProfileDto();

        return profileDtoSupplier;
    }

    @Override
    public boolean writeToProfile(ProfileDto dto) {
        //todo implement
        logger.log(Level.INFO,"Not yet implemented yet -> FileRepo.writeToProfile()");
        return false;
    }
}
