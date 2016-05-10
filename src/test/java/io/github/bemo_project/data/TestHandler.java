package io.github.bemo_project.data;

import io.github.bemo_project.AbstractHandler;

import java.util.Map;

/**
 * Created by wizard on 10.05.2016.
 */
public class TestHandler extends AbstractHandler {
    public String body = "{}";
    public Map<String, String> headers;
    public String urlPart = "test";
    public int status = 200;

    @Override
    public Object getBody() {
        return body;
    }

    @Override
    public Map<String, String> getHeaders() {
        return headers;
    }

    @Override
    public String getUrlPart() {
        return urlPart;
    }

    @Override
    public int getStatus() {
        return status;
    }
}
