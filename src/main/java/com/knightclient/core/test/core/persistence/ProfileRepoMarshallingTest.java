package com.knightclient.core.test.core.persistence;

import com.knightclient.core.Configuration;
import com.knightclient.core.persistence.DTO.ProfileDto;
import com.knightclient.core.persistence.ProfileRepo;
import com.knightclient.core.test.core.TestValues;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ProfileRepoMarshallingTest {

    private ProfileRepo repo;

    @BeforeEach
    void setup() {
        repo = ProfileRepo.getInstance();
    }

    @Test
    void doesExist() {
        assertNotEquals(null, repo);
    }

    @Test
    void shouldReturnANewUser() {
        Configuration.useFileRepo = true;
        Configuration.startWithNewProfile = true;
        ProfileDto dto = repo.readFromProfile();
        assertEquals("new user", dto.getUserName());
    }

    @Test
    void shouldReturnUserData() {
        Configuration.useFileRepo = true;
        Configuration.startWithNewProfile = false;
        ProfileDto dto = new ProfileDto().setUserName(TestValues.STRING_ONE);
        repo.writeToProfile(dto);
        assertEquals(TestValues.STRING_ONE, dto.getUserName());

    }


}
