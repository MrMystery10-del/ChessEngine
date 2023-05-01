package core.persistence;

import core.Configuration;
import core.TestValues;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import persistence.DTO.ProfileDto;
import persistence.ProfileRepo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class ProfileRepoMarshallingTest {

    private ProfileRepo repo;

    @BeforeEach
    void setup(){
        repo=ProfileRepo.getInstance();
        Configuration.useFileRepo=true;
        Configuration.useDataBaseRepo=false;
        Configuration.userConfigLocation=System.getProperty("user.home") + "/chessEngine";
        Configuration.profileFileName = "user.profile";

    }

    @Test
    void doesExist(){
        assertNotEquals(null,repo);
    }



    @Test
    void shouldReturnANewUser(){

        Configuration.startWithNewProfile=true;
        ProfileDto dto = repo.readFromProfile();
        assertEquals("new user",dto.getUserName());
        ProfileRepo.destroy();
    }

    @Test
    void shouldReturnUserData(){
        Configuration.startWithNewProfile=false;
        ProfileDto dto = new ProfileDto().setUserName(TestValues.STRING_ONE);
        repo.writeToProfile(dto);
        assertEquals(TestValues.STRING_ONE,dto.getUserName());
        ProfileRepo.destroy();

    }


}
