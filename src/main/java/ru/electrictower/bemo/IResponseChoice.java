package ru.electrictower.bemo;

/**
 * Created by v1_wizard.
 */
public interface IResponseChoice {

    IMockProperties responseFromRecorder(Object obj);

    IMockProperties customResponse();

}
