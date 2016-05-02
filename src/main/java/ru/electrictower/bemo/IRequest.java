package ru.electrictower.bemo;

import java.util.Map;

/**
 * Created by v1-wizard on 30.4.16.
 */
public interface IRequest {

    Object getBody();

    int getStatus();

    Map<String, String> getHeaders();
}
