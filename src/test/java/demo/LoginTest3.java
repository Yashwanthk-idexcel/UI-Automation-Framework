package demo;

import com.ui.pages.HomePage;

import constants.Browser;

public class LoginTest3 {

	public static void main(String[] args) {
		HomePage homePage = new HomePage(Browser.CHROME, true);
	
		String userName = homePage.goToLoginPage().doLoginWith("yash@gmail.com", "password").getUserName();
		System.out.println(userName);		
	}

}
