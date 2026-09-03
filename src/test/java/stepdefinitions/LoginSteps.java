package stepdefinitions;

import hooks.Hooks;
import java.time.Duration;

//import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
//import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.LoginPage;

public class LoginSteps {
	
	private LoginPage login;
	
	private LoginPage loginPage() {
		
		if (login == null) {
			login = new LoginPage(Hooks.getDriver());
		}
		return login;
	}
    
	//Steps for login with valid credentials
	@Given("I am on the login page")
	public void userOnLoginPage() {
		loginPage().verifyUrl("https://www.saucedemo.com/");
	}
	
	
	@When("I enter valid username and password")
	public void userEntersValidCredentials() {
		loginPage().enterCredentials("standard_user", "secret_sauce");
	}
	
	@And("I click on the login button")
	public void userClicksLoginButton() {
		loginPage().clickLoginButton();
	}
	
	@Then("I should be redirected to the dashboard page")
	public void verifyPage() {
		loginPage().verifyUrl("https://www.saucedemo.com/inventory.html");
	}

	// Steps for invalid credentials
	@When("I enter invalid username and password")
	public void userEntersInvalidCredentials() {
		loginPage().enterCredentials("wrong_user", "wrong_password");
	}

	@Then("I should see an error message indicating invalid credentials")
	public void verifyInvalidCredentialsError() {
		loginPage().verifyErrorMessage("Epic sadface: Username and password do not match any user in this service");
	}

	// Steps for empty fields
	@When("I leave the username and password fields empty")
	public void userLeavesFieldsEmpty() {
		loginPage().enterCredentials("", "");
	}

	@Then("I should see an error message indicating that fields cannot be empty")
	public void verifyEmptyFieldsError() {
		loginPage().verifyErrorMessage("Epic sadface: Username is required");
	}

}
