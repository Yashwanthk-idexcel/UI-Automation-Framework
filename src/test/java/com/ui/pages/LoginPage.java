package com.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.utility.BrowserUtil;

public class LoginPage extends BrowserUtil {
	
	private static final By EMAIL_TEXTBOX_LOCATOR = By.id("email");
	private static final By PASSWORD_TEXTBOX_LOCATOR = By.id("password");
	private static final By SUBMIT_BUTTON_LOCATOR = By.id("submitLogin");
	private static final By ERROR_MSG_LOCATOR = By.xpath("//div[contains(@class, \"alert-danger\")]/ol/li");


	public LoginPage(WebDriver driver) {
		super(driver);
	}

	public MyAccountPage doLoginWith(String email, String password) {
		enterText(EMAIL_TEXTBOX_LOCATOR, email);
		enterText(PASSWORD_TEXTBOX_LOCATOR, password);
		clickOn(SUBMIT_BUTTON_LOCATOR);
		return new MyAccountPage(getDriver());
	}
	
	public LoginPage doLoginWithInvalidCreds(String email, String password) {
		enterText(EMAIL_TEXTBOX_LOCATOR, email);
		enterText(PASSWORD_TEXTBOX_LOCATOR, password);
		clickOn(SUBMIT_BUTTON_LOCATOR);
		return new LoginPage(getDriver());
	}
	
	public String getErrorMessage() {
		return getVisibleTest(EMAIL_TEXTBOX_LOCATOR);
	}
	
}
