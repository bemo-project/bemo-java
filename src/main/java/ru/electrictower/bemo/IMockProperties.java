package ru.electrictower.bemo;

/**
 * Created by v1_wizard.
 */
public interface IMockProperties {

    IMockProperties delay(int seconds);

    IMockProperties setHeaders(Object[] objects);

    IMockProperties setStatus(int status);

    IMockProperties setBody(String body);

    IMockProperties setType(String type);
}
