package pages;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;

import commonUtils.SeleniumUtils;

public class SearchResultPage extends SeleniumUtils {

	static int i = 0;
	RepositoryDetailsPage objRepoDetails = new RepositoryDetailsPage();

	public static class SearchResultPage_locators {

		public static final By searchResultList = By.xpath("//li[contains(@class,'repo-list-item')]/div/h3/a");
	}

	public void fork_And_Star_ReposList() {

		for (i = 0; i < 5; i++) {

			findMultipleElements_openNewTab(SearchResultPage_locators.searchResultList, i);
		}

		logger.info("Opened top 5 repositories in new tabs.");

		String parent = driver.getWindowHandle();

		Set<String> currentWindows = driver.getWindowHandles();
		Iterator<String> itr = currentWindows.iterator();

		while (itr.hasNext()) {

			String child_window = itr.next();

			if (!parent.equals(child_window)) {

				driver.switchTo().window(child_window);
				String pageTitle = getPageTitle();
				logger.info("Switched to child window named: " + pageTitle + " and now forking, starring repos.");
				objRepoDetails.clickForkBtn(pageTitle);
				pageTitle = getPageTitle();
				objRepoDetails.verifyStarBtn_clickStarBtn(pageTitle);
			}
		}
	}
}
