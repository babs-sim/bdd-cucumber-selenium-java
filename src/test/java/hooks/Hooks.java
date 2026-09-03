package hooks;

import java.time.Duration;

import org.openqa.selenium.WebDriver;

import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hooks {
	
	private static WebDriver driver;
	
	private String url = "https://www.saucedemo.com/";
	
	@Before
	public void setup() {
		driver = library.Browsers.launchBrowser("Chrome");
		driver.manage().window().maximize();
		driver.get(this.url);
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));
		
	}
	
	public static WebDriver getDriver() {
		return driver;
	}
	
	
	@After
	public void tearDown() {
		
		if (driver != null) {
			driver.quit();
		}
	}

	
}


	