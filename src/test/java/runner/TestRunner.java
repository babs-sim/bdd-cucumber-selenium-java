package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
	features = "src/test/resources/features", // run all .feature files under this folder
	glue = {"stepdefinitions", "hooks"}, // package(s) with step definitions
	plugin = {"pretty", "html:target/cucumber-reports"}, // Generate HTML report
	monochrome = true
)
	public class TestRunner extends AbstractTestNGCucumberTests {
	}
