package core.persistence;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;

import core.TestValues;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import persistence.DTO.ProfileDto;

import static org.junit.jupiter.api.Assertions.*;

public class UnmarshallToDtoTest {

    ProfileDto dto;

    @BeforeEach
    void setUp() {
        dto = new ProfileDto();
    }

    @Test
    void doesExist() {
        assertNotNull(dto);
    }

    @SuppressWarnings("RedundantThrows")
    @Test
    void unmarshalWithoutFileAndIncorrectContents() throws JsonProcessingException {

        ObjectMapper mapper = new ObjectMapper();
        assertThrows(UnrecognizedPropertyException.class, () -> {
            String jsonAsString =
                    "{_userName_:_a_," +
                    "_userID_:1," +
                    "_booleanValue_:true}" ;
            jsonAsString = jsonAsString.replaceAll("_", "\"");

            mapper.readValue( jsonAsString, ProfileDto.class);
        });
    }@Test
    void unmarshalWithoutFile() throws JsonProcessingException {

        ObjectMapper mapper = new ObjectMapper();

            String jsonAsString =
                    "{_userName_:_"+ TestValues.STRING_ONE+ "_," +
                    "_userId_:"+TestValues.INT_ONE+
                    "}" ;
            jsonAsString = jsonAsString.replaceAll("_", "\"");
            dto=mapper.readValue( jsonAsString, ProfileDto.class);
            assertEquals(TestValues.STRING_ONE, dto.getUserName());
            assertEquals(TestValues.INT_ONE,dto.getUserId());

    }

}
