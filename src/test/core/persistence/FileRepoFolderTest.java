package core.persistence;

import core.Configuration;
import core.TestValues;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import persistence.repos.FileRepo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FileRepoFolderTest {


    @Test
    void fileRepoCannotProcessWrongFolder() {
        FileRepo.destroy();
        String toCompare = System.getProperty("user.home");
        Assertions.assertNotEquals(toCompare, Configuration.userConfigLocation);
        toCompare = TestValues.STRING_ONE;
        Configuration.userConfigLocation = toCompare;
        assertEquals(toCompare, Configuration.userConfigLocation);
        assertThrows(IllegalArgumentException.class, () -> FileRepo.getInstance());
        Configuration.userConfigLocation = "";
        FileRepo.getInstance();
    }

    @Test
    void fileRepoCanProcessFolder(){
        FileRepo.destroy();
        Configuration.userConfigLocation="/temp";
        FileRepo.getInstance();
        //reset for other test;
        FileRepo.destroy();
        Configuration.userConfigLocation = "";
        FileRepo.getInstance();
    }


}
