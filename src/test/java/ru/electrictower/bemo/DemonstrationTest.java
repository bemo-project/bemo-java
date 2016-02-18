package ru.electrictower.bemo;

import com.google.gson.Gson;
import org.openqa.selenium.By;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import ru.electrictower.bemo.contract.GoogleValidatorResponse;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static junit.framework.Assert.assertEquals;

/**
 * Created by v1_wizard.
 */
public class DemonstrationTest {

    private BeMo beMo;
    private String urlPattern = "InputValidator";

    @BeforeTest
    public void setUp() {
        Gson gson = new Gson();
        beMo = new BeMo(getWebDriver());
        beMo.mockFor(urlPattern)
                .with()
                .body(gson.toJson(new GoogleValidatorResponse()))
                .status(200);
    }

    @Test
    public void testMockGoogleRegistration() {
        open("https://accounts.google.com/SignUp");
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
