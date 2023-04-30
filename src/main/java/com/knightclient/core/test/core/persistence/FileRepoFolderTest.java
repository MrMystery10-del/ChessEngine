package com.knightclient.core.test.core.persistence;


import com.knightclient.core.Configuration;
import com.knightclient.core.persistence.DTO.ProfileDto;
import com.knightclient.core.persistence.repos.FileRepo;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.Assert.*;
import static org.testng.Assert.assertThrows;
import static org.testng.AssertJUnit.assertEquals;

public class FileRepoFolderTest {

    @Test
    void fileRepoCannotProcessWrongFolder() {
        FileRepo.destroy();
        String toCompare = System.getProperty("user.home") + "/chessEngine/";
        Assertions.assertEquals(toCompare, Configuration.userConfigLocation);
        toCompare = "invalid location";
        Configuration.userConfigLocation = toCompare;
        assertEquals(toCompare, Configuration.userConfigLocation);
        assertThrows(IllegalArgumentException.class, FileRepo::getInstance);
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