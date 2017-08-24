# README #

This is a sample Mavenized web test automation project (that uses Cucumber, Java, Selenium). 
This is the working project to be implemented on all projects, configure the project alias based on the assigned project.

### Before running ###

* Make sure you have a local Maven installation,  at least version 3.3.9 
* Make sure you have a local JDK installation, version 1.8
* Update Maven dependencies: mvn clean compile
* The copy of MainConfig.properties is located on this project
* Download updated "Third Party Browser Drivers" on your local drive. 
		
### Scripting ###
* Use the different WebDriver Helper methods.

### To run the test ###

* Run all: **mvn clean test**
* Sample for running a specific set of scenarios by tag: **mvn clean test -Dcucumber.options="--tags @@smoke"**

### Technical requirements ###
* Saucelabs integration
* Option to run only a subset of all features/scenarios
* Multi-browser support
* Others TBD

Author: V. Arce