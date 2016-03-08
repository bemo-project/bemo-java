# Bemo
Bemo(back-end mock) - it's simple way to mock your back-end from webdriver UI tests.

> WARNING: It's framework prototype. Use at your own risk.


### Example usage:
```java
    @Test
    public void testAddressErrorGoogleRegistration() {
        open(GOOGLE_SIGN_UP_URL);

        GoogleValidatorResponse gVResponse = new GoogleValidatorResponse();

        BeMo beMo = new BeMo(getWebDriver()); // Create Bemo instance and setup WebDriver.
        beMo.mockFor(VALIDATOR_URL_PART) // Constract your mock for url pattern.
                .with()
                .body(gson.toJson(gVResponse))  // Set response body.
                .status(200); // Set http response status.
        beMo.inject().enable(); // Inject xhook to page and enable mock.

        $(GMAIL_ADDRESS_INPUT).setValue("aliaksei.boole");
        $(SUBMIT_BUTTON).click();

        $(ERROR_MESSAGE).shouldHave(text("It's work!"));
        assertEquals(1, beMo.getCallCountFor(VALIDATOR_URL_PART));
    }
```

### Main Bemo API
- Setup [xhook](https://github.com/jpillora/xhook) on page:
```java
public BeMo inject()
```
- Enable your prepared mocks:
```java
public void enable()
```
- Disable all mocks:
```java
public void disable()
```
- Construct mock for url pattern:
```java
public IMockBuilder mockFor(String urlPart)
```
- Get count of mock calls for url pattern:
```java
public int getCallCountFor(String urlPart)
```
- Get last ajax request for url pattern (**NEW!**):
```java
public AjaxRequest getRequestFor(String urlPart)
```

### Mock Builder API
- Construct your mock:
```java
public IResponseBuilder with()
```
- Construct your mock with delay (use when you want simulate long request execution):
```java
public IResponseBuilder withDelay(int seconds); // throw new NotImplementedException("Coming soon...");
```

### Response Builder API
- Set fake response http status to mock:
```java
public IResponseBuilder status(int status);
```
- Set fake response http body to mock:
```java
public IResponseBuilder body(String body);
```

# Tech
Bemo use [xhook](https://github.com/jpillora/xhook) to work. 

# Contributing
Fill free to contribute!

License
----

Apache License
