package com.cucumber.selenium.helpers;

import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebDriverHelper {

	private static final Logger logger = LogManager.getLogger();
	private WebDriver driver;

	public WebDriverHelper(WebDriver driver) {
		this.driver = driver;
	}

	public void sendKeys(WebElement element, String value) {
		if (value.isEmpty()) {
			logger.warn(LogInformationCategories.TEST_DATA.getCategory() + LoggerMessages.DELIMITER
					+ LoggerMessages.BLANK_TESTDATA);
		}
		element.click();
		element.clear();
		element.sendKeys(value);

		if (element.getAttribute("value").equals(value)) {
			logger.info(LogInformationCategories.BROWSER_ACTION.getCategory() + LoggerMessages.DELIMITER
					+ LoggerMessages.TYPE_VALUE, value, element.getAttribute("id"));
		}
	}

	public void sendKeys(By locator, String value) {
		driver.findElement(locator).click();
		driver.findElement(locator).clear();
		driver.findElement(locator).sendKeys(value);

		if (driver.findElement(locator).getAttribute("value").equals(value)) {
			logger.info(LogInformationCategories.BROWSER_ACTION.getCategory() + LoggerMessages.DELIMITER
					+ LoggerMessages.TYPE_VALUE, value, driver.findElement(locator).getAttribute("id"));
		}
	}

	public void waitForElementPresence(final WebElement searchField) {
		isElementPresent(searchField, 500);
	}

	public boolean isElementPresent(WebElement element, int seconds) {
		boolean isElementPresent = true;
		int count = 0;

		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);

		while (isElementPresent) {
			try {
				if (element.isDisplayed()) {
					break;
				} else {
					if (count == seconds) {
						isElementPresent = false;
						break;
					}

					count++;
				}
			} catch (Exception ex) {
				if (count == seconds) {
					isElementPresent = false;
					break;
				}

				count++;
			}
		}

		// TODO: factor
		if (!isElementPresent) {
			if (isPageLoaded()) {
				logger.warn(LogInformationCategories.CODE.getCategory() + LoggerMessages.DELIMITER
						+ LoggerMessages.LOCATOR_CHANGE);
			} else {
				logger.warn(LogInformationCategories.CODE.getCategory() + LoggerMessages.DELIMITER
						+ LoggerMessages.PAGE_TIMEOUT);
			}
		}

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		return isElementPresent;
	}

	public boolean isElementPresent(By elementBy, int seconds) {

		boolean isElementPresent = true;
		int count = 0;

		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);

		while (isElementPresent) {
			try {
				if (driver.findElement(elementBy).isDisplayed()) {
					break;
				} else {
					if (count == seconds) {
						isElementPresent = false;
						break;
					}

					count++;
				}
			} catch (Exception ex) {
				if (count == seconds) {
					isElementPresent = false;
					break;
				}

				count++;
			}
		}

		// TODO: factor
		if (!isElementPresent) {
			if (isPageLoaded()) {
				logger.warn(LogInformationCategories.CODE.getCategory() + LoggerMessages.DELIMITER
						+ LoggerMessages.LOCATOR_CHANGE);
			} else {
				logger.warn(LogInformationCategories.CODE.getCategory() + LoggerMessages.DELIMITER
						+ LoggerMessages.PAGE_TIMEOUT);
			}
		}

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		return isElementPresent;
	}

	public void scrollIntoView(By object) {
		WebElement objElement = driver.findElement(object);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].scrollIntoView();", objElement);
		executor.executeScript("window.scrollBy(0, -150);");
	}

	public void scrollIntoView(WebElement element) {
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].scrollIntoView();", element);
		executor.executeScript("window.scrollBy(0, -150);");
	}

	public void waitForPageLoaded() {
		ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return isPageLoaded();
			}
		};

		WebDriverWait wait = new WebDriverWait(driver, 500);
		wait.until(expectation);
	}

	public void handleAlert() {
		Alert alert = driver.switchTo().alert();
		alert.accept();
	}

	private boolean isPageLoaded() {
		return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
	}

	public void waitForElementToDisappear(By by, int seconds) {
		boolean isOverLayPresent = true;
		int count = 0;

		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		while (isOverLayPresent) {
			try {
				if (!driver.findElement(by).isDisplayed()) {
					logger.debug("Element disappear after " + count + " counts");
					isOverLayPresent = false;
					break;
				} else {
					if (count == seconds) {
						logger.debug("Element does not disappear after " + count + " counts");
						break;
					}
					count++;
				}

			} catch (NoSuchElementException e) {
				logger.debug("Element is not present after " + count + " counts");
				isOverLayPresent = false;
				break;

			} catch (Exception e) {
				if (e.getMessage().contains("element is not attached to the page document")) {
					logger.debug("Element is not attached to the page document after " + count + " counts");
					isOverLayPresent = false;
					break;
				}

			}
		}

	}

	public void waitForElementToBeClickable(WebElement element, int seconds) {
		WebDriverWait wait = new WebDriverWait(driver, seconds);
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	public void scrollToBottom() {
		JavascriptExecutor jse = ((JavascriptExecutor) driver);
		jse.executeScript("window.scrollTo(0, document.body.scrollHeight)");
	}

	public Document parsePage() {
		String html = driver.getPageSource();
		Document doc = Jsoup.parse(html);
		return doc;
	}
}
