package screens;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriverException;

import UXP.Common;
import UXP.DLGGlobal;
import base.DLG;
import base.DLGHealthCheckException;
import utility.WaitUtil;

public class Search extends DLG {

	public static String policyNumber = "//div/input[@id = 'C2__QUE_57179A1244E6618013741']";
	public static String searchButton = "//div/button[@id='C2__BUT_57179A1244E6618013765']";
	public static String passVerifyButton = "//div/button[@id = 'C2__BUT_9252AAA206FD524963824']";
	public static String gridPolicyNumber;
	public static String gridPolicyHolder;
	public static String gridProduct;
	public static String gridScheme;
	public static String gridStatus;
	public static String gridEffectiveFrom;
	public static String gridEffectiveTo;
	public static String gridAuthorisedContact;
	String title = "Search";

	public static final Logger log = Logger.getLogger(Search.class.getName());

	public void hasScreenLoaded() throws DLGHealthCheckException {
		try{
		WaitUtil.waitForSpinner(driver);
		log.debug("Entered in hasScreenLoaded() method of Search class");
		Common.printFunctionName("Search Screen - check screen loading");
		log.debug("Locator of webelement policyNumber is " + policyNumber);
		if(DLGGlobal.getDriver().getTitle().equals(title)){
			Common.doesElementExists("Policy number field", policyNumber);
			log.debug("Locator of webelement Search button is " + searchButton);
			Common.doesElementExists("Search button", searchButton);}
		else{throw new WebDriverException("Page title displayed is not as expected. Expected Page title -"+title
				+ ". Current Page title- "+DLGGlobal.getDriver().getTitle()+"Test");}
		log.info("Search screen has loaded successfully.");
		log.debug("Exit from hasScreenLoaded() method of Search class");
	} catch (WebDriverException e) {
		DLGGlobal.getDriver().close();
		throw new DLGHealthCheckException("Search Screen not Loading. "+e.getMessage().split("Test")[0], e);
	} catch (Exception e) {
		DLGGlobal.getDriver().close();
		e.printStackTrace();
	}
	}

	public Search enterPolicyNumber(String policyNumber) throws DLGHealthCheckException {
		try {
			WaitUtil.waitForSpinner(driver);
			log.debug("Entered in enterPolicyNumber(String policyNumber) method of Search class");
			Common.printFunctionName("Search Screen - enter policy number");
			System.out.println("***********" + policyNumber);
			if (policyNumber.equals("=GlobalPolicy")) {
				log.debug("Entered in if condition");
				policyNumber = Common.getPolicyNumber();
				log.debug("Exit from if condition");
			}
			System.out.println("****************" + policyNumber);
			Common.InteractInput(Search.policyNumber, policyNumber, "PolicyNumber");
			log.info("Entered the policy Number " + policyNumber);
			log.debug("Exit from enterPolicyNumber(String policyNumber) method of Search class");
			return this;
		} catch (WebDriverException e) {
			DLGGlobal.getDriver().close();
			throw new DLGHealthCheckException("Unable to enter Policy Number", e);
		} catch (Exception e) {
			DLGGlobal.getDriver().close();
			e.printStackTrace();
			return null;
		}
	}

	public Search clickOnSearchButton() throws DLGHealthCheckException {
		try {
			WaitUtil.waitForSpinner(driver);
			log.debug("Entered in clickOnSearchButton() method of Search class");
			Common.printFunctionName("Search Screen - click on search button");
			Common.InteractButton(searchButton, "searchButton");
			WaitUtil.waitForSpinner(driver);
			log.info("Clicked on Search button");
			log.debug("Exit from clickOnSearchButton() method of Search class");
			return this;
		} catch (WebDriverException e) {
			DLGGlobal.getDriver().close();
			throw new DLGHealthCheckException("Unable to click on Search Button", e);
		} catch (Exception e) {
			DLGGlobal.getDriver().close();
			e.printStackTrace();
			return null;
		}
	}

	public void checkPolicyAndQuoteData(String policyNumber) throws DLGHealthCheckException {
		try {
			WaitUtil.waitForSpinner(driver);
			log.debug("Entered in checkPolicyAndQuoteData(String policyNumber) method of Search class");
			Common.printFunctionName("Search Screen - check Policy/Quote data");
			gridPolicyNumber = "//table[@id = 'C2__TBL_9C3BD3DB194DC120259818']/tbody/tr/td/div/span[@id='C2__QUE_9C3BD3DB194DC120259880_R1' and text()='"
					+ policyNumber + "']";
			Common.doesElementExists("Policy number - " + policyNumber, gridPolicyNumber);
			WaitUtil.waitForSpinner(driver);
			Common.InteractTable("//table[@id = 'C2__TBL_9C3BD3DB194DC120259818']/tbody/tr[1]",
					"Policy and Quote table");
			log.info("Interacting with the Policy and Quote table");
			log.debug("Exit from checkPolicyAndQuoteData(String policyNumber) method of Search class");
		} catch (WebDriverException e) {
			DLGGlobal.getDriver().close();
			throw new DLGHealthCheckException("Unable to check Policy and QuoteData", e);
		} catch (Exception e) {
			if(!(DLGGlobal.getDriver()==null)){
			DLGGlobal.getDriver().close();
			e.printStackTrace();}
			else{throw new DLGHealthCheckException("Unable to check Policy and QuoteData", e);}
			
		}
	}

	public void CheckAuthorisedContact() throws DLGHealthCheckException {
		try {
			WaitUtil.waitForSpinner(driver);
			log.debug("Entered in CheckAuthorisedContact() method of Search class");
			Common.printFunctionName("Search Screen - check Authorised Contact");
			WaitUtil.waitForSpinner(driver);
			Common.InteractTable("//table[@id = 'C2__TBL_5D046CA9B13A360C790789']/tbody/tr[1]",
					"Authorized Contact table");
			log.info("Validated the authorised contact");
			log.debug("Exit from CheckAuthorisedContact() method of Search class");
		}catch (WebDriverException e) {
			DLGGlobal.getDriver().close();
			throw new DLGHealthCheckException("Unable to check authorised contact", e);
		} catch (Exception e) {
			DLGGlobal.getDriver().close();
			e.printStackTrace();
		}
	}

	public Search clickOnPoliciesAndQuotes(String policyNumber) throws DLGHealthCheckException {
		try {
			WaitUtil.waitForSpinner(driver);
			System.out.println("Policy click check Done 2");
			Common.printFunctionName("Search Screen - click Policy and Quote table");
			return this;
		} catch (WebDriverException e) {
			DLGGlobal.getDriver().close();
			throw new DLGHealthCheckException("Unable to click on Policies and Quotes", e);
		} catch (Exception e) {
			DLGGlobal.getDriver().close();
			e.printStackTrace();
			return null;
		}
	}

	public Search clickOnAuthorisedContacts(String name) throws DLGHealthCheckException {
		try {
			WaitUtil.waitForSpinner(driver);
			Common.printFunctionName("Search Screen - click Authorised Contact table");
			return this;
		} catch (WebDriverException e) {
			DLGGlobal.getDriver().close();
			throw new DLGHealthCheckException("Element not found.", e);
		} catch (Exception e) {
			DLGGlobal.getDriver().close();
			e.printStackTrace();
			return null;
		}
	}

	public CustomerDashboard clickOnVerify() throws DLGHealthCheckException {
		try {
			log.debug("Entered in clickOnVerify() method of Search class");
			WaitUtil.waitForSpinner(driver);
			Common.InteractButton(passVerifyButton, "Pass Verify");
			// Common.InteractElement("verify");
			log.info("Clicked on Pass verification button");
			CustomerDashboard customerDashboard = new CustomerDashboard();
			DLG.setCustomerDashboard(customerDashboard);
			log.debug("Exit from clickOnVerify() method of Search class");
			return customerDashboard;
		} catch (WebDriverException e) {
			DLGGlobal.getDriver().close();
			throw new DLGHealthCheckException("Unable to click on Verify.", e);
		} catch (Exception e) {
			DLGGlobal.getDriver().close();
			e.printStackTrace();
			return null;
		}
	}
}
