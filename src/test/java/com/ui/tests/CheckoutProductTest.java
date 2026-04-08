package com.ui.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.ui.pages.ConfirmAddressPage;
import com.ui.pages.SearchResultPage;
import com.ui.pages.ShipmentDetailsPage;
import com.ui.pages.ShoppingCartPage;

import constants.Size;

public class CheckoutProductTest extends TestBase {

	private static final String SEARCH_TERM = "Printed Summer Shirt";
	private SearchResultPage searchResultPage;

	@BeforeMethod(description = "user logins to the application and searches the product")
	public void setUp() {
		searchResultPage = homePage.goToLoginPage().doLoginWith("yashwanthk@gmail.com", "Password")
				.searchForProduct(SEARCH_TERM);
	}

	@Test(description = "Verify user successfully able to buy the products", groups = { "sanity", "e2e" })
	public void checkoutTest() {
		String result = searchResultPage.clickOnProductByIndex(0).selectProductSize(Size.L).addProductToCart()
				.proceedToCheckout().goToConfirmAddressPage().goToShipmentDetailsPage().goToPaymentPage().makePaymentByWire();
		
		Assert.assertEquals(result, "Your order on My Shop is complete.");
	}

}
