package com.ui.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ui.pages.AddressPage;
import com.ui.pages.MyAccountPage;
import com.ui.pojo.AddressPojo;
import com.utility.FakeAddressUtil;

@Listeners(com.ui.listeners.TestListener.class)
public class AddNewFirstAddressTest extends TestBase {
	private MyAccountPage myAccountPage;
	private AddressPojo address;

	@BeforeMethod(description = "valid user logs in for the first time and not added his address information yet")
	public void setUp() {
		myAccountPage = homePage.goToLoginPage().doLoginWith("yashwanthkgmail.com", "Password");
		address = FakeAddressUtil.getFakeAddress();
	}

	@Test
	public void addNewAddress() {
		String newAddress = myAccountPage.goToAddAddressPage().saveAddress(address);
		Assert.assertEquals(newAddress, address.getAddressAlias().toUpperCase());
	}

}
