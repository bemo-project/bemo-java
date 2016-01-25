package ru.electrictower.bemo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

/**
 * Created by v1_wizard.
 */
public class DemonstrationTest {

    @Test
    public void firstTest() {
        Bemo bemo = new Bemo();
        WebDriver webDriver = new FirefoxDriver(bemo.getFirefoxProfile());
        webDriver.get("https://ru.wargaming.net/registration/ru/");
        bemo.inject(webDriver);
        webDriver.quit();
    }

}
