package com.cucumber.selenium.pageobjects;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import com.cucumber.selenium.domain.Details;
import com.cucumber.selenium.helpers.WebDriverHelper;

public class SearchResultPage {
	private WebDriver wd;
	private WebDriverHelper wh;

	public SearchResultPage(WebDriver driver, WebDriverHelper wh) {
		PageFactory.initElements(driver, this);
		this.wd = driver;
		this.wh = wh;
	}

	@FindBy(xpath = "//h5[contains(text(),'The total amount due')]")
	private WebElement amountDue;

	@FindBy(xpath = "//th/font[contains(text(),'Default Date')]/ancestor::tr[1]/following-sibling::tr/td")
	private WebElement defaultDate;

	public String getTotalAmountDue() {
		String totalAmountDue;
		if (wh.isElementPresent(amountDue, 10)) {
			totalAmountDue = amountDue.getText();
			totalAmountDue = totalAmountDue.substring(totalAmountDue.lastIndexOf("parcel is $") + 1);

		} else {
			totalAmountDue = wd.findElement(By.xpath("//h4")).getText();
		}
		return totalAmountDue;

	}

	public String getDefaultDate() {
		String defaltDueDate;
		if (wh.isElementPresent(defaultDate, 10)) {
			defaltDueDate = defaultDate.getText();
		} else {
			defaltDueDate = wd.findElement(By.xpath("//h4")).getText();
		}

		return defaltDueDate;

	}
}
