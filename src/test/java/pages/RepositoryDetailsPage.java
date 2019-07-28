package pages;

import org.openqa.selenium.By;

import commonUtils.SeleniumUtils;

public class RepositoryDetailsPage extends SeleniumUtils {

	public static class RepoDetailsPage_locators {

		public static final By forkBtn = By.xpath("//button[contains(@title,'Fork your')]");
		public static final By starBtn = By.xpath("//button[contains(@title,'Star')]");
		public static final By unStarBtn = By.xpath("//button[contains(@title,'Unstar')]");
	}

	public void clickForkBtn(String pageTitle) {

		waitForElementPresense_AndClick(RepoDetailsPage_locators.forkBtn);
		logger.info("Forked the repo: " + pageTitle);
	}

	public boolean verifyUnStarBtn() {

		return isElementDisplayed(RepoDetailsPage_locators.unStarBtn);
	}

	public void clickUnStarBtn(String pageTitle) {

		logger.info("Repo: " + pageTitle + " is already starred, marking it as UnStar first.");
		waitForElementPresense_AndClick(RepoDetailsPage_locators.unStarBtn);
		logger.info("Unstarred the repo: " + pageTitle);
	}

	public void clickStarBtn(String pageTitle) {

		waitForElementPresense_AndClick(RepoDetailsPage_locators.starBtn);
		logger.info("Starred the repo: " + pageTitle);
	}

	public void verifyStarBtn_clickStarBtn(String pageTitle) {

		if (verifyUnStarBtn()) {
			// we can ignore this step if we don't want to Star Repo which ones are already
			// Starred.
			clickUnStarBtn(pageTitle);
			clickStarBtn(pageTitle);
		} else {
			clickStarBtn(pageTitle);
		}

	}
}
