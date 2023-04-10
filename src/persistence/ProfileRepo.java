package persistence;

import persistence.repos.FileRepo;

/**
 * Profile repository
 */
public class ProfileRepo {

    private static ProfileRepo instance;

    private FileRepo fileRepo;

    private ProfileRepo() {
        fileRepo = FileRepo.getInstance();
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
}