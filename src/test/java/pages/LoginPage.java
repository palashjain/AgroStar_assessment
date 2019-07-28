package pages;

import org.openqa.selenium.By;

import commonUtils.SeleniumUtils;

public class LoginPage extends SeleniumUtils {

	public static class LoginPage_locators {

		public static final By userNameTextBox = By.id("login_field");
		public static final By passwordTextBox = By.id("password");
		public static final By signInBtn = By.name("commit");
	}

	public void loginGitHub(String userName, String password) {

		waitForElementPresense_AndSendKeys(LoginPage_locators.userNameTextBox, userName);
		waitForElementPresense_AndSendKeys(LoginPage_locators.passwordTextBox, password);
		waitForElementPresense_AndClick(LoginPage_locators.signInBtn);
		logger.info("Logging In GitHub using UserName: " + userName + " and Password: " + password);
	}
}
