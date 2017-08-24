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

import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class SearchWebsite {
	private WebDriver wd;
	private WebDriverHelper wh;
	private final Logger logger = LogManager.getLogger();
	private List<Details> listOfDetails = new ArrayList<Details>();
	private UserSteps userSteps;

	public SearchWebsite() {
		userSteps = new UserSteps(Hooks.driver, Hooks.wh);
		this.wd = Hooks.driver;
		this.wh = Hooks.wh;
	}

	@Given("^I search given URL$")
	public void i_search_given_url() {
		List<String> list = ExcelUtility.getUrls();

		for (String url : list) {
			logger.debug("Open website");
			wd.navigate().to(url);

			listOfDetails.add(userSteps.getDetails(url, "san bernardino","none"));
		}
		GlobalVariable.LIST_OF_DETAILS = listOfDetails;

	}

	

}
