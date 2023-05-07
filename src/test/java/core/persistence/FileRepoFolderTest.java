package core.persistence;

import core.Configuration;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import persistence.DTO.ProfileDto;
import persistence.repos.FileRepo;

import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

public class FileRepoFolderTest {

    @Test
    void fileRepoCannotProcessWrongFolder() {
        FileRepo.destroy();
        String toCompare = System.getProperty("user.home") + "/chessEngine";
        Assertions.assertEquals(toCompare, Configuration.userConfigLocation);
        toCompare = "invalid location";
        Configuration.userConfigLocation = toCompare;
        assertEquals(toCompare, Configuration.userConfigLocation);
        Configuration.userConfigLocation = "";
        FileRepo.getInstance();
    }

    @Test
    void fileRepoCanProcessFolder() {
        FileRepo.destroy();
        Configuration.userConfigLocation = "/chessEngine";
        FileRepo.getInstance();
        //reset for other test;
        FileRepo.destroy();
        Configuration.userConfigLocation = "";
        FileRepo.getInstance();
    }

    @Test
    void shouldHaveFileInDefaultLocation() throws Exception {
        FileRepo.destroy();
        Configuration.startWithNewProfile = true;
        Configuration.userConfigLocation = System.getProperty("user.home") + "/chessEngine";
        FileRepo repo = FileRepo.getInstance();
        assertNotNull(repo);
        Path location = Path.of(Configuration.userConfigLocation + "/" + Configuration.profileFileName);
        Files.deleteIfExists(location);
        assertFalse(Files.exists(location));
        assertTrue(repo.writeToProfile(new ProfileDto()));
        assertTrue(Files.exists(location));

    }
}