package com.cucumber.selenium.stepdefinition.searchURL;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.cucumber.selenium.domain.Details;
import com.cucumber.selenium.helpers.ExcelUtility;
import com.cucumber.selenium.helpers.GlobalVariable;
import com.cucumber.selenium.helpers.Hooks;
import com.cucumber.selenium.helpers.WebDriverHelper;
import com.cucumber.selenium.steps.UserSteps;

import cucumber.api.java.en.Then;

public class LogDetails {
	private WebDriverHelper wh;
	private final Logger logger = LogManager.getLogger();
	private UserSteps userSteps;

	public LogDetails() {
		userSteps = new UserSteps(Hooks.driver, Hooks.wh);
		this.wh = Hooks.wh;
	}

	@Then("^I should log the details$")
	public void i_should_log_the_details() {
		logger.debug("Log details");

		ExcelUtility.updateDetails(GlobalVariable.LIST_OF_DETAILS);
	}

	@Then("^I should log the details on template$")
	public void i_should_log_the_details_on_template() {
		logger.debug("Log details");

		ExcelUtility.updateDetailsOnTemplate(GlobalVariable.LIST_OF_DETAILS);
	}
}
