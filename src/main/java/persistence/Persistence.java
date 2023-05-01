package persistence;

import persistence.DTO.ProfileDto;
import persistence.interfaces.ProfileInterface;

import java.util.logging.Logger;


//general persistence manager,  profile, saving/loading game towards different repo's
public class Persistence implements ProfileInterface {
    private static final Logger logger = Logger.getLogger(Persistence.class.getName());
    private static Persistence instance;
    private final ProfileRepo profileRepo = ProfileRepo.getInstance();

    //private constructor
    private Persistence() {
    }

    public static Persistence getInstance() {
        if (instance == null) instance = new Persistence();
        return instance;
    }

    @Override
    public ProfileDto readFromProfile() {
        logger.finest("reading profile from " + this.getClass().getSimpleName());
        return profileRepo.readFromProfile();
    }

    @Override
    public boolean writeToProfile(ProfileDto dto) {
        logger.finest("writing to profile from " + this.getClass().getSimpleName());
        return profileRepo.writeToProfile(dto);
    }
}
