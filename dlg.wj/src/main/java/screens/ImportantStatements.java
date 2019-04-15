package screens;

import org.apache.log4j.Logger;

import base.DLG;
import base.DLGHealthCheckException;
import common.Common;
import utility.WaitUtil;


public class ImportantStatements extends DLG {
	
	private static final Logger log = Logger.getLogger(ImportantStatements.class.getName());
	
	public ImportantStatements consent(String consent) throws DLGHealthCheckException{
		log.debug("Entering in consent(String consent) method of ImportantStatements class");
		Common.printFunctionName("Important Statements - Consent form");
		Common.interactButton("newImportantStatmntRdBtnIAgree");
		log.info("Clicked on I agree radio button");
		log.debug("Exit from consent(String consent) method of ImportantStatements class");
		return this;
	}	

	public PreviousLosses clickOnNextButton() throws DLGHealthCheckException{				
		log.debug("Entering in clickOnNextButton() method of ImportantStatements class");
		Common.printFunctionName("Important Statements - Click on Next button");		
		Common.interactButton("imprtntSttmntsNextBtn");
		WaitUtil.waitForSpinner(driver);
		log.info("Click on Next button - Important Statements");
		PreviousLosses previousLosses = new PreviousLosses();
		log.debug("Exit from clickOnNextButton() method of ImportantStatements class");
		return previousLosses;
	}	
}
