package ru.electrictower.bemo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

/**
 * Created by v1_wizard.
 */
public class DemonstrationTest {

    @Test
    public void test() {
        WebDriver webDriver = new FirefoxDriver();
        Bemo bemo = new Bemo(webDriver);
        webDriver.get("https://ru.wargaming.net/registration/ru/");
        bemo.inject();
        bemo.enable();
        bemo.disable();
        webDriver.quit();
    }

}
