package com.cucumber.selenium.stepdefinition.searchURL;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

import com.cucumber.selenium.domain.Details;
import com.cucumber.selenium.helpers.ExcelUtility;
import com.cucumber.selenium.helpers.GlobalVariable;
import com.cucumber.selenium.helpers.Hooks;
import com.cucumber.selenium.helpers.WebDriverHelper;
import com.cucumber.selenium.steps.UserSteps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class SearchBlount {
	private WebDriver wd;
	private WebDriverHelper wh;
	private final Logger logger = LogManager.getLogger();
	private List<Details> listOfDetails = new ArrayList<Details>();
	private UserSteps userSteps;

	public SearchBlount() {
		userSteps = new UserSteps(Hooks.driver, Hooks.wh);
		this.wd = Hooks.driver;
		this.wh = Hooks.wh;
	}

	@Given("^I search blount$")
	public void i_search_given_url() {
		List<String> list = ExcelUtility.getPPIN();
		List<String> notFOund = new ArrayList<String>();
		List<String> errorPPIN = new ArrayList<String>();

		for (String ppin : list) {
			try {
				logger.debug("Open website");
				wd.navigate().to("http://www.deltacomputersystems.com/AL/AL08/plinkquerya.html");
				boolean isPPINPresent = userSteps.searchPPIN(ppin);
				System.out.println(ppin);
				String url = wd.getCurrentUrl();
				if (isPPINPresent) {
					listOfDetails.add(userSteps.getDetails(url, "blount", ppin));
				} else {
					notFOund.add(ppin);
				}
			} catch (Exception e) {
				logger.error(e.getMessage());
				errorPPIN.add(ppin);
			}

		}
		/*
		 * logger.debug("Open website"); wd.navigate().to(
		 * "http://www.deltacomputersystems.com/AL/AL08/plinkquerya.html"); boolean
		 * isPPINPresent = userSteps.searchPPIN(Integer.toString(304));
		 * System.out.println(304); String url = wd.getCurrentUrl();
		 * listOfDetails.add(userSteps.getDetails(url, "blount","304")); //9554 14371
		 * 1374 // 10266 20739
		 */

		System.out.println("Not Found PPIN: " + notFOund);
		System.out.println("Error Found PPIN: " + errorPPIN);
		GlobalVariable.LIST_OF_DETAILS = listOfDetails;
	}

}
