package cucumber.ss.dlg;

import java.io.IOException;

import org.apache.log4j.Logger;
import base.DLG;
import cucumber.api.java.en.And;
import exception.DLGHealthCheckException;
import screens.CustomerDashboard;
import utility.EnvironmentStatusUtils;

public class CustomerDashboardStepsDefinition {

	CustomerDashboard customerDashboard = DLG.getCustomerDashboard();

	String env = System.getProperty("environment");
	EnvironmentStatusUtils status;
	String errorMessage = "NA";
	int testcase = 2;
	private static final Logger log = Logger.getLogger(LoginStepsDefinition.class.getName());

	@And("^verify the policy number$")
	public void verify_the_policy_number() throws DLGHealthCheckException, IOException {
		try {
			customerDashboard.verifyPolicyNumbers();
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
