package io.github.bemo_project;

import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertNotNull;


/**
 * Created by v1-wizard on 2.5.16.
 */
public class TemplatesTests {

    @Test
    public void verifyTemplatesLoaded() {
        assertNotNull(JsMaker.enable());
    }


}
