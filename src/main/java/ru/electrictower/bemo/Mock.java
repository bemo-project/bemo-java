package ru.electrictower.bemo;

import org.apache.commons.lang.RandomStringUtils;

import static com.google.common.base.Preconditions.checkNotNull;
import static ru.electrictower.bemo.BeMoConstants.Ajax.statusTextMap;

/**
 * Created by v1_wizard.
 */
public class Mock implements IResponseSolver, IResponseBuilder {
    private String id = RandomStringUtils.random(10, true, false);
    private FakeResponse fakeResponse = null;

    public FakeResponse getFakeResponse() {
        if (fakeResponse == null) {
            fakeResponse = new FakeResponse();
        }
        return fakeResponse;
    }

    public IResponseBuilder with(FakeResponse fakeResponse) {
        this.fakeResponse = fakeResponse;
        return this;
    }

    public IResponseBuilder with() {
        return this;
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
        String preparedBody = body.replaceAll("(\\r|\\n|\\t)", "").replaceAll("\'", "\"");
        getFakeResponse().setBody(preparedBody);
        return this;
    }

    public String getId() {
        return id;
    }
}
