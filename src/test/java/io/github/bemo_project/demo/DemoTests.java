package io.github.bemo_project.demo;

import io.github.bemo_project.AbstractCall;
import io.github.bemo_project.BeMo;
import io.github.bemo_project.demo.contract.ValidatorCall;
import io.github.bemo_project.demo.contract.ValidatorHandler;
import io.github.bemo_project.demo.locators.GoogleRegistrationPage;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

/**
 * Created by v1_wizard.
 */
public class DemoTests {
    private BeMo bemo;
    private ValidatorHandler validatorHandler;

    @BeforeMethod
    public void beforeMethod() {
        open("https://accounts.google.com/SignUp");

        bemo = new BeMo(getWebDriver());
        validatorHandler = new ValidatorHandler();
    }

    @AfterMethod
    public void afterMethod() {
            bemo.release();
    }

    @Test
    public void testAddressErrorGoogleRegistration() {
        bemo.addHandler(validatorHandler).inject();

        $(GoogleRegistrationPage.GMAIL_ADDRESS_INPUT).setValue("aliaksei.boole");
        $(GoogleRegistrationPage.SUBMIT_BUTTON).click();

        $(GoogleRegistrationPage.ERROR_MESSAGE).shouldHave(text("It's work."));

        validatorHandler.assertCallCount(1);
    }

    @Test
    public void testAddressHintForGoogleRegistration() {
        validatorHandler.responseBody.input01.ErrorData = new String[]{"gogi_gruzinidze"};
        bemo.addHandler(validatorHandler).inject();

        $(GoogleRegistrationPage.GMAIL_ADDRESS_INPUT).setValue("aliaksei.boole");
        $(GoogleRegistrationPage.SUBMIT_BUTTON).click();

        $(GoogleRegistrationPage.HINT_NICKNAME_MESSAGE).shouldHave(text("gogi_gruzinidze"));

        validatorHandler.assertCalled();
        validatorHandler.assertCalledOnce();
    }

    @Test
    public void testVerifyAjaxRequest() {
        bemo.addHandler(validatorHandler).inject();

        $(GoogleRegistrationPage.GMAIL_ADDRESS_INPUT).setValue("aliaksei.boole");
        $(GoogleRegistrationPage.SUBMIT_BUTTON).click();

        $(GoogleRegistrationPage.ERROR_MESSAGE).shouldHave(text("It's work."));

        validatorHandler.assertCalledOnceWith(new ValidatorCall());
    }

    @Test
    public void testVerifySeveralAjaxRequests() {
        bemo.addHandler(validatorHandler).inject();

        $(GoogleRegistrationPage.GMAIL_ADDRESS_INPUT).setValue("aliaksei.boole");
        $(GoogleRegistrationPage.SUBMIT_BUTTON).click();

        $(GoogleRegistrationPage.GMAIL_ADDRESS_INPUT).setValue("gogi.gruzinidze");
        $(GoogleRegistrationPage.SUBMIT_BUTTON).click();

        $(GoogleRegistrationPage.ERROR_MESSAGE).shouldHave(text("It's work."));

        List<AbstractCall> calls = new ArrayList<AbstractCall>();
        calls.add(new ValidatorCall());
        calls.add(new ValidatorCall("gogi.gruzinidze"));

        validatorHandler.assertCalledWith(calls);
    }
}
