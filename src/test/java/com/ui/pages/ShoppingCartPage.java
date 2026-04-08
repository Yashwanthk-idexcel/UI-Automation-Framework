package com.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.utility.BrowserUtil;

public class ShoppingCartPage extends BrowserUtil{
	
	private static final By PROCEED_TO_CHECKOUT_BUTTON_LOCATOR = By.xpath("//p[contains(@class,\"cart_nagivation\")]/a[@title=\"Proceed to Checkout\"]");
	

	public ShoppingCartPage(WebDriver driver) {
		super(driver);
	}
	
	
	public ConfirmAddressPage goToConfirmAddressPage() {
		clickOn(PROCEED_TO_CHECKOUT_BUTTON_LOCATOR);
		return new ConfirmAddressPage(getDriver());
	}
	

}
