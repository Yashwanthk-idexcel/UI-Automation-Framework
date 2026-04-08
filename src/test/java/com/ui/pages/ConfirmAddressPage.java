package com.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.utility.BrowserUtil;

public class ConfirmAddressPage extends BrowserUtil {
	private static final By PROCEED_TO_CHECKOUT_BUTTON_LOCATOR = By.name("processAddress");

	public ConfirmAddressPage(WebDriver driver) {
		super(driver);
	}

	public ShipmentDetailsPage goToShipmentDetailsPage() {
		clickOn(PROCEED_TO_CHECKOUT_BUTTON_LOCATOR);
		return new ShipmentDetailsPage(getDriver());
	}
	
}
