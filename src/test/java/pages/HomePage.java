package pages;

import org.openqa.selenium.By;

import commonUtils.SeleniumUtils;

public class HomePage extends SeleniumUtils {

	public static class HomePage_locators {

		public static final By signInBtn = By.linkText("Sign in");
	}

	public static class LoggedIn_HomePage_locators {

		public static final By searchTextBox = By.xpath("//input[@name='q']");
	}

	public void clickSignInBtn() {

		waitForElementPresense_AndClick(HomePage_locators.signInBtn);
		logger.info("Clicked on Sign In Button on HomePage, let's login.");
	}

	public void searchProgLang(String searchLang) {

		waitForElementPresense_AndSendKeys_AndPressEnter(LoggedIn_HomePage_locators.searchTextBox, searchLang);
		logger.info("Searching repositories for language: " + searchLang);
	}
}
