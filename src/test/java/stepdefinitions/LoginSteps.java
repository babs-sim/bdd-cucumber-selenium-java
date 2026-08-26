package stepdefinitions;

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
	
	WebDriver driver;
	String url = "https://www.saucedemo.com/";
	
	LoginPage login;
	
	
	//Initialise the browser before each scenario
	@Before
	 public void setup() {
		driver = library.Browsers.launchBrowser("Chrome");
		driver.manage().window().maximize();
		driver.get(this.url);
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));
		
		login = new LoginPage(driver);
	}
	
	// Cleanup after each test
    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
    
	//Steps for login with valid credentials
	@Given("I am on the login page")
	public void userOnLoginPage() {
		login.verifyUrl("https://www.saucedemo.com/");
	}
	
	
	@When("I enter valid username and password")
	public void userEntersValidCredentials() {
		login.enterCredentials("standard_user", "secret_sauce");
	}
	
	@And("I click on the login button")
	public void userClicksLoginButton() {
		login.clickLoginButton();
	}
	
	@Then("I should be redirected to the dashboard page")
	public void verifyPage() {
		login.verifyUrl("https://www.saucedemo.com/inventory.html");
	}

	// Steps for invalid credentials
	@When("I enter invalid username and password")
	public void userEntersInvalidCredentials() {
		login.enterCredentials("wrong_user", "wrong_password");
	}

	@Then("I should see an error message indicating invalid credentials")
	public void verifyInvalidCredentialsError() {
		login.verifyErrorMessage("Epic sadface: Username and password do not match any user in this service");
	}

	// Steps for empty fields
	@When("I leave the username and password fields empty")
	public void userLeavesFieldsEmpty() {
		login.enterCredentials("", "");
	}

	@Then("I should see an error message indicating that fields cannot be empty")
	public void verifyEmptyFieldsError() {
		login.verifyErrorMessage("Epic sadface: Username is required");
	}

}
