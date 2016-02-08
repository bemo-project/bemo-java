package ru.electrictower.bemo;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import static com.google.common.base.Preconditions.checkNotNull;
import static ru.electrictower.bemo.BeMoConstants.Ajax.statusTextMap;

/**
 * Created by wizard.
 */
class MockImpl implements IResponseChoice, IMockProperties {

    private int status = 0;
    private String body = null;
    public String statusText = null;

    public IMockProperties responseFromRecorder(Object obj) {
        throw new NotImplementedException();
    }

    public IMockProperties customResponse() {
        return this;
    }

    public IMockProperties delay(int seconds) {
        throw new NotImplementedException();
    }

    public IMockProperties setHeaders(Object[] objects) {
        throw new NotImplementedException();
    }

    public IMockProperties setStatus(int status) {
        if (statusTextMap.containsKey(status)) {
            this.statusText = statusTextMap.get(status);
        } else {
            throw new RuntimeException("Unknown http status code:" + status);
        }
        this.status = status;
        return this;
    }

    public IMockProperties setBody(String body) {
        checkNotNull(body);
        this.body = body;
        return this;
    }

    public IMockProperties setType(String type) {
        throw new NotImplementedException();
    }
}
