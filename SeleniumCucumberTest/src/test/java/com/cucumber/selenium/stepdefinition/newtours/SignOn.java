package com.cucumber.selenium.stepdefinition.newtours;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import com.cucumber.selenium.domain.Details;
import com.cucumber.selenium.helpers.ExcelUtility;
import com.cucumber.selenium.helpers.GlobalVariable;
import com.cucumber.selenium.helpers.Hooks;
import com.cucumber.selenium.helpers.WebDriverHelper;
import com.cucumber.selenium.steps.UserSteps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class SignOn {
	private WebDriverHelper wh;
	private final Logger logger = LogManager.getLogger();
	private UserSteps userSteps;
	private WebDriver wd;

	public SignOn() {
		userSteps = new UserSteps(Hooks.driver, Hooks.wh);
		this.wh = Hooks.wh;
		this.wd = Hooks.driver;
	}

	@Given("^I sign on using '(.*)' and '(.*)'$")
	public void i_sign_on(String username, String password) {
		logger.debug("Sign on the website");
		userSteps.enterUserName(username);
		userSteps.enterPassword(password);
		userSteps.clickSubmit();
	}

	@Then("^I am redirected to '(.*)'$")
	public void i_am_redirected_to(String url) {
		logger.debug("Sign on the website");

		Assert.assertTrue(wd.getCurrentUrl().equals(url));
	}

	@And("^I see '(.*)'$")
	public void i_see(String page) {
	String pageContent = userSteps.getCurrenPageContent();
	Assert.assertTrue(pageContent.equals(page));
	}
	}
}
