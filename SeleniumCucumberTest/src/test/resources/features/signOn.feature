@signOn
Feature: Sign on the website

  Scenario Outline: Sign on the website
    When I sign on using '<username>' and '<password>'
    Then I am redirected to 'http://newtours.demoaut.com/mercuryreservation.php'
    And I see 'Flight Finder'

    Examples: 
      | username   | password    |
      | andrewVlad | Password123 |
