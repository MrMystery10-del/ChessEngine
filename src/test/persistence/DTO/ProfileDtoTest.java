package persistence.DTO;

import core.TestValues;
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
        assertEquals(null,toTest.getUserName());
        toTest.setUserName(TestValues.STRING_ONE);
        assertEquals(TestValues.STRING_ONE, toTest.getUserName());
    }
}