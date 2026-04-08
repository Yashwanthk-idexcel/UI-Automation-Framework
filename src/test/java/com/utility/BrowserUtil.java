package com.utility;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import constants.Browser;

public abstract class BrowserUtil {

	private static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
	private WebDriverWait wait;

	public BrowserUtil(WebDriver driver) {
		super();
		this.driver.set(driver);
		wait = new WebDriverWait(driver, Duration.ofSeconds(30L));
	}

	public BrowserUtil(Browser browserName) {
		if (browserName == Browser.CHROME)
			driver.set(new ChromeDriver());
		else if (browserName == Browser.EDGE)
			driver.set(new EdgeDriver());
		else if (browserName == Browser.FIREFOX)
			driver.set(new FirefoxDriver());
		else
			System.err.print("Invalid Browser Name, Please provide chrome or edge");
		
		wait = new WebDriverWait(driver.get(), Duration.ofSeconds(30L));
	}

	// Headless execution
	public BrowserUtil(Browser browserName, boolean isHeadless) {
		if (browserName == Browser.CHROME) {
			if (isHeadless) {
				ChromeOptions options = new ChromeOptions();
				options.addArguments("--headless=old"); // take care of driver launches in headless mode
				options.addArguments("--window-size=1920,1080"); // Full screen mode even in headless mode
				driver.set(new ChromeDriver(options));
			} else
				driver.set(new ChromeDriver());

		} else if (browserName == Browser.EDGE)
			if (isHeadless) {
				EdgeOptions options = new EdgeOptions();
				options.addArguments("--headless=old"); // take care of driver launches in headless mode
				options.addArguments("disable-gpu");
				driver.set(new EdgeDriver(options));
			} else
				driver.set(new EdgeDriver());
		else if (browserName == Browser.FIREFOX)
			if (isHeadless) {
				FirefoxOptions options = new FirefoxOptions();
				options.addArguments("--headless=old"); // take care of driver launches in headless mode
				driver.set(new FirefoxDriver(options));
			} else
				driver.set(new FirefoxDriver());
		else
			System.err.print("Invalid Browser Name, Please provide chrome or edge");

		wait = new WebDriverWait(driver.get(), Duration.ofSeconds(30L));

	}

	public BrowserUtil(String browserName) {
		if (browserName.equalsIgnoreCase("chrome"))
			driver.set(new ChromeDriver());
		else if (browserName.equalsIgnoreCase("edge"))
			driver.set(new EdgeDriver());
		else
			System.err.print("Invalid Browser Name, Please provide chrome or edge");

		wait = new WebDriverWait(driver.get(), Duration.ofSeconds(30L));
	}

	public WebDriver getDriver() {
		return driver.get();
	}

	public void goToWebsite(String url) {
		driver.get().get(url);
	}

	public void maximizeWindow() {
		driver.get().manage().window().maximize();
	}

	public void clickOn(By locator) {
//		WebElement element = driver.get().findElement(locator);
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
		element.click();
	}

	public void clickOn(WebElement element) {
		element.click();
	}
	
	public void clickOnCheckbox(By locator) {
//		WebElement element = driver.get().findElement(locator);
		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		element.click();
	}

	public void clearText(By textBoxLocator) {
//		WebElement element = driver.get().findElement(textBoxLocator);
		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(textBoxLocator));
		element.clear();
	}

	public void enterText(By locator, String text) {
//		WebElement emailTextbox = driver.get().findElement(locator);
		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		element.sendKeys(text);
	}

	public void selectFromDropdown(By locator, String optionToSelect) {
		WebElement dropdownField = driver.get().findElement(locator);
		Select select = new Select(dropdownField);
		select.selectByVisibleText(optionToSelect);
	}

	public void enterSpecialKey(By locator, Keys keysToEnter) {
//		WebElement element = driver.get().findElement(locator);
		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		element.sendKeys(keysToEnter);
	}

	public String getVisibleTest(By locator) {
		WebElement elementXpath = driver.get().findElement(locator);
		return elementXpath.getText();
	}

	public String getVisibleTest(WebElement element) {
		return element.getText();
	}

	public List<String> getAllVisibleTest(By locator) {
		List<WebElement> elementXpath = driver.get().findElements(locator);
		List<String> visibleTextList = new ArrayList<String>();

		for (WebElement webElement : elementXpath) {
			visibleTextList.add(getVisibleTest(webElement));
		}

		return visibleTextList;
	}

	public List<WebElement> getAllWebElements(By locator) {
		List<WebElement> elementXpath = driver.get().findElements(locator);
		return elementXpath;
	}

	public String takeScreenshot(String testName) {

		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("HH-MM-SS");
		String timestamp = formatter.format(date);

		String path = "./screenshots/" + testName + " - " + timestamp + ".png";
		TakesScreenshot screenshot = (TakesScreenshot) driver.get();
		File screenshotData = screenshot.getScreenshotAs(OutputType.FILE);
		File screenshotFile = new File(path);
		try {
			FileUtils.copyFile(screenshotData, screenshotFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return path;
	}

}
