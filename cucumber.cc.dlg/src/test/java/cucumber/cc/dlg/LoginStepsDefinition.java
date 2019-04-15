package cucumber.cc.dlg;

import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;
import base.DLGHealthCheckException;
import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import screens.Login;
import utility.EnvironmentStatusUtils;

public class LoginStepsDefinition {

	String environment = System.getProperty("environment");
	Login login;
	EnvironmentStatusUtils status ;
	String errorMessage = "NA";
	int testcase = 1;

	private static final Logger log = Logger.getLogger(LoginStepsDefinition.class.getName());

	@Given("^User is on contact centre login screen$")
	public void user_is_on_contact_centre_login_screen() throws IOException, DLGHealthCheckException {
		// String env = System.getProperty("environment");
		login = new Login(environment);
		// Common.checkLoadingImage();
		try {
			login.hasScreenLoaded();
		} catch (DLGHealthCheckException e) {
			errorMessage = e.getMessage();
			log.error(e.getMessage());
			throw e;
		} finally {
			status= new EnvironmentStatusUtils();
			status.writeExcel(errorMessage, environment, testcase);
		}
		// Write code here that turns the phrase above into concrete actions
	}

	@Then("^enters login credentials$")
	public void enters_login_credentials(DataTable table) throws IOException, DLGHealthCheckException {
		List<List<String>> data = table.raw();
		try {
			login.enterCredentials(data.get(1).get(1), data.get(2).get(1));
		} catch (DLGHealthCheckException e) {
			errorMessage = e.getMessage();
			log.error(e.getMessage());
			throw e;
		} finally {
			status.writeExcel(errorMessage, environment, testcase);
			// assertTrue(errorMessage.equalsIgnoreCase("NA"));
		}
	}

	@Then("^clicks on login button$")
	public void clicks_on_login_button() throws IOException, DLGHealthCheckException {
		try {
			login.clickOnLoginButton();
		} catch (DLGHealthCheckException e) {
			errorMessage = e.getMessage();
			log.error(e.getMessage());
			throw e;
		} finally {
			status.writeExcel(errorMessage, environment, testcase);
		}
	}
}