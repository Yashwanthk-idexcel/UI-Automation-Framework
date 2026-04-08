package com.ui.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.utility.BrowserUtil;
import com.utility.JsonUtil;
import com.utility.LoggerUtil;
import com.utility.PropertiesUtil;

import constants.Browser;
import constants.Env;

public class HomePage extends BrowserUtil {
	Logger logger = LoggerUtil.getLogger(this.getClass());
	private static final By SIGN_IN_LINK_LOCATOR = By.xpath("//a[contains(text(),'Sign In')]");

	public HomePage(Browser browserName, boolean isHeadless) {
		super(browserName, isHeadless); // To call parent class constructor from child class constructor
//		goToWebsite(PropertiesUtil.getProperty(Env.QA, "URL"));
		goToWebsite(JsonUtil.readUrlFromJson(Env.QA).getUrl());
	}
	
	public HomePage(WebDriver driver) {
		super(driver); // To call parent class constructor from child class constructor
		goToWebsite(JsonUtil.readUrlFromJson(Env.QA).getUrl());
	}

	public LoginPage goToLoginPage() {
		logger.info("Go to Sign in Page");
		clickOn(SIGN_IN_LINK_LOCATOR);
		return new LoginPage(getDriver());
	}

}
