package ru.electrictower.bemo;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * Created by wizard.
 */
public class MockImpl implements IResponseChoice, IMockProperties {

    public IMockProperties responseFromRecorder(Object obj) {
        throw new NotImplementedException();
    }

    public IMockProperties customResponse() {
        throw new NotImplementedException();
    }

    public IMockProperties delay(int seconds) {
        throw new NotImplementedException();
    }

    public IMockProperties setHeaders(Object[] objects) {
        throw new NotImplementedException();
    }

    public IMockProperties setStatus(int status) {
        throw new NotImplementedException();
    }

    public IMockProperties setBody(Object object) {
        throw new NotImplementedException();
    }

    public IMockProperties setType(String type) {
        throw new NotImplementedException();
    }
}
