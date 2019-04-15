package screens;

import org.apache.log4j.Logger;

import base.DLG;
import base.DLGHealthCheckException;
import common.Common;
import utility.WaitUtil;

public class People extends DLG {

	private static final Logger log = Logger.getLogger(People.class.getName());

	public People pickTypeOfEmployees(String employeesListIndex) throws NumberFormatException, DLGHealthCheckException {
		log.debug("Entering in pickTypeOfEmployees (String employeesListIndex) method of People class");
		Common.printFunctionName("People In Your Business - Enter employee information");
		Common.interactListbox("pplSlct", Integer.parseInt(employeesListIndex));
	//	Common.checkLoadingImage(); // Method to check whether loading image
									// exists or not.
		log.info("Select the Type of employees. selected index is " + employeesListIndex);
		log.debug("Exit from pickTypeOfEmployees (String employeesListIndex) method of People class");
		return this;
	}

	public Liability clickOnNextButton() throws DLGHealthCheckException {
		WaitUtil.waitForSpinner(driver);
		log.debug("Entering in clickOnNextButton() method of People class");
		Common.printFunctionName("People In Your Business - Click on Next button");
		Common.interactButton("pplNextBtn");
		log.info("Click on the Next button in People Module");
		WaitUtil.waitForSpinner(driver);
		Liability liability = new Liability();
		//DLG.setLiability(liability);
		log.debug("Exit from clickOnNextButton() method of People class");
		WaitUtil.waitForSpinner(driver);
		return liability;
	}

}
