package ru.electrictower.bemo;

/**
 * Created by v1_wizard.
 */
public interface IMockBuilder {

    IResponseBuilder with();

    IResponseBuilder withDelay(int seconds);

}
