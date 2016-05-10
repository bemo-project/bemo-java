package io.github.bemo_project;

import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;

/**
 * Created by v1-wizard on 2.5.16.
 */
public class TemplatesTest {

    @Test
    public void verifyCallCount() {
        assertThat(JsMaker.callCount("{test}"), containsString("handler = {test}"));
    }

    @Test
    public void verifyGetCalls() {
        assertThat(JsMaker.getCalls("{test}"), containsString("handler = {test}"));
    }

    @Test
    public void verifyInitialize() {
        assertThat(JsMaker.initialize("{test}"), containsString("bemoContext = JSON.parse('{test}');"));
    }

    @Test
    public void verifyHandlers() {
        assertThat(JsMaker.handlers("{test}"), containsString("bemoContext = {test};"));
    }
}
