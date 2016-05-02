package ru.electrictower.bemo.demonstration;

import org.apache.http.client.fluent.Request;
import org.testng.annotations.Test;
import ru.electrictower.bemo.demonstration.contract.GoogleValidatorRequest;
import ru.electrictower.bemo.demonstration.contract.GoogleValidatorResponse;

import java.io.IOException;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.apache.http.entity.ContentType.APPLICATION_JSON;
import static ru.electrictower.bemo.demonstration.locators.GoogleRegistrationPage.*;

/**
 * Created by v1_wizard.
 */
public class DemonstrationTest {

    private static String GOOGLE_SIGN_UP_URL = "https://accounts.google.com/SignUp";

    @Test
    public void testAddressErrorGoogleRegistration() {
        open(GOOGLE_SIGN_UP_URL);

        GoogleValidatorResponse gVResponse = new GoogleValidatorResponse();
        $(GMAIL_ADDRESS_INPUT).setValue("aliaksei.boole");
        $(SUBMIT_BUTTON).click();

        $(ERROR_MESSAGE).shouldHave(text("It's work."));
    }

    @Test
    public void testAddressHintForGoogleRegistration() {
        open(GOOGLE_SIGN_UP_URL);

        GoogleValidatorResponse gVResponse = new GoogleValidatorResponse();
        gVResponse.input01.ErrorData = new String[]{"gogi_gruzinidze"};

        $(GMAIL_ADDRESS_INPUT).setValue("aliaksei.boole");
        $(SUBMIT_BUTTON).click();

        $(HINT_NICKNAME_MESSAGE).shouldHave(text("gogi_gruzinidze"));
    }

    @Test
    public void testVerifyAjaxRequest(){
        open(GOOGLE_SIGN_UP_URL);

        GoogleValidatorResponse gVResponse = new GoogleValidatorResponse();

        $(GMAIL_ADDRESS_INPUT).setValue("aliaksei.boole");
        $(SUBMIT_BUTTON).click();
    }

    @Test
    public void testValidationGoogleApi() throws IOException {
        GoogleValidatorRequest gVRequest = new GoogleValidatorRequest();

        String responseBody = Request.Post("https://accounts.google.com/InputValidator?resource=SignUp")
                .bodyString("", APPLICATION_JSON)
                .execute().returnContent().asString();
    }

}
