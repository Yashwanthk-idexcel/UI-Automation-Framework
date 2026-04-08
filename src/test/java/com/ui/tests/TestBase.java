package com.ui.tests;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.ui.pages.HomePage;
import com.utility.BrowserUtil;
import com.utility.LambdaTestUtil;
import com.utility.LoggerUtil;

import constants.Browser;

// Parent of all the test class
public class TestBase {

	protected HomePage homePage;
	Logger logger = LoggerUtil.getLogger(this.getClass());
	private boolean isLambdaTest;

	@BeforeMethod(description = "Load the homepage of the website")
	@Parameters({ "browser", "isLambdaTest", "isHeadless" })
	public void setup(
			@Optional("chrome") String browser, @Optional("false") boolean isLambdaTest, @Optional("false") boolean isHeadless, ITestResult result) {

		WebDriver lambdaDriver;
		this.isLambdaTest = isLambdaTest;

		if (isLambdaTest) {
			lambdaDriver = LambdaTestUtil.initializeLambdaTestSession(browser, result.getMethod().getMethodName());
			homePage = new HomePage(lambdaDriver);

		} else {
			logger.info("Loads the Homepage of the website, in Headless mode");
			homePage = new HomePage(Browser.valueOf(browser.toUpperCase()), isHeadless);
		}
	}

	public BrowserUtil getBrowserInstance() {
		return homePage;
	}

	@AfterMethod(description = "Tear down the browser")
	public void tearDown() {

		if (isLambdaTest)
			LambdaTestUtil.quitSession();
		else
			((WebDriver) homePage).quit();
	}

}
