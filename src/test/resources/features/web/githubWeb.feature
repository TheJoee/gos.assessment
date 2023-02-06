Feature: Github Website Tests
  Description: The objective of this assessment is to implement some test suites for validating and checking
  the user interface of GitHub web page.

  Scenario: User sign-in
    Given I navigate to Github login page
    When I login with valid credentials
    Then I will see landing page
    Then I will close the browser

  Scenario: Get repositories list
    Given I navigate to Github login page
    When I login with valid credentials
    Then I will see landing page
    When I navigate to repositories page
    Then I will see all the users repositories list
    Then I will close the browser

  Scenario: Create a new repository
    Given I navigate to Github login page
    When I login with valid credentials
    Then I will see landing page
    When I navigate to repositories page
    Then I will see all the users repositories list
    When I select option to create a new repository
    Then I will see new repository page
    When I fill all required information and confirm
    Then I will see the created repository on the repositories list
    Then I will close the browser