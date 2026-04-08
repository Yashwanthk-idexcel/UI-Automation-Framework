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

@Listeners({com.ui.listeners.TestListener.class})
public class LoginTest extends TestBase {
	Logger logger = LoggerUtil.getLogger(this.getClass());

	@Test(description = "Verifies login credentials passing via json file", groups = { "sanity", "e2e", "json" }, 
			dataProviderClass = com.ui.dataproviders.LoginDataProvider.class , dataProvider = "LoginTestJsonDataProvider")
	public void loginTestViaJson(User user) {
		String userName = homePage.goToLoginPage().doLoginWith(user.getEmail(), user.getPassword()).getUserName();
		Assert.assertEquals(userName, "Jatin Sharma");
	}

	@Test(description = "Verifies login credentials passing via csv file", groups = { "sanity", "e2e", "csv" }, 
			dataProviderClass = com.ui.dataproviders.LoginDataProvider.class , dataProvider = "LoginTestCsvDataProvider")
	public void loginTestViaCsv(User user) {
		String userName = homePage.goToLoginPage().doLoginWith(user.getEmail(), user.getPassword()).getUserName();
		Assert.assertEquals(userName, "Jatin Sharma");
	}
	
	@Test(description = "Verifies login credentials passing via excel file", groups = { "sanity", "e2e", "excel" }, 
			dataProviderClass = com.ui.dataproviders.LoginDataProvider.class , dataProvider = "LoginTestExcelDataProvider",
			retryAnalyzer = com.ui.listeners.MyRetryAnalyzer.class)
	public void loginTestViaExcel(User user) {
		String userName = homePage.goToLoginPage().doLoginWith(user.getEmail(), user.getPassword()).getUserName();
		Assert.assertEquals(userName, "Jatin Sharma");
	}
}
