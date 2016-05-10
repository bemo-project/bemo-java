package io.github.bemo_project.data;

import io.github.bemo_project.AbstractCall;

import java.util.Map;

/**
 * Created by wizard on 10.05.2016.
 */
public class TestCall extends AbstractCall {
    public String method = "GET";
    public String url = "test/url";
    public String body = "{test}";
    public Map<String, String> headers;

    @Override
    public String getMethod() {
        return method;
    }

    @Override
    public String getUrl() {
        return url;
    }

    @Override
    public Object getBody() {
        return body;
    }

    @Override
    public Map<String, String> getHeaders() {
        return headers;
    }
}