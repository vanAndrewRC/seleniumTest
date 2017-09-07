package com.cucumber.selenium.helpers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PropertyFileUtility {
	private static final Logger logger = LogManager.getLogger();
	private String fileLocation;
	Properties properties;

	public PropertyFileUtility(String fileLocation) {
		this.fileLocation = fileLocation;
	}

	public Properties getProperties() {
		logger.debug("Fetching property file from file location " + fileLocation);
		if (properties == null) {
			properties = new Properties();
		}
		try (FileInputStream fileInputStream = new FileInputStream(fileLocation);) {
			if (isAFile(fileLocation)) {
				properties.load(fileInputStream);
			}
		} catch (IOException e) {
			logger.error("Reading file " + fileLocation + " has failed or been interrupted.");
		}
		return properties;
	}

	private boolean isAFile(String fileLocation) {
		return new File(fileLocation).isFile();
	}

	public String getValueFor(String key) {
		return getProperties().getProperty(key);
	}

}
