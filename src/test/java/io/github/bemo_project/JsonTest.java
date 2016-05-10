package io.github.bemo_project;

import io.github.bemo_project.data.TestCall;
import io.github.bemo_project.data.TestHandler;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.assertEquals;

/**
 * Created by v1-wizard on 2.5.16.
 */
public class JsonTest {

    @Test
    public void verityInitJson() {
        assertEquals(JsonMaker.init("test"), "{\"XHookScriptURL\":\"test\"}");
    }

    @Test
    public void verifyHandlersJson() {
        List<AbstractHandler> handlers = new ArrayList<AbstractHandler>();
        handlers.add(new TestHandler());
        String handlersAsString = JsonMaker.handlers(handlers);
        assertThat(handlersAsString, containsString("\"body\":\"{}\""));
        assertThat(handlersAsString, containsString("\"statusText\":\"OK\""));
        assertThat(handlersAsString, containsString("\"status\":200"));
        assertThat(handlersAsString, containsString("\"body\":\"{}\""));
    }

    @Test
    public void verifyMakeBodyFromCall() {
        String bodyAsString = JsonMaker.makeBody(new TestCall());
        assertEquals(bodyAsString, "{test}");
    }

    @Test
    public void verifyDecodeCalls() {
        String testDatum = "[{\"method\":\"POST\",\"url\":\"InputValidator?resource=SignUp\"," +
                "\"headers\":{\"Content-type\":\"application/json\"},\"body\":\"" +
                "{\\\"input01\\\":{\\\"Input\\\":\\\"GmailAddress\\\"," +
                "\\\"GmailAddress\\\":\\\"aliaksei.boole\\\",\\\"FirstName\\\":\\\"\\\"," +
                "\\\"LastName\\\":\\\"\\\"},\\\"Locale\\\":\\\"ru\\\"}\"}]";
        List<AbstractCall> abstractCalls = JsonMaker.decodeCalls(testDatum);
        assertThat(abstractCalls, hasSize(1));
        assertThat(abstractCalls.get(0), notNullValue());
    }

    @Test
    public void verifyHandler() {
        String handlerAsString = JsonMaker.handler(new TestHandler());
        assertThat(handlerAsString, containsString("\"body\":\"{}\""));
        assertThat(handlerAsString, containsString("\"statusText\":\"OK\""));
        assertThat(handlerAsString, containsString("\"status\":200"));
        assertThat(handlerAsString, containsString("\"body\":\"{}\""));
    }
}
