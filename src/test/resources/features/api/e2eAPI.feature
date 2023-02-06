Feature: E2E API Tests
  Description: The main goal is to implement tests for verifying and checking the response of the targeted API endpoints

  Scenario: the Authorized can List, Add and Delete repos from the Organization
    Given The list of repositories is available
    When I create a new repository
    Then the repository is now on the list
    When I delete a repository
    Then the repository is no longer on the list
