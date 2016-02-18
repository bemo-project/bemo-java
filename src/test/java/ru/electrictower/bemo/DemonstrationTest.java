package ru.electrictower.bemo;

import org.openqa.selenium.By;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static junit.framework.Assert.assertEquals;

/**
 * Created by v1_wizard.
 */
public class DemonstrationTest {

    public final String GOOGLE_SIGN_UP_URL = "https://accounts.google.com/SignUp";
    private BeMo beMo;
    private String urlPattern = "InputValidator";
    private String body = "{\n" +
            "\"input01\":{\n" +
            "\t\t\t\"Valid\":\"false\",\n" +
            "\t\t\t\"ErrorMessage\":\"Hello selenium camp 2016.\",\n" +
            "\t\t\t\"Errors\":{\"GmailAddress\":\"Hello selenium camp 2016.\"},\n" +
            "\"ErrorData\":[]\n" +
            "},\n" +
            "\"Locale\":\"ru\"\n" +
            "}";

    @BeforeTest
    public void setUp(){
        beMo = new BeMo(getWebDriver());
        beMo.mockFor(urlPattern)
                .with()
                .body(body)
                .status(200);
    }

    @Test
    public void testMockGoogleRegistration() {
        open(GOOGLE_SIGN_UP_URL);
        beMo.inject().enable();

        assertEquals(0, beMo.getCallCountFor(urlPattern));

        $(By.name("GmailAddress")).setValue("aliaksei.boole");
        $(By.name("submitbutton")).click();

        assertEquals(1, beMo.getCallCountFor(urlPattern));

        $(By.name("GmailAddress")).setValue("aliaksei.bul");
        $(By.name("RecoveryEmailAddress")).click();

        assertEquals(2, beMo.getCallCountFor(urlPattern));
    }
}
