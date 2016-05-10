package io.github.bemo_project.demo.contract;

import io.github.bemo_project.AbstractHandler;

import java.util.Map;

/**
 * Created by wizard on 06.05.2016.
 */
public class ValidatorHandler extends AbstractHandler {

    public ValidatorResponseBody responseBody = new ValidatorResponseBody();

    @Override
    public Object getBody() {
        return responseBody;
    }

    @Override
    public Map<String, String> getHeaders() {
        return null;
    }

    @Override
    public String getUrlPart() {
        return "InputValidator";
    }

    @Override
    public int getStatus() {
        return 200;
    }
}
