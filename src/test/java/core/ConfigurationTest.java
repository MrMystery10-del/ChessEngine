package core;


import org.junit.jupiter.api.Test;

import java.util.Properties;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ConfigurationTest {


    @Test
    void canProcessValues() {

        Properties props = new Properties();
        props.setProperty("startWithNewProfile", "false");
        props.setProperty("useFileRepo", TestValues.STRING_TWO);


        //basic config test, with wrong values
        Configuration.parseCommandLine(props);
        assertTrue(Configuration.startWithNewProfile);
        assertTrue(Configuration.useFileRepo);
        //use database instead of file
        props.setProperty("useDataBaseRepo", "true");
        Configuration.parseCommandLine(props);
        assertNotEquals(true, Configuration.useFileRepo);
    }

}