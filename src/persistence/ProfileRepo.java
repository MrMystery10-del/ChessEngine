package persistence;


import persistence.repos.FileRepo;

/** Profile repository
 * @author=Fa285634
 * @version=0.1
 *
 */
public class ProfileRepo {

    private static ProfileRepo instance;

    private FileRepo fileRepo;


    private ProfileRepo() {
        fileRepo=FileRepo.getInstance();
    }

    public static ProfileRepo getInstance() {
        if (instance == null) {
            instance = new ProfileRepo();
        }
        return instance;
    }




}
