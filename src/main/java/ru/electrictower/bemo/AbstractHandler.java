package ru.electrictower.bemo;

import org.apache.commons.lang.RandomStringUtils;
import org.openqa.selenium.JavascriptExecutor;

import java.util.List;
import java.util.Map;

/**
 * Created by v1-wizard on 30.4.16.
 */
public abstract class AbstractHandler {
    String id = RandomStringUtils.random(10, true, false);
    private JavascriptExecutor jsExecutor;

    void setJsExecutor(JavascriptExecutor jsExecutor) {
        this.jsExecutor = jsExecutor;
    }

    public void assertCallCount(int count) {
        int actualCount = (Integer) jsExecutor.executeScript(JsMaker.callCountJs(null));
        if (actualCount != count) {
            throw new AssertionError(); //TODO
        }
    }

    public void assertCalled() {
        throw new AssertionError(); //TODO
    }


    public void assertCalledWith(List<IRequest> requestList) {
        throw new AssertionError(); //TODO
    }


    public void assertCalledOnceWith(IRequest request) {
        throw new AssertionError(); //TODO
    }


    public abstract Object getBody();

    public abstract Map<String, String> getHeaders();

    public abstract String getUrlPart();

    public abstract int getStatus();
}
