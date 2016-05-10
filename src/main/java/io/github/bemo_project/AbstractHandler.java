package io.github.bemo_project;

import org.openqa.selenium.JavascriptExecutor;

import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by v1-wizard on 30.4.16.
 */
public abstract class AbstractHandler {
    private final String uid = UUID.randomUUID().toString();
    private JavascriptExecutor jsExecutor;

    void setJsExecutor(JavascriptExecutor jsExecutor) {
        this.jsExecutor = jsExecutor;
    }

    public String getUid() {
        return uid;
    }

    public void assertCallCount(long count) {
        long actualCount = getCallCount();
        if (actualCount != count) {
            throw new AssertionError(
                    String.format("Expected call count is [%s], but actual is [%s] for %s.", count, actualCount, this)
            );
        }
    }

    public void assertCalled() {
        long actualCount = getCallCount();
        if (actualCount == 0) {
            throw new AssertionError(
                    String.format("%s wasn't called.", this)
            );
        }
    }

    public void assertCalledOnce() {
        long actualCount = getCallCount();
        if (actualCount != 1) {
            throw new AssertionError(
                    String.format("%s wasn't called once. It was called [%s] times.", this, actualCount)
            );
        }
    }

    private long getCallCount() {
        return (Long) jsExecutor.executeScript(JsMaker.callCount(JsonMaker.handler(this)));
    }


    public void assertCalledWith(List<AbstractCall> callList) {
        List<AbstractCall> actualCalls = JsonMaker.decodeCalls(getCallsAsString());
        if (!actualCalls.equals(callList)) {
            throw new AssertionError(
                    String.format("\nExpected is %s,\n but actual is %s \n for %s", callList, actualCalls, this)
            );
        }
    }


    public void assertCalledOnceWith(AbstractCall call) {
        List<AbstractCall> actualCalls = JsonMaker.decodeCalls(getCallsAsString());
        if (actualCalls.size() != 1) {
            throw new AssertionError(
                    String.format("%s wasn't called once. It was called [%s] times.", this, actualCalls.size())
            );
        }

        AbstractCall actualCall = actualCalls.get(0);
        if (!actualCall.equals(call)) {
            throw new AssertionError(
                    String.format("\n Expected is %s,\n but actual is %s \n for %s", call, actualCall, this)
            );
        }
    }

    private String getCallsAsString() {
        return (String) jsExecutor.executeScript(JsMaker.getCalls(JsonMaker.handler(this)));
    }

    @Override
    public String toString() {
        return String.format("Handler{urlPart=%s, uid=%s}", getUid(), getUrlPart());
    }

    public abstract Object getBody();

    public abstract Map<String, String> getHeaders();

    public abstract String getUrlPart();

    public abstract int getStatus();
}
