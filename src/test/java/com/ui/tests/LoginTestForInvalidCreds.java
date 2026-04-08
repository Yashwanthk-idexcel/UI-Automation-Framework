package com.ui.tests;

import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ui.pages.HomePage;
import com.ui.pojo.User;
import com.utility.LoggerUtil;

import constants.Browser;

@Listeners({ com.ui.listeners.TestListener.class })
public class LoginTestForInvalidCreds extends TestBase {
	Logger logger = LoggerUtil.getLogger(this.getClass());
	private static final String INVALID_EMAIL = "testing@gmail.com";
	private static final String INVALID_PASSWORD = "passwor";

	@Test(description = "Verifies login credentials passing invalid credentails of users", groups = { "sanity",
			"negative", })
	public void loginTestForInvalidCreds() {
		String errorMessage = homePage.goToLoginPage().doLoginWithInvalidCreds(INVALID_EMAIL, INVALID_PASSWORD)
				.getErrorMessage();
		Assert.assertEquals(errorMessage, "Authentication failed.");
	}
}
