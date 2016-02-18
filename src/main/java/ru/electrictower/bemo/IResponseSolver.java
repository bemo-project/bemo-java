package ru.electrictower.bemo;

/**
 * Created by v1_wizard.
 */
public interface IResponseSolver {

    IResponseBuilder with(FakeResponse obj);

    IResponseBuilder with();

}
