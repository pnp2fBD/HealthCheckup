package cucumber.ss.dlg;

import org.junit.runner.RunWith;
import org.testng.annotations.AfterTest;

import UXP.DLGGlobal;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@RunWith(Cucumber.class)
@CucumberOptions(format = { "pretty", "html:target/html/",
		"json:target/json/ss.json" }, features = "src/test/resources")
public class SSrunnerTest extends AbstractTestNGCucumberTests {

	@AfterTest
	public void quitDriver() {
		if (DLGGlobal.getDriver() != null)
			DLGGlobal.getDriver().quit();
	}

}