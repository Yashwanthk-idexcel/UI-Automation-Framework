package com.ui.pages;

import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.utility.BrowserUtil;

public class SearchResultPage extends BrowserUtil {

	private static final By PRODUCT_LISTING_TITLE_LOCATOR = By.xpath("//span[@class=\"lighter\"]");
	private static final By SEARCHED_PRODUCT_LIST_LOCATOR = By.xpath("//h5[@itemprop=\"name\"]/a");

	public SearchResultPage(WebDriver driver) {
		super(driver);
	}
	
	public String getSearchResultTitle() {
		return getVisibleTest(PRODUCT_LISTING_TITLE_LOCATOR);
	}
	
	public boolean verifySearchTextPresentInResultProductNames(String searchedText) {
		List<String> keywordsList = Arrays.asList(searchedText.toLowerCase().split(" "));
		List<String> resultProductNamesList = getAllVisibleTest(SEARCHED_PRODUCT_LIST_LOCATOR);
		
		return resultProductNamesList.stream().anyMatch(resultName -> (keywordsList.stream().anyMatch(resultName.toLowerCase()::contains)));
	}
	
	public ProductDetailsPage clickOnProductByIndex(int index) {
		WebElement elementRef = getAllWebElements(SEARCHED_PRODUCT_LIST_LOCATOR).get(index);
		clickOn(elementRef);
		return new ProductDetailsPage(getDriver());
	}

}
