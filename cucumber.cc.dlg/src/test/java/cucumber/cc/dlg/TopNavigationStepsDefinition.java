package cucumber.cc.dlg;

import java.io.IOException;

import org.apache.log4j.Logger;
import base.DLG;
import base.DLGHealthCheckException;
import cucumber.api.java.en.And;
import utility.EnvironmentStatusUtils;

public class TopNavigationStepsDefinition {

	String environment = System.getProperty("environment");
	EnvironmentStatusUtils status;
	String errorMessage = "NA";
	int testcase = 6;
	private static final Logger log = Logger.getLogger(LoginStepsDefinition.class.getName());

	@And("^Logout from the application$")
	public void Logout_from_the_application() throws IOException, DLGHealthCheckException {
		try {
			DLG.topNavigation.logOut();
		} catch (DLGHealthCheckException e) {
			errorMessage = e.getMessage();
			log.error(e.getMessage());
			throw e;
		} finally {
			status= new EnvironmentStatusUtils();
			status.writeExcel(errorMessage, environment, testcase);
		}
	}

	@And("^close the browser$")
	public void close_the_browser() throws Throwable {
		DLG.topNavigation.closeBrowser();
	}

}