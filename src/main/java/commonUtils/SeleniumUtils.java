package commonUtils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SeleniumUtils {

	protected static WebDriver driver;
	protected static Logger logger = LogManager.getLogger(SeleniumUtils.class.getName());
	protected static String basePath = System.getProperty("user.dir");
	protected static Properties prop;

	public void propFile(String filePath) {

		try {

			prop = new Properties();
			FileInputStream inputStream = new FileInputStream(basePath + filePath);
			prop.load(inputStream);

		} catch (FileNotFoundException Ex) {
			logger.info("File not found" + Ex.getMessage());

		} catch (IOException e) {
			logger.info("Exception occured" + e.getMessage());
		}
	}

	public void openBrowser(String browser, String filePath) {

		if (browser.contains("chrome")) {
			System.setProperty("webdriver.chrome.driver", basePath + "/chromedriver");
			driver = new ChromeDriver();
		} else if (browser.contains("firefox")) {
			System.setProperty("webdriver.gecko.driver", basePath + "/geckodriver");
			driver = new FirefoxDriver();
		} else if (browser.contains("headlessChrome")) {
			System.setProperty("webdriver.chrome.driver", basePath + "/chromedriver");
			ChromeOptions options = new ChromeOptions();
			options.addArguments("headless");
			driver = new ChromeDriver(options);
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		logger.info("Browser getting launched.");
		propFile(filePath);
	}

	public static void getURL(String url) {
		driver.get(url);
		logger.info("Opening website: " + url + " in browser.");
	}

	public static void clickElement(By locator) {
		driver.findElement(locator).click();
	}

	public static void sendKeysInTextBox(By locator, String enterText) {
		driver.findElement(locator).clear();
		driver.findElement(locator).sendKeys(enterText);
	}

	public static void waitForElementVisibility_AndClick(By locator) {
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).click();
	}

	public static void waitForElementPresense_AndClick(By locator) {
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.presenceOfElementLocated(locator)).click();
	}

	public static void waitForElementVisibility_AndSendKeys(By locator, String enterText) {
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).sendKeys(enterText);
	}

	public static void waitForElementPresense_AndSendKeys(By locator, String enterText) {
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.presenceOfElementLocated(locator)).sendKeys(enterText);
	}

	public static void waitForElementPresense_AndSendKeys_AndPressEnter(By locator, String enterText) {
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.presenceOfElementLocated(locator)).sendKeys(enterText, Keys.ENTER);
	}

	public static void findMultipleElements_openNewTab(By locator, int index) {
		String clickOnLink = Keys.chord(Keys.CONTROL, Keys.ENTER);
		driver.findElements(locator).get(index).sendKeys(clickOnLink);
	}

	public static void traverseMultipleWindows() {

		Set<String> currentWindows = driver.getWindowHandles();
		Iterator<String> itr = currentWindows.iterator();

		while (itr.hasNext()) {
			driver.switchTo().window(itr.next());
		}
	}

	public static boolean isElementPresent(By locator) {
		boolean flag = true;
		try {
			driver.findElement(locator);
		} catch (Exception e) {
			flag = false;
			logger.error("Exception occured while locating the element: " + e.getMessage());
		}
		return flag;
	}

	public static boolean isElementDisplayed(By locator) {
		boolean flag = true;
		try {
			driver.findElement(locator).isDisplayed();
		} catch (Exception e) {
			flag = false;
			logger.error("Exception occured while locating the element: " + e.getMessage());
		}
		return flag;
	}

	public static String getPageTitle() {

		return driver.getTitle();
	}

	public void tearDown() {
		logger.info("Closing the browser");
		driver.quit();
	}
}
