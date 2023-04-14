package persistence;

import core.Configuration;
import persistence.DTO.ProfileDto;
import persistence.interfaces.ProfileInterface;
import persistence.repos.FileRepo;

import java.util.logging.Logger;

/**
 * Profile repository
 */
public class ProfileRepo implements ProfileInterface {

    private static final Logger logger = Logger.getLogger(FileRepo.class.getName());
    private static ProfileRepo instance;
    private final FileRepo fileRepo = FileRepo.getInstance();
    //private final DatabaseRepo databaseRepo = DatabaseRepo.getInstance();
    private ProfileInterface source;


    private ProfileRepo() {
        if (Configuration.useDataBaseRepo) {
            //todo -> Database Repo
            //FileRepo.destroy();
            logger.severe("Not yet implemented");
            //source=dataBaseRepo;
            logger.finest("Repo source set to database Repo");

        }
        if (Configuration.useFileRepo) {
            //DataBaseRepo.destroy();
            logger.finest("Repo source set to file Repo");
            source = fileRepo;
        }

    }

    public static ProfileRepo getInstance() {
        if (instance == null) instance = new ProfileRepo();
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
        logger.finest("reading profile from ProfileRepo");
        return source.readFromProfile();
    }

    @Override
    public boolean writeToProfile(ProfileDto dto) {
        logger.finest("writing to profile from ProfileRepo");
        return source.writeToProfile(dto);
    }
}