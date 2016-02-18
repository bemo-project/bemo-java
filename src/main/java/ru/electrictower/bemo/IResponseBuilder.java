package ru.electrictower.bemo;

/**
 * Created by v1_wizard.
 */
public interface IResponseBuilder {
    IResponseBuilder status(int status);

    IResponseBuilder body(String body);
}
