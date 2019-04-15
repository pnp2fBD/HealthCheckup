package cucumber.cc.dlg;

import java.io.IOException;

import org.apache.log4j.Logger;
import base.DLG;
import base.DLGHealthCheckException;
import cucumber.api.java.en.Then;
import screens.CustomerDashboard;
import utility.EnvironmentStatusUtils;

public class CustomerDashboardStepsDefinition {

	String environment = System.getProperty("environment");
	EnvironmentStatusUtils status ;
	String errorMessage = "NA";
	int testcase = 5;
	private static final Logger log = Logger.getLogger(LoginStepsDefinition.class.getName());

	CustomerDashboard customerDashboard = DLG.getCustomerDashboard();

	@Then("^click on Passed Verification$")
	public void click_on_Passed_Verification() throws Throwable {
		customerDashboard.clickPassedVerification();
		
	}

	@Then("^verify document generation$")
	public void verify_Document_Generation() throws IOException, DLGHealthCheckException {
		try {
			customerDashboard.verifyDocumentGeneration();
		} catch (DLGHealthCheckException e) {
			errorMessage = e.getMessage();
			log.error(e.getMessage());
			throw e;
		} finally {
			status= new EnvironmentStatusUtils();
			status.writeExcel(errorMessage, environment, testcase);
		}
	}

	@Then("^click on Complete$")
	public void click_on_Complete() throws DLGHealthCheckException, IOException {
		try {
			customerDashboard.clickOnComplete();
		} catch (DLGHealthCheckException e) {
			errorMessage = e.getMessage();
			log.error(e.getMessage());
			throw e;
		} finally {
			
			status.writeExcel(errorMessage, environment, testcase);
		}
	}

}
