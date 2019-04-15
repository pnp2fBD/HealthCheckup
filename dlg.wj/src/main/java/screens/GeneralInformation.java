package screens;

import common.Common;
import utility.WaitUtil;
import utility.TestUtil;
import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import base.DLG;
import base.DLGHealthCheckException;

public class GeneralInformation extends DLG {

	private static final Logger log = Logger.getLogger(GeneralInformation.class.getName());
	private static String email;

	public GeneralInformation enterGeneralInformation(String businessName, String firstName, String lastName)
			throws DLGHealthCheckException, NumberFormatException, InterruptedException {
		log.debug("Entering in enterGeneralInformation(..) method of GeneralInformation class");
		log.info("General Information - Enter business details");
		Common.interactInput("businessNameTxt", businessName);
		log.info("Business name is " + businessName);
		//Common.InteractListbox("titleSlct", title);
		//log.info("Title is " + title);
		Common.interactInput("firstNameTxt", firstName);
		log.info("First name is " + firstName);
		Common.interactInput("lastNameTxt", lastName);
		log.info("Last name is " + lastName);
		log.debug("Exit from enterGeneralInformation(..) method of GeneralInformation class");
		return this;
	}
	
	public GeneralInformation contactEmailAddress(String telephoneNumber, String marketingPreference,String postCode) throws DLGHealthCheckException {
		log.debug("Your Contact detail method of GeneralInformation class");
		email = TestUtil.getEmail();
		Common.interactInput("emailAddressdetail", email + "@ssp-uk.com");
		log.info("Email entered is " + email + "@ssp-uk.com");
		Common.interactInput("contactTelehoneNumber", telephoneNumber);
		log.info("Telephone number is " + telephoneNumber);
		Common.interactInput("postCodeTxt", postCode);
		log.info("Postal code is " + postCode);
		//Common.checkLoadingImage();
		log.debug("Exit from contactEmailAddress() method of GeneralInformation class");
		return this;
	}
	
	public static String getEmail(){
		return email;
	}

	public GeneralInformation clickOnFindAddress() throws DLGHealthCheckException, InterruptedException {
		log.debug("Entering in clickOnFindAddress() method of GeneralInformation class");
		Common.printFunctionName("General Information - Click on find address button");
		WaitUtil.waitForSpinner(driver);
		TestUtil.javaScriptExecutorUtil("arguments[0].click();", "findAddressBtn");
		//Common.interactButton("findAddressBtn");
		WaitUtil.waitForSpinner(driver);
		log.info("Clicked on Find address button");
		log.debug("Exit from clickOnFindAddress() method of GeneralInformation class");
		return this;
	}

	public GeneralInformation pickAddress(String addressListIndex) throws DLGHealthCheckException {
		log.debug("Entering in pickAddress(String addressListIndex) method of GeneralInformation class");
		WaitUtil.waitForSpinner(driver);
		Common.printFunctionName("General Information - Select address");
		Common.interactListbox("addressListSlct", Integer.parseInt(addressListIndex));
		log.info("Select the address from the list");
		//Common.checkLoadingImage();
		WaitUtil.waitForSpinner(driver);
		log.debug("Exit from pickAddress(String addressListIndex) method of GeneralInformation class");
		return this;
	}

	public YourBusiness clickOnNextButton() throws DLGHealthCheckException {
		WaitUtil.waitForSpinner(driver);
		log.debug("Entering in clickOnNextButton() method of GeneralInformation class");
		Common.printFunctionName("General Information - Click on next button");
		Common.interactButton("gnrlNextBtn");
		log.info("Clicked on the next button");
		WaitUtil.waitForSpinner(driver);
		YourBusiness yourBusiness = new YourBusiness();
		//DLG.setYourBusiness(yourBusiness);
		log.debug("Exit from clickOnNextButton() method of GeneralInformation class");
		return yourBusiness;
	}

}
