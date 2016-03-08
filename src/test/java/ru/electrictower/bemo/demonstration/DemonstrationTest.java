package ru.electrictower.bemo.demonstration;

import com.google.gson.Gson;
import org.apache.http.client.fluent.Request;
import org.testng.annotations.Test;
import ru.electrictower.bemo.AjaxRequest;
import ru.electrictower.bemo.BeMo;
import ru.electrictower.bemo.demonstration.contract.GoogleValidatorRequest;
import ru.electrictower.bemo.demonstration.contract.GoogleValidatorResponse;

import java.io.IOException;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static org.apache.http.entity.ContentType.APPLICATION_JSON;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static ru.electrictower.bemo.demonstration.locators.GoogleRegistrationPage.*;

/**
 * Created by v1_wizard.
 */
public class DemonstrationTest {

    private Gson gson = new Gson();
    private static String VALIDATOR_URL_PART = "InputValidator";
    private static String GOOGLE_SIGN_UP_URL = "https://accounts.google.com/SignUp";

    @Test
    public void testAddressErrorGoogleRegistration() {
        open(GOOGLE_SIGN_UP_URL);

        GoogleValidatorResponse gVResponse = new GoogleValidatorResponse();

        BeMo beMo = new BeMo(getWebDriver());
        beMo.mockFor(VALIDATOR_URL_PART)
                .with()
                .body(gson.toJson(gVResponse))
                .status(200);
        beMo.inject().enable();

        $(GMAIL_ADDRESS_INPUT).setValue("aliaksei.boole");
        $(SUBMIT_BUTTON).click();

        $(ERROR_MESSAGE).shouldHave(text("It's work."));
        assertEquals(1, beMo.getCallCountFor(VALIDATOR_URL_PART));
    }

    @Test
    public void testAddressHintForGoogleRegistration() {
        open(GOOGLE_SIGN_UP_URL);

        GoogleValidatorResponse gVResponse = new GoogleValidatorResponse();
        gVResponse.input01.ErrorData = new String[]{"gogi_gruzinidze"};

        BeMo beMo = new BeMo(getWebDriver());
        beMo.mockFor(VALIDATOR_URL_PART)
                .with()
                .body(gson.toJson(gVResponse))
                .status(200);
        beMo.inject().enable();

        $(GMAIL_ADDRESS_INPUT).setValue("aliaksei.boole");
        $(SUBMIT_BUTTON).click();

        $(HINT_NICKNAME_MESSAGE).shouldHave(text("gogi_gruzinidze"));
        assertEquals(1, beMo.getCallCountFor(VALIDATOR_URL_PART));
    }

    @Test
    public void testVerifyAjaxRequest(){
        open(GOOGLE_SIGN_UP_URL);

        GoogleValidatorResponse gVResponse = new GoogleValidatorResponse();

        BeMo beMo = new BeMo(getWebDriver());
        beMo.mockFor(VALIDATOR_URL_PART)
                .with()
                .body(gson.toJson(gVResponse))
                .status(200);
        beMo.inject().enable();

        $(GMAIL_ADDRESS_INPUT).setValue("aliaksei.boole");
        $(SUBMIT_BUTTON).click();

        AjaxRequest ajaxReq = beMo.getRequestFor(VALIDATOR_URL_PART);
        GoogleValidatorRequest gVRequest = gson.fromJson(ajaxReq.body, GoogleValidatorRequest.class);
        assertTrue(gVRequest.input01.containsValue("aliaksei.boole"));
        assertEquals(ajaxReq.url, "InputValidator?resource=SignUp");
    }

    @Test
    public void testValidationGoogleApi() throws IOException {
        GoogleValidatorRequest gVRequest = new GoogleValidatorRequest();

        String responseBody = Request.Post("https://accounts.google.com/InputValidator?resource=SignUp")
                .bodyString(gson.toJson(gVRequest), APPLICATION_JSON)
                .execute().returnContent().asString();

        GoogleValidatorResponse gVResponse = gson.fromJson(responseBody, GoogleValidatorResponse.class);
        assertEquals(
                "Имя уже занято. Обратите внимание: невозможно создать новое имя пользователя, " +
                        "добавив точки к уже существующему или поменяв строчные буквы на заглавные. " +
                        "Попробуйте ещё раз.",
                gVResponse.input01.ErrorMessage
        );

        assertTrue(gVResponse.input01.ErrorData[0].contains("aliakseiboole"));
    }

}
