package com.cucumber.selenium.pageobjects;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.cucumber.selenium.domain.Details;
import com.cucumber.selenium.helpers.GlobalVariable;
import com.cucumber.selenium.helpers.WebDriverHelper;

public class BlountPage {
	private WebDriver wd;
	private WebDriverHelper wh;

	private final Logger logger = LogManager.getLogger();

	public BlountPage(WebDriver wd, WebDriverHelper wh) {
		PageFactory.initElements(wd, this);
		this.wd = wd;
		this.wh = wh;
	}

	@FindBy(name = "HTMPPIN")
	private WebElement ppinInput;

	@FindBy(name = "HTMSUBMIT")
	private WebElement submit;

	@FindBy(xpath = "//td/font/b[contains(text(),'OWNER')]/../../following-sibling::td/font")
	private WebElement owner;

	@FindBy(xpath = "//td/font/b[contains(text(),'ACCOUNT NUMBER')]/ancestor::td[1]/following-sibling::td/font")
	private WebElement taxAccountNumber;

	@FindBy(xpath = "//td/font/b[contains(text(),'PARCEL')]/ancestor::td[1]/following-sibling::td[1]/font")
	private WebElement parcelNumber;

	@FindBy(xpath = "//td/font/b[contains(text(),'OWNER')]/ancestor::tr[1]/following-sibling::tr[1]")
	private WebElement street1;

	@FindBy(xpath = "//td/font/b[contains(text(),'OWNER')]/ancestor::tr[1]/following-sibling::tr[2]")
	private WebElement street2;

	@FindBy(xpath = "//td/font/b[contains(text(),'OWNER')]/ancestor::tr[1]/following-sibling::tr[3]/td[2]")
	private WebElement remainingMailingAddress1;

	@FindBy(xpath = "//td/font/b[contains(text(),'OWNER')]/ancestor::tr[1]/following-sibling::tr[4]/td[2]")
	private WebElement remainingMailingAddress2;

	@FindBy(xpath = "//td/font/b[contains(text(),'TAX HISTORY')]/ancestor::tr[1]/following-sibling::tr")
	private List<WebElement> listOfTaxDues;

	@FindBy(xpath = "//td/font/b[contains(text(),'TAX SALES')]/ancestor::tr[1]/following-sibling::tr")
	private List<WebElement> listOfTaxSales;

	@FindBy(xpath = "//font/b[contains(text(),'ACRES')]/ancestor::td[1]/following-sibling::td")
	private WebElement acres;

	@FindBy(xpath = "//td/font/b[contains(text(),'TAX SALES')]/ancestor::tr[1]/following-sibling::tr")
	private List<WebElement> yearBuilt;

	@FindBy(xpath = "//font/b[contains(text(),'ADDRESS')]/ancestor::td[1]/following-sibling::td")
	private WebElement propertyAddress;

	@FindBy(xpath = "//font/b[contains(text(),'ASSESSED')]/ancestor::td[1]/following-sibling::td")
	private WebElement assessedValue;

	@FindBy(xpath = "//font[contains(text(),'NO TAX SALES FOUND')]")
	private WebElement noTaxSalesNotif;

	public boolean searchPPIN(String ppin) {
		ppinInput.click();
		ppinInput.clear();
		ppinInput.sendKeys(ppin);

		logger.debug("Click submit");
		submit.click();

		boolean isLinkTextPresent = wh.isElementPresent(By.xpath("//a/font[contains(text(),'" + ppin + "')]"), 10);
		if (isLinkTextPresent) {
			wd.findElement(By.xpath("//a/font[contains(text(),'" + ppin + "')]")).click();
		}
		return isLinkTextPresent;

	}

	public String getOwner() {
		return owner.getText();
	}

	public String getTaxAccount() {
		return taxAccountNumber.getText();
	}

	public Details getTaxDues() {
		Details details = new Details();
		for (int i = 1; i < listOfTaxDues.size(); i++) {
			WebElement taxDueElement = listOfTaxDues.get(i);
			boolean isNo = taxDueElement.findElement(By.cssSelector("td:nth-of-type(4)")).getText().contains("N");
			System.out.println(isNo);
			if (isNo) {
				String year = taxDueElement.findElement(By.cssSelector("td:nth-of-type(1)")).getText().replaceAll(" ",
						"");
				String taxDue = taxDueElement.findElement(By.cssSelector("td:nth-of-type(3)")).getText();
				System.out.println("Tax due for year: " + year + " is " + taxDue);

				switch (year) {
				case "2016":
					details.setCurrentYearTaxDue(taxDue);
					break;
				case "2015":
					details.setTaxDue2015(taxDue);
					break;
				case "2014":
					details.setTaxDue2014(taxDue);
					break;
				case "2013":
					details.setTaxDue2013(taxDue);
					break;
				case "2012":
					details.setTaxDue2012(taxDue);
					break;
				case "2011":
					details.setTaxDue2011(taxDue);
					break;
				case "2010":
					details.setTaxDue2010(taxDue);
					break;
				case "2009":
					details.setTaxDue2009(taxDue);
					break;
				}
			}

		}

		return details;

	}

	public Details getTaxSales() {
		Details details = new Details();
		boolean isNoTaxSalesNotifPresent = wh.isElementPresent(noTaxSalesNotif, 10);
		if (!isNoTaxSalesNotifPresent) {
			for (int i = 1; i < listOfTaxSales.size() - 1; i++) {
				WebElement taxSalesElement = listOfTaxSales.get(i);

				String year = taxSalesElement.findElement(By.cssSelector("td:nth-of-type(1)")).getText().replaceAll(" ",
						"");
				String soldTo = taxSalesElement.findElement(By.cssSelector("td:nth-of-type(2)")).getText();
				String[] redeemedDateBy = taxSalesElement.findElement(By.cssSelector("td:nth-of-type(4)")).getText()
						.split("  ");
				String redeemedDate = redeemedDateBy[0];
				String redeemedBy = redeemedDateBy[1];
				System.out.println("Sold to for year: " + year + " is " + soldTo);

				switch (year) {
				case "2016":
					details.setSoldTo2016(soldTo);
					details.setRedeemedDate2016(redeemedDate);
					details.setRedeemedBy2016(redeemedBy);
					break;
				case "2015":
					details.setSoldTo2015(soldTo);
					details.setRedeemedDate2015(redeemedDate);
					details.setRedeemedBy2015(redeemedBy);

					break;
				case "2014":
					details.setSoldTo2014(soldTo);
					details.setRedeemedDate2014(redeemedDate);
					details.setRedeemedBy2014(redeemedBy);
					break;
				case "2013":
					details.setSoldTo2013(soldTo);
					details.setRedeemedDate2013(redeemedDate);
					details.setRedeemedBy2013(redeemedBy);
					break;
				case "2012":
					details.setSoldTo2012(soldTo);
					details.setRedeemedDate2012(redeemedDate);
					details.setRedeemedBy2012(redeemedBy);
					break;
				case "2011":
					details.setSoldTo2011(soldTo);
					details.setRedeemedDate2011(redeemedDate);
					details.setRedeemedBy2011(redeemedBy);
					break;
				case "2010":
					details.setSoldTo2010(soldTo);
					details.setRedeemedDate2010(redeemedDate);
					details.setRedeemedBy2010(redeemedBy);
					break;
				case "2009":
					details.setSoldTo2009(soldTo);
					details.setRedeemedDate2009(redeemedDate);
					details.setRedeemedBy2009(redeemedBy);
					break;
				}

			}

		}
		return details;

	}

	public String getParcelNumber() {
		return parcelNumber.getText();
	}

	public String getMailingAddress() {
		String mailingAddress = null;
		if (street1.getText().contains("C/O")) {
			GlobalVariable.SECOND_OWNER = street1.getText().replace("C/O", "");
			mailingAddress = street2.getText() + "?" + remainingMailingAddress1.getText() + "?"
					+ remainingMailingAddress2.getText();
		} else if (street1.getText().contains("%")) {
			GlobalVariable.SECOND_OWNER = street1.getText().replace("%", "");
			mailingAddress = street2.getText() + "?" + remainingMailingAddress1.getText() + "?"
					+ remainingMailingAddress2.getText();
		} else if (street1.getText().contains("REM -")) {
			GlobalVariable.SECOND_OWNER = street1.getText().replace("REM -", "");
			mailingAddress = street2.getText() + "?" + remainingMailingAddress1.getText() + "?"
					+ remainingMailingAddress2.getText();
		} else {
			mailingAddress = street1.getText() + street2.getText() + "?" + remainingMailingAddress1.getText() + "?"
					+ remainingMailingAddress2.getText();
			GlobalVariable.SECOND_OWNER = null;

		}
		return mailingAddress;
	}

	public String getYearBuilt() {
		String year = null;
		if (!wh.isElementPresent(noTaxSalesNotif, 10)) {
			year = yearBuilt.get(yearBuilt.size() - 2).findElement(By.cssSelector("td")).getText();
		}
		return year;
	}

	public String getPropertyAddresss() {
		return propertyAddress.getText();
	}

	public String getAssessedValue() {
		return assessedValue.getText();
	}

	public String getAcres() {
		return acres.getText();
	}

	/*
	 * public String getMailingStreet() { return street1.getText() + " " +
	 * street2.getText(); }
	 * 
	 * public String getMailingCity() { String[] address =
	 * remainingMailingAddress.getText().split(","); String city = address[0];
	 * return city; }
	 * 
	 * public String getMailingState() { String[] address =
	 * remainingMailingAddress.getText().split(","); String state =
	 * address[1].split(" ")[1]; System.out.println(state); return state; }
	 * 
	 * public String getMailingZipCode() { String[] address =
	 * remainingMailingAddress.getText().split(","); String zipcode =
	 * address[1].split(" ")[2]; System.out.println(zipcode); return zipcode;
	 * 
	 * }
	 */
}
