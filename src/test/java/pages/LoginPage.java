package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class LoginPage {

//declarations
	WebDriver driver;
	WebDriverWait wait;

//constructor
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
//locators
	By usernameInput = By.id("user-name");
	By passwordInput = By.id("password");
	By loginButton = By.xpath("//input[@id='login-button']");
	By errorMessage = By.cssSelector("[data-test='error']");
	
//reusable methods
	
	public void enterCredentials(String username, String password) {
		driver.findElement(usernameInput).sendKeys(username);
		driver.findElement(passwordInput).sendKeys(password);
	}
	
	public void clickLoginButton() {
		driver.findElement(loginButton).click();
	}
	
	public void verifyUrl(String expectedUrl) {
		String actualUrl = driver.getCurrentUrl();
		Assert.assertEquals(actualUrl, expectedUrl, "Expected Url was not reached");
	}
	
	public void verifyErrorMessage(String expectedMssg) {
		String actualMssg = driver.findElement(errorMessage).getText();
		Assert.assertEquals(actualMssg, expectedMssg, "Expected error message was not displayed");
	}
	

}
