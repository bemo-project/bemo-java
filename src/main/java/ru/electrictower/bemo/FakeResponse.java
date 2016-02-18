package ru.electrictower.bemo;

/**
 * Created by v1_wizard.
 */
public class FakeResponse {
    private String body = "";
    private int status = 200;
    private String statusText = "OK";

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getStatusText() {
        return statusText;
    }

    public void setStatusText(String statusText) {
        this.statusText = statusText;
    }
}
