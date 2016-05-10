package io.github.bemo_project;

import io.github.bemo_project.data.TestCall;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.core.Is.is;

/**
 * Created by wizard on 10.05.2016.
 */
public class AbstractCallTest {

    @Test
    public void verifyEqualsIgnoreNulls() {
        TestCall call1 = new TestCall();
        TestCall call2 = new TestCall();
        call2.body = null;

        assertThat(call1, is(equalTo(call2)));
    }
}
