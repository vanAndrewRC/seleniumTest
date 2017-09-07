package com.cucumber.selenium.helpers;

public enum LogInformationCategories {
	TEST_DATA("TEST DATA"),
	ENVIRONMENT("ENVIRONMENT"),
	CODE ("CODE"),
	EXCEPTION ("EXCEPTION"),
	BROWSER_ACTION ("BROWSER ACTION");

	private String category;
	
	private LogInformationCategories(String category) {
		this.category = category;
	}
	
	public String getCategory() {
		return category;
	}
}
