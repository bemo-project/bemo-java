package ru.electrictower.bemo;

import java.util.List;

/**
 * Created by v1_wizard.
 */
public class AjaxRequest {
    public final String url;
    public final String body;

    AjaxRequest(List<String> jsList) {
        this.url = jsList.get(0);
        this.body = jsList.get(1);
    }
}
