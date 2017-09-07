package com.cucumber.selenium.helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class PhantomTest {
	public static void main(String[] args) {
		 DesiredCapabilities capabilities = DesiredCapabilities.phantomjs();
		    PhantomJSDriver driver = new PhantomJSDriver(capabilities);
		driver.get("http://www.google.com");

		WebElement element = driver.findElement(By.name("q"));
		element.sendKeys("Cheese!");
		element.submit();

		System.out.println("Page title is: " + driver.getTitle());

		driver.quit();
	}
}
