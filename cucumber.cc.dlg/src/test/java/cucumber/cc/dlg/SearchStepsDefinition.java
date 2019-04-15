package cucumber.cc.dlg;

import java.io.IOException;

import org.apache.log4j.Logger;
import base.DLG;
import base.DLGHealthCheckException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import screens.Search;
import utility.EnvironmentStatusUtils;
import utility.ExcelReader;

public class SearchStepsDefinition {

	String environment = System.getProperty("environment");
	EnvironmentStatusUtils status;
	String errorMessage = "NA";
	int testcase = 2;
	private static final Logger log = Logger.getLogger(LoginStepsDefinition.class.getName());

	String policyNumber;

	public SearchStepsDefinition() throws IOException {
		try {
			ExcelReader excelReadUtil = new ExcelReader();
			policyNumber = excelReadUtil.getPolicyNumber(environment);
		} catch (IOException e) {
			errorMessage = e.getMessage();
			log.error(e.getMessage());
		}
	}

	Search search = DLG.getSearch();

	@Then("^Enter policy number$")
	public void enter_policy_number() throws IOException, DLGHealthCheckException {

		try {
			// String policyNumber = System.getProperty("policyNumber");
			search.enterPolicyNumber(policyNumber);
		} catch (DLGHealthCheckException e) {
			errorMessage = e.getMessage();
			log.error(e.getMessage());
			throw e;
		} finally {
			status= new EnvironmentStatusUtils();
			status.writeExcel(errorMessage, environment, testcase);
		}
	}

	@Then("^clicks on Search button$")
	public void click_on_Search_button() throws IOException, DLGHealthCheckException {
		try {
			search.clickOnSearchButton();	
		} catch (DLGHealthCheckException e) {
			errorMessage = e.getMessage();
			log.error(e.getMessage());
			throw e;
		} finally {
			status.writeExcel(errorMessage, environment, testcase);
		}

	}

	@And("^check the policy status$")
	public void check_the_policy_status() throws IOException, DLGHealthCheckException {
		try {
			// String policyNumber = System.getProperty("policyNumber");
			testcase = 3;
			search.checkPolicyAndQuoteData(policyNumber);
		} catch (DLGHealthCheckException e) {
			errorMessage = e.getMessage();
			log.error(e.getMessage());
			throw e;
		} finally {
			status.writeExcel(errorMessage, environment, testcase);
		}
	}

	@Then("^click policies and quotes table$")
	public void click_policies_and_quotes_table() throws IOException {

		try {
			// String policyNumber = System.getProperty("policyNumber");
			testcase = 4;
			search.clickOnPoliciesAndQuotes(policyNumber);
		} catch (DLGHealthCheckException e) {
			errorMessage = e.getMessage();
			log.error(e.getMessage());
		}
	}

	@And("^check the authorised contact$")
	public void check_the_authorised_contact() throws IOException, DLGHealthCheckException {
		try {
			testcase = 4;
			search.CheckAuthorisedContact();
		} catch (DLGHealthCheckException e) {
			errorMessage = e.getMessage();
			log.error(e.getMessage());
			throw e;
		} finally {
			status.writeExcel(errorMessage, environment, testcase);
			// assertTrue(errorMessage.equalsIgnoreCase("NA"));
		}
	}

	/*
	 * @Then("^click on Authorised Contacts$") public void
	 * click_on_Authorised_Contacts() throws IOException { try {
	 * search.clickOnAuthorisedContacts(policyNumber); testcase=4; } catch
	 * (DLGHealthCheckException e) { errorMessage = e.getMessage();
	 * log.error(e.getMessage());
	 * 
	 * }finally{status.writeExcel(errorMessage, environment, testcase);} }
	 */

	@Then("^click on Verify$")
	public void click_on_Verify() throws IOException, DLGHealthCheckException {
		try {
			testcase = 5;
			search.clickOnVerify();
		} catch (DLGHealthCheckException e) {
			errorMessage = e.getMessage();
			log.error(e.getMessage());
			throw e;
		} finally {
			status.writeExcel(errorMessage, environment, testcase);

			// assertTrue(errorMessage.equalsIgnoreCase("NA"));
		}
	}
}
