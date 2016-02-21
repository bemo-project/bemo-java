package ru.electrictower.bemo.demonstration.locators;

import org.openqa.selenium.By;

/**
 * Created by v1_wizard.
 */
public class GoogleRegistrationPage {
    public static final By GMAIL_ADDRESS_INPUT = By.name("GmailAddress");
    public static final By SUBMIT_BUTTON = By.name("submitbutton");
    public static final By ERROR_MESSAGE = By.id("errormsg_0_GmailAddress");
    public static final By HINT_NICKNAME_MESSAGE = By.xpath("//div[@id='username-suggestions']/a");
}
