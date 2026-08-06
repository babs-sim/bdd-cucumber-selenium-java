package stepdefinitions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginSteps {
	
	private WebDriver driver;
	private String url = "https://www.saucedemo.com/";
	
	//Initialise the browser before each scenario
	
	@Before
	public void setUp() {
		driver = new ChromeDriver();
	}
	
	//Step definitions for login with valid credentials
	@Given("I am on the login page")
	public void userOnLoginPage() {
		if (!this.driver.getCurrentUrl().equals(this.url)) {
            this.driver.get(this.url);
		}
	}
	
	@When("I enter valid username and password")
	public void userEntersValidCredentials() {
		WebElement usernameField = driver.findElement(By.id("user-name"));
		WebElement passwordField = driver.findElement(By.id("password"));
		usernameField.sendKeys("standard_user");
		passwordField.sendKeys("secret_sauce");
	}
	
	@And("I click on the login button")
	public void userClicksLoginButton() {
		WebElement loginButton = driver.findElement(By.id("login-button"));
		loginButton.click();
	}
	
	@Then("I should be redirected to the dashboard page")
	public void verifyPage() {
		String expectedUrl = "https://www.saucedemo.com/inventory.html";
		String actualUrl = driver.getCurrentUrl();
		Assert.assertEquals(actualUrl, expectedUrl, "Expected to be redirected to the products page after successful login");
	}

	// Steps for invalid credentials
	@When("I enter invalid username and password")
	public void userEntersInvalidCredentials() {
		WebElement usernameField = driver.findElement(By.id("user-name"));
		WebElement passwordField = driver.findElement(By.id("password"));
		usernameField.clear();
		passwordField.clear();
		usernameField.sendKeys("invalid_user");
		passwordField.sendKeys("wrong_password");
	}

	@Then("I should see an error message indicating invalid credentials")
	public void verifyInvalidCredentialsError() {
		WebElement error = driver.findElement(By.cssSelector("[data-test='error']"));
		Assert.assertTrue(error.isDisplayed(), "Expected an error message to be displayed for invalid credentials");
	}

	// Steps for empty fields
	@When("I leave the username and password fields empty")
	public void userLeavesFieldsEmpty() {
		WebElement usernameField = driver.findElement(By.id("user-name"));
		WebElement passwordField = driver.findElement(By.id("password"));
		usernameField.clear();
		passwordField.clear();
	}

	@Then("I should see an error message indicating that fields cannot be empty")
	public void verifyEmptyFieldsError() {
		WebElement error = driver.findElement(By.cssSelector("[data-test='error']"));
		Assert.assertTrue(error.isDisplayed(), "Expected an error message to be displayed when fields are empty");
	}
	
	 // Cleanup after each test
    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

}
