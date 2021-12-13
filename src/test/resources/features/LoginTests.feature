@WebFile
Feature: Tests for MoneyLion Web application

 @AboutUsPage
 Scenario: Able to access MoneyLion about page successfully
    Given I am a new customer
    And access to the MoneyLion website
    When I hover on "AboutUS" and click on "AboutUSSubMenu" at the top of the webpage
    Then I should redirected to the "MoneyLion" about page
    And I should be able to see "Offices"