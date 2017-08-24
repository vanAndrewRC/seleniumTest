package com.cucumber.selenium.runner;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features/search/blount.feature", glue = {
		"com.cucumber.selenium.stepdefinition.searchURL", "com.cucumber.selenium.helpers" }, plugin = { "pretty",
				"html:target/cucumber", "json:results/cucumber.json", "junit:results/cucumber.xml" })

public class BlountSearchTest {

}
