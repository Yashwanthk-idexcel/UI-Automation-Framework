package com.ui.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ui.pages.MyAccountPage;

@Listeners({ com.ui.listeners.TestListener.class })
public class SearchProductTest extends TestBase {

	private MyAccountPage myAccountPage;
	private static final String SEARCHED_TEXT = "Printed Summer Dress";

	@BeforeMethod(description = "valida used logs into the application")
	public void setup() {
		myAccountPage = homePage.goToLoginPage().doLoginWith("yashwanthk@gmail.com", "password");
	}

	@Test(description = "Verify the Search functionality giving proper search results for the user", groups = { "smoke",
			"sanity", "e2e" })
	public void verifyProductSearchTest() {
		boolean actualResult = myAccountPage.searchForProduct(SEARCHED_TEXT).verifySearchTextPresentInResultProductNames(SEARCHED_TEXT);

		Assert.assertEquals(actualResult, true);
	}

}
