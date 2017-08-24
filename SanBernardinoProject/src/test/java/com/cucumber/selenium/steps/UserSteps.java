package com.cucumber.selenium.steps;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;

import com.cucumber.selenium.domain.Details;
import com.cucumber.selenium.helpers.GlobalVariable;
import com.cucumber.selenium.helpers.WebDriverHelper;
import com.cucumber.selenium.pageobjects.BlountPage;
import com.cucumber.selenium.pageobjects.SearchResultPage;

public class UserSteps {
	private SearchResultPage searchResultPage;
	private BlountPage blountPage;
	private WebDriverHelper wh;

	public UserSteps(WebDriver wd, WebDriverHelper wh) {
		searchResultPage = new SearchResultPage(wd, wh);
		blountPage = new BlountPage(wd, wh);
		this.wh = wh;
	}

	public Details getDetails(String url, String page, String ppin) {
		Details details = new Details();
		switch (page) {
		case "san bernardino":
			details.setUrl(url);
			details.setAmountDue(searchResultPage.getTotalAmountDue());
			details.setDefaultDate(searchResultPage.getDefaultDate());

			break;

		case "blount":
			details.setUrl(url);
			details.setPPIN(ppin);
			details.setOwner(blountPage.getOwner());
			details.setTaxAccount(blountPage.getTaxAccount());
			details.setParcelNumber(blountPage.getParcelNumber());
			String mailingAddress = blountPage.getMailingAddress().replace("\n", "").replace("\r", "")
					.replaceAll("\\s+", " ");
			System.out.println("Mailing address : " + mailingAddress);
			String stateAndzipcode = mailingAddress.substring(mailingAddress.lastIndexOf(",") + 1);
			System.out.println(mailingAddress);
			String[] stateZip = stateAndzipcode.split(" ");
			String city = null;
			System.out.println("hi" + (mailingAddress.substring(mailingAddress.lastIndexOf("?") + 1)));

			if (!(mailingAddress.substring(mailingAddress.lastIndexOf("?") + 1).equals(" "))) {
				city = mailingAddress.substring(mailingAddress.lastIndexOf("?") + 1, mailingAddress.indexOf(","));
			} else if (!(mailingAddress.substring(mailingAddress.indexOf(",") + 1).equals(" "))) {
				city = mailingAddress.substring(mailingAddress.indexOf("?") + 1, mailingAddress.lastIndexOf(","));
			} else {
				city = mailingAddress.substring(mailingAddress.indexOf("?") + 1, mailingAddress.indexOf(","));
			}

			mailingAddress = mailingAddress.replace("?", "");
			String state = stateZip[1];
			String zipcode = stateZip[2];
			String street = mailingAddress.split(city)[0];
			System.out.println("Street: " + street);
			System.out.println("City: " + city);
			System.out.println("State: " + state);
			System.out.println("Zipcode: " + zipcode);
			details.setMailingStreet(street);
			details.setMailingCity(city);
			details.setMailingState(state);
			details.setMailingZipCode(zipcode);
			details.setInCare(GlobalVariable.SECOND_OWNER);

			details.setAcre(blountPage.getAcres());
			details.setPropertyAddress(blountPage.getPropertyAddresss());
			details.setAssessedValue(blountPage.getAssessedValue());
			details.setYearBuilt(blountPage.getYearBuilt());

			System.out.println("Acres: " + blountPage.getAcres());
			System.out.println("Property Address: " + blountPage.getPropertyAddresss());
			System.out.println("Assessed value: " + blountPage.getAssessedValue());
			System.out.println("Year Built: " + blountPage.getYearBuilt());

			Details taxDueDetails = blountPage.getTaxDues();

			details.setCurrentYearTaxDue(taxDueDetails.getCurrentYearTaxDue());
			details.setTaxDue2015(taxDueDetails.getTaxDue2015());
			details.setTaxDue2014(taxDueDetails.getTaxDue2014());
			details.setTaxDue2013(taxDueDetails.getTaxDue2013());
			details.setTaxDue2012(taxDueDetails.getTaxDue2012());
			details.setTaxDue2011(taxDueDetails.getTaxDue2011());
			details.setTaxDue2010(taxDueDetails.getTaxDue2010());
			details.setTaxDue2009(taxDueDetails.getTaxDue2009());
			System.out.println("Tax Due 2016: " + taxDueDetails.getCurrentYearTaxDue());
			System.out.println("Tax Due 2015: " + taxDueDetails.getTaxDue2015());
			System.out.println("Tax Due 2014: " + taxDueDetails.getTaxDue2014());
			System.out.println("Tax Due 2013: " + taxDueDetails.getTaxDue2013());
			System.out.println("Tax Due 2012: " + taxDueDetails.getTaxDue2012());
			System.out.println("Tax Due 2011: " + taxDueDetails.getTaxDue2011());
			System.out.println("Tax Due 2010: " + taxDueDetails.getTaxDue2010());
			System.out.println("Tax Due 2009: " + taxDueDetails.getTaxDue2009());

			Details taxSales = blountPage.getTaxSales();

			details.setSoldTo2016(taxSales.getSoldTo2016());
			details.setSoldTo2015(taxSales.getSoldTo2015());
			details.setSoldTo2014(taxSales.getSoldTo2014());
			details.setSoldTo2013(taxSales.getSoldTo2013());
			details.setSoldTo2012(taxSales.getSoldTo2012());
			details.setSoldTo2011(taxSales.getSoldTo2011());
			details.setSoldTo2010(taxSales.getSoldTo2010());
			details.setSoldTo2009(taxSales.getSoldTo2009());

			details.setRedeemedBy2016(taxSales.getRedeemedBy2016());
			details.setRedeemedBy2015(taxSales.getRedeemedBy2015());
			details.setRedeemedBy2014(taxSales.getRedeemedBy2014());
			details.setRedeemedBy2013(taxSales.getRedeemedBy2013());
			details.setRedeemedBy2012(taxSales.getRedeemedBy2012());
			details.setRedeemedBy2011(taxSales.getRedeemedBy2011());
			details.setRedeemedBy2010(taxSales.getRedeemedBy2010());
			details.setRedeemedBy2009(taxSales.getRedeemedBy2009());

			details.setRedeemedDate2016(taxSales.getRedeemedDate2016());
			details.setRedeemedDate2015(taxSales.getRedeemedDate2015());
			details.setRedeemedDate2014(taxSales.getRedeemedDate2014());
			details.setRedeemedDate2013(taxSales.getRedeemedDate2013());
			details.setRedeemedDate2012(taxSales.getRedeemedDate2012());
			details.setRedeemedDate2011(taxSales.getRedeemedDate2011());
			details.setRedeemedDate2010(taxSales.getRedeemedDate2010());
			details.setRedeemedDate2009(taxSales.getRedeemedDate2009());
		}

		return details;
	}

	public boolean searchPPIN(String ppin) {

		return blountPage.searchPPIN(ppin);
	}

}
