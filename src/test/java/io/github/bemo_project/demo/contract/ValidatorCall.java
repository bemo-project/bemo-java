package io.github.bemo_project.demo.contract;

import io.github.bemo_project.AbstractCall;

import java.util.Map;

/**
 * Created by wizard on 09.05.2016.
 */
public class ValidatorCall extends AbstractCall {
    public ValidatorRequestBody body = new ValidatorRequestBody();

    public ValidatorCall() {
    }

    public ValidatorCall(String gmail_address) {
        body.input01.put("GmailAddress", gmail_address);
    }

    public String getMethod() {
        return "POST";
    }

    public String getUrl() {
        return "InputValidator?resource=SignUp";
    }

    public Object getBody() {
        return body;
    }

    public Map<String, String> getHeaders() {
        return null;
    }
}
