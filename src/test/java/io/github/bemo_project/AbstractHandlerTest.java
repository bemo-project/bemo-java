package io.github.bemo_project;

import io.github.bemo_project.data.TestCall;
import io.github.bemo_project.data.TestHandler;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.anyVararg;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by wizard on 10.05.2016.
 */
public class AbstractHandlerTest {

    @Test
    public void verifyAssertCalled() {
        JavascriptExecutor jsExecutor = mock(JavascriptExecutor.class);
        when(jsExecutor.executeScript(anyString(), anyVararg())).thenReturn(2L);

        AbstractHandler handler = new TestHandler();

        handler.setJsExecutor(jsExecutor);
        handler.assertCalled();
    }

    @Test(expectedExceptions = AssertionError.class)
    public void verifyAssertCalledThrowError() {
        JavascriptExecutor jsExecutor = mock(JavascriptExecutor.class);
        when(jsExecutor.executeScript(anyString(), anyVararg())).thenReturn(0L);

        AbstractHandler handler = new TestHandler();

        handler.setJsExecutor(jsExecutor);
        handler.assertCalled();
    }

    @Test
    public void verifyAssertCalledOnce() {
        JavascriptExecutor jsExecutor = mock(JavascriptExecutor.class);
        when(jsExecutor.executeScript(anyString(), anyVararg())).thenReturn(1L);

        AbstractHandler handler = new TestHandler();

        handler.setJsExecutor(jsExecutor);
        handler.assertCalledOnce();
    }

    @Test(expectedExceptions = AssertionError.class)
    public void verifyAssertCalledOnceThrowError() {
        JavascriptExecutor jsExecutor = mock(JavascriptExecutor.class);
        when(jsExecutor.executeScript(anyString(), anyVararg())).thenReturn(2L);

        AbstractHandler handler = new TestHandler();

        handler.setJsExecutor(jsExecutor);
        handler.assertCalledOnce();
    }

    @Test
    public void verifyAssertCallCount() {
        JavascriptExecutor jsExecutor = mock(JavascriptExecutor.class);
        when(jsExecutor.executeScript(anyString(), anyVararg())).thenReturn(5L);

        AbstractHandler handler = new TestHandler();

        handler.setJsExecutor(jsExecutor);
        handler.assertCallCount(5);
    }

    @Test(expectedExceptions = AssertionError.class)
    public void verifyAssertCallCountThrowError() {
        JavascriptExecutor jsExecutor = mock(JavascriptExecutor.class);
        when(jsExecutor.executeScript(anyString(), anyVararg())).thenReturn(5L);

        AbstractHandler handler = new TestHandler();

        handler.setJsExecutor(jsExecutor);
        handler.assertCallCount(3);
    }

    @Test
    public void verifyAssertCalledOnceWith() {
        JavascriptExecutor jsExecutor = mock(JavascriptExecutor.class);
        String testDatum = "[{\"method\":\"GET\",\"url\":\"test/url\"," +
                "\"headers\":{\"Content-type\":\"application/json\"},\"body\":\"{test}\"}]";
        when(jsExecutor.executeScript(anyString(), anyVararg())).thenReturn(testDatum);

        AbstractHandler handler = new TestHandler();

        handler.setJsExecutor(jsExecutor);
        handler.assertCalledOnceWith(new TestCall());
    }

    @Test(expectedExceptions = AssertionError.class)
    public void verifyAssertCalledOnceWithThrowError() {
        JavascriptExecutor jsExecutor = mock(JavascriptExecutor.class);
        String testDatum = "[{\"method\":\"POST\",\"url\":\"test/url\"," +
                "\"headers\":{\"Content-type\":\"application/json\"},\"body\":\"{test}\"}]";
        when(jsExecutor.executeScript(anyString(), anyVararg())).thenReturn(testDatum);

        AbstractHandler handler = new TestHandler();

        handler.setJsExecutor(jsExecutor);
        handler.assertCalledOnceWith(new TestCall());
    }

    @Test(expectedExceptions = AssertionError.class)
    public void verifyAssertCalledOnceWithThrowErrorIfCallsCountNot1() {
        JavascriptExecutor jsExecutor = mock(JavascriptExecutor.class);
        when(jsExecutor.executeScript(anyString(), anyVararg())).thenReturn("[]");

        AbstractHandler handler = new TestHandler();

        handler.setJsExecutor(jsExecutor);
        handler.assertCalledOnceWith(new TestCall());
    }

    @Test
    public void verifyAssertCalledWith() {
        JavascriptExecutor jsExecutor = mock(JavascriptExecutor.class);
        String testDatum = "[{\"method\":\"GET\",\"url\":\"test/url\"," +
                "\"headers\":{\"Content-type\":\"application/json\"},\"body\":\"{test}\"}]";
        when(jsExecutor.executeScript(anyString(), anyVararg())).thenReturn(testDatum);

        AbstractHandler handler = new TestHandler();
        List<AbstractCall> testCalls = new ArrayList<AbstractCall>();
        testCalls.add(new TestCall());

        handler.setJsExecutor(jsExecutor);
        handler.assertCalledWith(testCalls);
    }

    @Test(expectedExceptions = AssertionError.class)
    public void verifyAssertCalledWithThrowErrorIfArrayIsEmpty() {
        JavascriptExecutor jsExecutor = mock(JavascriptExecutor.class);
        String testDatum = "[]";
        when(jsExecutor.executeScript(anyString(), anyVararg())).thenReturn(testDatum);

        AbstractHandler handler = new TestHandler();
        List<AbstractCall> testCalls = new ArrayList<AbstractCall>();
        testCalls.add(new TestCall());

        handler.setJsExecutor(jsExecutor);
        handler.assertCalledWith(testCalls);
    }

    @Test(expectedExceptions = AssertionError.class)
    public void verifyAssertCalledWithThrowError() {
        JavascriptExecutor jsExecutor = mock(JavascriptExecutor.class);
        String testDatum = "[{\"method\":\"POST\",\"url\":\"test/url\"," +
                "\"headers\":{\"Content-type\":\"application/json\"},\"body\":\"{test}\"}]";
        when(jsExecutor.executeScript(anyString(), anyVararg())).thenReturn(testDatum);

        AbstractHandler handler = new TestHandler();
        List<AbstractCall> testCalls = new ArrayList<AbstractCall>();
        testCalls.add(new TestCall());

        handler.setJsExecutor(jsExecutor);
        handler.assertCalledWith(testCalls);
    }
}
