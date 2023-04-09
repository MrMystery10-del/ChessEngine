package persistence.repos;

import persistence.DTO.ProfileDto;
import persistence.interfaces.ProfileInterface;

import java.util.function.Supplier;
import java.util.logging.Level;
import java.util.logging.Logger;


public class FileRepo implements ProfileInterface {

    private static final Logger logger = Logger.getLogger(FileRepo.class.getName());
    private static FileRepo instance;
    private FileRepo() {}

    public static FileRepo getInstance() {
        if (instance == null) {
            instance = new FileRepo();
        }
        return instance;
    }


    @Override
    public Supplier<ProfileDto> readFromProfile() {
        //todo implement
        logger.log(Level.INFO,"Not yet implemented yet -> FileRepo.readFromProfile()");
        return null;
    }

    @Override
    public boolean writeToProfile(ProfileDto dto) {
        //todo implement
        logger.log(Level.INFO,"Not yet implemented yet -> FileRepo.writeToProfile()");
        return false;
    }
}
