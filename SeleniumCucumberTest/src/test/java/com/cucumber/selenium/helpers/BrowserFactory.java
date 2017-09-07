package com.cucumber.selenium.helpers;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class BrowserFactory {
	private static final String CHROME_DRIVER_KEY = "webdriver.chrome.driver";
	private static final String IE_DRIVER_KEY = "webdriver.ie.driver";
	private static final String FIREFOX_DRIVER_KEY = "webdriver.gecko.driver";
	private static final String PHANTOMJS_DRIVER_KEY = "phantomjs.binary.path";

	private static final Logger logger = LogManager.getLogger();

	public static WebDriver getBrowser(String browser, String driverFolderPath) {
		WebDriver driver = null;

		try {
			if (browser.equalsIgnoreCase("Chrome")) {
				System.setProperty(CHROME_DRIVER_KEY, driverFolderPath + "chromedriver.exe");
				ChromeOptions chOption = new ChromeOptions();
				chOption.addArguments("--disable-extensions");
				chOption.addArguments("--disable-notifications");
				chOption.addArguments("test-type");
				Map<String, Object> prefs = new HashMap<String, Object>();
				prefs.put("credentials_enable_service", false);
				prefs.put("profile.password_manager_enabled", false);
				chOption.setExperimentalOption("prefs", prefs);

				driver = new ChromeDriver(chOption);
			} else if (browser.equalsIgnoreCase("IE")) {
				System.setProperty(IE_DRIVER_KEY, driverFolderPath + "IEDriverServer.exe");
				DesiredCapabilities cap = DesiredCapabilities.internetExplorer();
				cap.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
				cap.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);
				cap.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
				driver = new InternetExplorerDriver(cap);

			} else if (browser.equalsIgnoreCase("Firefox")) {
				driver = new FirefoxDriver();
				System.setProperty(FIREFOX_DRIVER_KEY, driverFolderPath + "geckodriver.exe");
				DesiredCapabilities capabilities = DesiredCapabilities.firefox();
				capabilities.setCapability("marionette", false);
				driver = new FirefoxDriver(capabilities);
			} else if (browser.equalsIgnoreCase("PhantomJS")) {
				System.setProperty(PHANTOMJS_DRIVER_KEY, driverFolderPath + "phantomjs.exe");
				driver = new PhantomJSDriver();
			}

		} catch (Exception e) {
			e.printStackTrace();
			logger.error(LogInformationCategories.EXCEPTION.getCategory() + " - " + e.getMessage());
		}

		return driver;
	}

	
}
