package com.knightclient.core.test.core;

import org.junit.jupiter.api.Test;
import com.knightclient.core.test.core.*;
import com.knightclient.core.*;

import static org.junit.jupiter.api.Assertions.*;

class ConfigurationTest {

    @Test
    void canProcessValues() {
        String[] fooBars = {TestValues.STRING_ONE, TestValues.STRING_THREE, TestValues.STRING_TWO};
        Configuration.parseCommandLine(fooBars);
        assertFalse(Configuration.startWithNewProfile);
        assertTrue(Configuration.useFileRepo);
        String[] testers = {"useDataBaseRepo"};
        Configuration.parseCommandLine(testers);
        assertNotEquals(true, Configuration.useFileRepo);
        testers[0] = "useFileRepo";
        Configuration.parseCommandLine(testers);
        assertTrue(Configuration.useFileRepo);
    }

}