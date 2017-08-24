package com.cucumber.selenium.helpers;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;

public class Hooks {
	private static final Logger logger = LogManager.getLogger();
	public static WebDriver driver;
	public static WebDriverHelper wh;

	@Before
	/**
	 * Delete all cookies at the start of each scenario to avoid shared state
	 * between tests
	 */
	public void openBrowser(Scenario scenario) {
		logger.info(LogInformationCategories.BROWSER_ACTION.getCategory() + " - Called openBrowser for scenario {}",
				scenario.getId());

		String browser = "";
		String driverFolderPath = "";

		try {

			Properties properties = new PropertyFileUtility(PropertyFile.PROPERTY_FILE_LOCATION).getProperties();
			driverFolderPath = properties.getProperty(PropertyFile.DRIVER_LOCATION);
			browser = properties.getProperty(PropertyFile.BROWSER);
			driver = BrowserFactory.getBrowser(browser, driverFolderPath);
			driver.manage().deleteAllCookies();
			driver.manage().window().maximize();

		} catch (NullPointerException n) {
			logger.error(LogInformationCategories.EXCEPTION.getCategory() + " - WebDriver is null.");
		} catch (Exception e) {
			logger.error(LogInformationCategories.EXCEPTION.getCategory() + " - " + e.getMessage());
		}

		wh = new WebDriverHelper(driver);
	}

	//@After
	/**
	 * Embed a screenshot in test report if test is marked as failed
	 */
	public void embedScreenshot(Scenario scenario) throws Exception {
		Properties properties = new PropertyFileUtility(PropertyFile.PROPERTY_FILE_LOCATION).getProperties();
		String screenshotLocation = properties.getProperty(PropertyFile.SCREENSHOTS_FOLDER);

		if (scenario.isFailed()) {
			logger.info(scenario.getName() + " - Failed!");
			try {
				// to do
				scenario.write("Current Page URL is " + driver.getCurrentUrl());
				// byte[] screenshot = getScreenshotAs(OutputType.BYTES);
				byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
				File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
				String destDir = screenshotLocation + scenario.getName();

				Thread.sleep(3000);

				// Set file name using current date time
				String destFile = "image " + getCurrentDateTime() + ".png";

				// Copy paste file at destination folder location
				FileUtils.copyFile(scrFile, new File(destDir + "/" + destFile));
				scenario.embed(screenshot, "image/png");

			} catch (WebDriverException wde) {
				logger.error(wde.getMessage());
			} catch (IOException e) {
				logger.error(e.getMessage());
			} catch (InterruptedException e) {
				logger.error(e.getMessage());
			} finally {
				driver.close();
				driver.quit();
			}
		} else {
			logger.info(scenario.getName() + " - Passed!");
		}

		driver.quit();
	}

	public String getCurrentDateTime() {
		DateFormat dtf = new SimpleDateFormat("ddMMMyyyy_hh-mm-ssaa");
		String dateTime = dtf.format(new Date());
		return dateTime;
	}

}
