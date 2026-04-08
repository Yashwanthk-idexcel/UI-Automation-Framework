package com.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.utility.BrowserUtil;

public class ShipmentDetailsPage extends BrowserUtil {
	private static final By ACCEPT_TERMS_CHECKBOX_LOCATOR = By.id("uniform-cgv");
	private static final By PROCEED_TO_CHECKOUT_BUTTON_LOCATOR = By.name("processCarrier");

	public ShipmentDetailsPage(WebDriver driver) {
		super(driver);
	}
	
	public PaymentDetailsPage goToPaymentPage() {
		clickOnCheckbox(ACCEPT_TERMS_CHECKBOX_LOCATOR);
		clickOn(PROCEED_TO_CHECKOUT_BUTTON_LOCATOR);
		return new PaymentDetailsPage(getDriver());
	}

}
