package core.persistence;

import core.Configuration;
import core.TestValues;
import org.junit.jupiter.api.Test;
import persistence.DTO.ProfileDto;
import persistence.repos.FileRepo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class FileRepoMarshallingTest {

    @Test
    void unmarshallWitDefaultAndNewProfile(){
        Configuration.startWithNewProfile=true;
        FileRepo repo = FileRepo.getInstance();
        assertNotNull(repo);
        ProfileDto dto = repo.readFromProfile();
        assertNotNull(dto);
        assertEquals("new user",dto.getUserName());
        assertEquals(1,dto.getAchievements().size());
        FileRepo.destroy();

    }

    @Test
    void fullMarshallTestTroughFileRepo(){
        Configuration.startWithNewProfile=false;
        FileRepo repo = FileRepo.getInstance();
        ProfileDto dto=new ProfileDto()
                .setUserName(TestValues.STRING_TWO)
                .setEmailAddress(TestValues.STRING_THREE);
        repo.writeToProfile(dto);
        ProfileDto result = repo.readFromProfile();
        assertEquals(TestValues.STRING_TWO,result.getUserName());
        assertEquals(TestValues.STRING_THREE,result.getEmailAddress());
    }
}
