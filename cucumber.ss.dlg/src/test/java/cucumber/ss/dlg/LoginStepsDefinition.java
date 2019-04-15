package cucumber.ss.dlg;

import java.io.IOException;

import org.apache.log4j.Logger;
import base.DLG;
import cucumber.api.java.en.Then;
import exception.DLGHealthCheckException;
import screens.Login;
import utility.EnvironmentStatusUtils;
import utility.ExcelReader;

public class LoginStepsDefinition {
	Login login = DLG.getLogin();
	String env = System.getProperty("environment");
	String username, password;
	
	EnvironmentStatusUtils status;
	String errorMessage = "NA";
	int testcase = 1;
	private static final Logger log = Logger.getLogger(LoginStepsDefinition.class.getName());

	public LoginStepsDefinition() throws IOException {
		ExcelReader excelRead = new ExcelReader();
		username = excelRead.getUserName(env);
		password = excelRead.getPassword(env);
	}

	@Then("^Enter login credentials$")
	public void enter_login_credentials() throws IOException, DLGHealthCheckException {
		try {
			login.enterCredentials(username, password);
		} catch (DLGHealthCheckException e) {
			errorMessage = e.getMessage();
			log.error(e.getMessage());
			throw e;
		} finally {
			status = new EnvironmentStatusUtils();
			status.writeExcel(errorMessage, env, testcase);
		}
	}

	@Then("^clicks on Sign In button$")
	public void clicks_on_sign_in_button() throws IOException, DLGHealthCheckException {
		try {
			login.clickOnSignInButton();
		} catch (DLGHealthCheckException e) {
			errorMessage = e.getMessage();
			log.error(e.getMessage());
			throw e;
		} finally {
			status.writeExcel(errorMessage, env, testcase);
		}
	}
}