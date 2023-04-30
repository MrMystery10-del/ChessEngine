package com.knightclient.core.test.persistence.DTO;

import com.knightclient.core.persistence.DTO.ProfileDto;
import com.knightclient.core.test.core.TestValues;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProfileDtoTest {

    private ProfileDto toTest;

    @BeforeEach
    void setUp() {
        toTest = new ProfileDto();
    }

    @Test
    void doesExist() {
        assertNotNull(toTest);
    }


    @Test
    void testUserName() {
        assertNull(toTest.getUserName());
        toTest.setUserName(TestValues.STRING_ONE);
        assertEquals(TestValues.STRING_ONE, toTest.getUserName());
    }
}