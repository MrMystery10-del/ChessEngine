package com.knightclient.core.test.core.persistence;

import com.knightclient.core.Configuration;
import com.knightclient.core.persistence.ProfileRepo;
import com.knightclient.core.persistence.repos.FileRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class PersistenceTest {

    private ProfileRepo profileRepo;
    private FileRepo fileRepo;

    @BeforeEach
    void setup() {
        profileRepo = ProfileRepo.getInstance();
        fileRepo = FileRepo.getInstance();
    }

    @Test
    void doesExist() {
        assertNotNull(profileRepo);
        assertNotNull(fileRepo);
        ProfileRepo.destroy();
        FileRepo.destroy();
        assertNotEquals(profileRepo, ProfileRepo.getInstance());
        assertNotEquals(fileRepo, FileRepo.getInstance());


    }

    @Test
    void canProcessFolder() {
        String toCompare = System.getProperty("user.home");
        assertEquals("", Configuration.userConfigLocation);
        assertNotEquals(toCompare, Configuration.userConfigLocation);
    }

    @Test
    void shouldBeSingletonsUnlessSomeOneMessesWithTheCode() {
        assertEquals(ProfileRepo.getInstance(), profileRepo);
        assertEquals(FileRepo.getInstance(), fileRepo);
    }
}