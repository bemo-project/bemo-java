package ru.electrictower.bemo;

import org.apache.commons.lang.NotImplementedException;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringEscapeUtils;

import static com.google.common.base.Preconditions.checkNotNull;
import static ru.electrictower.bemo.BeMoConstants.Ajax.statusTextMap;

/**
 * Created by v1_wizard.
 */
public class Mock implements IMockBuilder, IResponseBuilder {
    private String id = RandomStringUtils.random(10, true, false);
    private FakeResponse fakeResponse = null;

    public FakeResponse getFakeResponse() {
        if (fakeResponse == null) {
            fakeResponse = new FakeResponse();
        }
        return fakeResponse;
    }

    public IResponseBuilder with() {
        return this;
    }

    public IResponseBuilder withDelay(int seconds) {
        throw new NotImplementedException("Coming soon...");
    }

    public IResponseBuilder status(int status) {
        if (statusTextMap.containsKey(status)) {
            getFakeResponse().setStatusText(statusTextMap.get(status));
        } else {
            throw new RuntimeException("Unknown http status code:" + status);
        }
        getFakeResponse().setStatus(status);
        return this;
    }

    public IResponseBuilder body(String body) {
        checkNotNull(body);
        String preparedBody = StringEscapeUtils.escapeJavaScript(body);
        getFakeResponse().setBody(preparedBody);
        return this;
    }

    public String getId() {
        return id;
    }
}
