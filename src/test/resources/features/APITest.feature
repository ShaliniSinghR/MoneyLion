@APIFile
Feature: Test reqres API calls

  @pageValidation
  Scenario Outline: Verify user is able to get response in page format
    Given User wants to get list of user based on "<FilterCriteria>"
    When User hit the "<APIMethod>" API "<TestName>"
    Then User gets successful response "<responseCode>"
    And User is able to validate the "<APIMethod>" Output
    Examples:
      | APIMethod |  FilterCriteria   | responseCode| TestName     |
      | GET       |  1                |    200      | PageFilter1  |

  @newUserEntry
  Scenario Outline: Verify user is able to add or update an entry
    Given User wants to add or update a new "<entryType>" in the database with "<URL>"
    When User hit the "<APIMethod>" API "<TestName>"
    Then User gets successful response "<responseCode>"
    And User is able to validate the "<APIMethod>" Output
    Examples:
      | APIMethod  |   TestName       | responseCode | entryType |       URL                       |
      | POST       | createEntry      | 201          |  Create   |  https://reqres.in/api/users    |
