package core.persistence;

import core.Configuration;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import persistence.repos.FileRepo;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FileRepoWithWrongFolderTest {

    @Test
    void fileRepoCannotProcessWrongFolder() {
        FileRepo.destroy();
        String toCompare = System.getProperty("user.home");
        Assertions.assertNotEquals(toCompare, Configuration.userConfigLocation);
        toCompare = "invalid location";
        Configuration.userConfigLocation = toCompare;
        assertEquals(toCompare, Configuration.userConfigLocation);
        Configuration.userConfigLocation = "";
        FileRepo.getInstance();
    }
}