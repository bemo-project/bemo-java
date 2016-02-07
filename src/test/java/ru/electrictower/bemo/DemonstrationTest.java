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
        BeMo beMo = new BeMo(webDriver);
        webDriver.get("https://ru.wargaming.net/registration/ru/");
        beMo.inject();
        beMo.enable();

        beMo.mockFor("/acc/")
                .customResponse()
                .setBody("test")
                .setHeaders(null)
                .setStatus(0)
                .setType(null)
                .delay(1000);

        beMo.mountAll();

        webDriver.quit();
    }

}
