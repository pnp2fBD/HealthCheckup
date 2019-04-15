package cucumber.ss.dlg;


import java.io.IOException;

import org.apache.log4j.Logger;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import exception.DLGHealthCheckException;
import screens.HomePage;
import utility.EnvironmentStatusUtils;

public class HomePageStepsDefinition {
	String env= System.getProperty("environment");
	
	EnvironmentStatusUtils status;
	String errorMessage="NA";
	int testcase = 1;
	private static final Logger log = Logger.getLogger(LoginStepsDefinition.class.getName());
	
	HomePage homePage;
	@Given("^User is on self service login screen$")
    public void user_is_on_self_service_login_screen() throws Exception {
        homePage = new HomePage(env);
    }

    @Then("^clicks on Sign In$")
    public void clicks_on_sign_in() throws IOException, DLGHealthCheckException {
        try {
			homePage.clickOnSignInButton();
		} catch (DLGHealthCheckException e) {
			errorMessage = e.getMessage();
			log.error(e.getMessage());
			throw e;
		} finally {
			status = new EnvironmentStatusUtils();
			status.writeExcel(errorMessage, env, testcase);
		}
    }
	
}