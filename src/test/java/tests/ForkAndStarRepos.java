package tests;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commonUtils.SeleniumUtils;
import pages.HomePage;
import pages.LoginPage;
import pages.SearchResultPage;

public class ForkAndStarRepos extends SeleniumUtils {

	HomePage objHome = new HomePage();
	LoginPage objLogin = new LoginPage();
	SearchResultPage objSearch = new SearchResultPage();

	@BeforeClass
	@Parameters({ "browser", "filePath" })
	public void openWebsite(String browser, String filePath) {

		openBrowser(browser, filePath);
		getURL(prop.getProperty("url"));
	}

	@Test(priority = 1)
	public void loginToGitHub() {

		objHome.clickSignInBtn();
		objLogin.loginGitHub(prop.getProperty("username"), prop.getProperty("password"));

	}

	@Test(priority = 2)
	public void search_Fork_Star_repos() {

		objHome.searchProgLang(prop.getProperty("searchLang"));
		objSearch.fork_And_Star_ReposList();
	}

	@AfterClass
	public void closeBrowser() {
		tearDown();
	}
}
