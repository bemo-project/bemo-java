package ru.electrictower.bemo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

/**
 * Created by v1_wizard.
 */
public class DemonstrationTest {

    public final String GOOGLE_SIGN_UP_URL = "https://accounts.google.com/SignUp";

    @Test
    public void test() {
        WebDriver webDriver = new FirefoxDriver();
        BeMo beMo = new BeMo(webDriver);
        webDriver.get(GOOGLE_SIGN_UP_URL);
        beMo.inject();
        beMo.enable();

//        beMo.mockFor("/acc/")
//                .customResponse()
//                .setBody("test")
//                .setHeaders(null)
//                .setStatus(0)
//                .setType(null)
//                .delay(1000);
//
//        beMo.mountAll();

        webDriver.quit();
    }

    @Test
    public void debugTest(){
        MockImpl mock = new MockImpl();
        mock.setStatus(409);
    }

}
