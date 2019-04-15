package screens;

import org.apache.log4j.Logger;
import base.DLG;
import base.DLGHealthCheckException;
import common.Common;
import utility.WaitUtil;

public class BusinessDetails extends DLG {

	private static final Logger log = Logger.getLogger(BusinessDetails.class.getName());

	public BusinessDetails whatDoYouDo(String profession) throws DLGHealthCheckException {
		log.debug("Entering in whatDoYouDo(String profession) method of BusinessDetails class");
		Common.printFunctionName("Business Details - Enter profession");
		Common.interactInput("Professiontxt", profession);
		log.info("Selected the profession " + profession);
		log.debug("Exit from whatDoYouDo(String profession) method of BusinessDetails class");
		return this;
	}

	public BusinessDetails clickOnProfession(String index) throws DLGHealthCheckException {
		log.debug("Entering in clickOnProfession(String index) method of BusinessDetails class");
		Common.printFunctionName("Business Details - Select profession");
		Common.interactAutoComplete(index);
		log.info("Business Details - Select profession. Index is  " + index);
		log.debug("Exit from clickOnProfession(String index) method of BusinessDetails class");
		return this;
	}

	public BusinessDetails whereDoYouRunYourBusinessFrom(String premises) throws DLGHealthCheckException {
		log.debug("Entering inwhereDoYouRunYourBusinessFrom(String premises) method of BusinessDetails class");
		Common.printFunctionName("Business Details - Select premises");
		Common.interactSpan(premises);
		log.info("Premises selected: " + premises);
		log.debug("Exit from whereDoYouRunYourBusinessFrom(String premises) method of BusinessDetails class");
		return this;
	}

	public BusinessDetails clickOnNextButton() throws DLGHealthCheckException {
		log.debug("Entering in clickOnNextButton() method of BusinessDetails class");
		Common.printFunctionName("Business Details - Click on next button");
		Common.interactButton("empNextBtn");
		log.info("Business Details - Click on next button");
		log.debug("Exit from clickOnNextButton() method of BusinessDetails class");
		return this;
	}

	public BusinessDetails doYouHaveEmployees(String employee) throws DLGHealthCheckException {
		log.debug("Entering in doYouHaveEmployees(String employee) method of BusinessDetails class");
		Common.printFunctionName("Business Details - Select employee");
		Common.interactSpan(employee);
		log.info("Business Details - Select employee " + employee);
		log.debug("Exit from doYouHaveEmployees(String employee) method of BusinessDetails class");
		return this;
	}

	public BusinessDetails getPolicyCoverage(String coverage) throws DLGHealthCheckException {
		log.debug("Entering in getPolicyCoverage (String coverage) method of BusinessDetails class");
		Common.printFunctionName("Business Details - Select coverage");
		// Common.InteractSpan(coverage,"Actions");
		Common.interactSpan(coverage);
		log.info("Business Details - Select coverage " + coverage);
		log.debug("Exit from getPolicyCoverage (String coverage) method of BusinessDetails class");
		return this;
	}

	public GeneralInformation clickOnContinueButton() throws DLGHealthCheckException {
		log.debug("Entering in clickOnContinueButton() method of BusinessDetails class");
		Common.printFunctionName("Business Details - Click on continue button");
		Common.interactButton("continueBtn");
		GeneralInformation generalInformation = new GeneralInformation();
		// DLG.setGeneralInformation(generalInformation);
		log.info("Business Details - Click on continue button");
		log.debug("Exit from clickOnContinueButton() method of BusinessDetails class");
		WaitUtil.waitForSpinner(driver);
		return generalInformation;
	}

}
