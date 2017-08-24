package com.cucumber.selenium.stepdefinition.searchURL;

import java.util.ArrayList;
import java.util.List;

import com.cucumber.selenium.domain.Details;
import com.cucumber.selenium.helpers.ExcelUtility;

public class Logging {

	public static void main(String[] args) {
		List<Details> listOfDetails = new ArrayList<Details>();
		Details details = new Details();
		details.setAmountDue("hi");
		details.setUrl("https://www.mytaxcollector.com/trPropInfo_CurrentTaxes.aspx?parcel=0607241110000");
		listOfDetails.add(details);
		ExcelUtility.updateDetails(listOfDetails);

	}

}
