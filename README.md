# Bemo ![Build Status](https://travis-ci.org/bemo-project/bemo-java.svg?branch=master)
Bemo(back-end mock) - it's simple way to mock your back-end from webdriver UI tests.

> WARNING: It's framework prototype. Use at your own risk.


### Example usage:
- Test Class
```java
public class DemoTests {
    private BeMo bemo;
    private ValidatorHandler validatorHandler;

    @BeforeMethod
    public void beforeMethod() {
        open("https://accounts.google.com/SignUp");

        bemo = new BeMo(getWebDriver()); // Make BeMo instance.
        validatorHandler = new ValidatorHandler(); //Make Bemo Handler.
    }

    @AfterMethod
    public void afterMethod() {
            bemo.release(); //Disable and destroy xhook.
    }

    @Test
    public void testAddressErrorGoogleRegistration() {
        bemo.addHandler(validatorHandler).inject(); // Register Handler and inject to xhook.

        $(GoogleRegistrationPage.GMAIL_ADDRESS_INPUT).setValue("aliaksei.boole");
        $(GoogleRegistrationPage.SUBMIT_BUTTON).click();

        $(GoogleRegistrationPage.ERROR_MESSAGE).shouldHave(text("It's work."));

        validatorHandler.assertCallCount(1); // Verify that handler was called only once.
        validatorHandler.assertCalledOnceWith(new ValidatorCall()); // Verify that handler was called with this call. 
    }
}
```
- ValidatorHandler
```java
public class ValidatorHandler extends AbstractHandler {

    public ValidatorResponseBody responseBody = new ValidatorResponseBody();

    @Override
    public Object getBody() {
        return responseBody; // If string than use as is. If Object than will transform to Json via Gson.
    }

    @Override
    public Map<String, String> getHeaders() {
        return null; // Null was ignoring.
    }

    @Override
    public String getUrlPart() {
        return "InputValidator"; // This is part of url for setup handler.
    }

    @Override
    public int getStatus() {
        return 200; // Http status code.
    }
}
```
- ValidatorCall
```java
public class ValidatorCall extends AbstractCall {
    public ValidatorRequestBody body = new ValidatorRequestBody();

    public ValidatorCall() {
    }

    public ValidatorCall(String gmail_address) {
        body.input01.put("GmailAddress", gmail_address);
    }

    public String getMethod() {
        return "POST"; // Http method.
    }

    public String getUrl() {
        return "InputValidator?resource=SignUp"; // Request url.
    }

    public Object getBody() {
        return body; // Request body.
    }

    public Map<String, String> getHeaders() {
        return null; // Null will be ignore in verification.
    }
}
```

# Tech
Bemo use [xhook](https://github.com/jpillora/xhook) and [gson](https://github.com/google/gson)  to work.

# Contributing
Fill free to contribute!

License
----

Apache License
