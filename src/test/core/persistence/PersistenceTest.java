package core.persistence;

import core.Configuration;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import persistence.ProfileRepo;
import persistence.repos.FileRepo;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


public class PersistenceTest {

    private ProfileRepo profileRepo;
    private FileRepo filerepo;

    @BeforeEach
    void setup() {
        profileRepo = ProfileRepo.getInstance();
        filerepo = FileRepo.getInstance();
    }

    @Test
    void doesExist() {
        assertNotNull(profileRepo);
        assertNotNull(filerepo);
    }

    @Test
    void canProcessFolder() {
        String toCompare = System.getProperty("user.home");
        Assertions.assertEquals("", Configuration.userConfigLocation);
        assertNotEquals(toCompare, Configuration.userConfigLocation);
    }
}