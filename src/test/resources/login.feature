

Feature: Login
  Scenario: Successful login with valid credentials	Given I am on the login page	When I enter valid username and password	And I click on the login button	Then I should be redirected to the dashboard page
  Scenario: Unsuccessful login with invalid credentials
	Given I am on the login page	When I enter invalid username and password
	And I click on the login button
	Then I should see an error message indicating invalid credentials
	
  Scenario: Unsuccessful login with empty credentials
  	Given I am on the login page
  	When I leave the username and password fields empty
  	And I click on the login button
  	Then I should see an error message indicating that fields cannot be empty

	
	