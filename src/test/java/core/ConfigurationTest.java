package core;


import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.*;

class ConfigurationTest {



    @Test
    void canProcessValues() throws IOException {
        Properties props = new Properties();
        props.setProperty("startWithNewProfile",TestValues.STRING_ONE);
        props.setProperty("useFileRepo",TestValues.STRING_TWO);

        //basic config test, with wrong values
        Configuration.parseCommandLine(props);
        assertFalse(Configuration.startWithNewProfile);
        assertTrue(Configuration.useFileRepo);
        //use database instead of file
        props.setProperty("useDataBaseRepo","true");
        Configuration.parseCommandLine(props);
        assertNotEquals(true,Configuration.useFileRepo);
    }

}