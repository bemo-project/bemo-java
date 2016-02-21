package ru.electrictower.bemo.demonstration.helpers;

import ru.electrictower.bemo.BeMo;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

/**
 * Created by v1_wizard.
 */
public class Helpers {

    public static void lovely_sleep(int seconds) {
        try {
            Thread.sleep((long) seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static BeMo makeBeMo() {
        return new BeMo(getWebDriver());
    }
}
