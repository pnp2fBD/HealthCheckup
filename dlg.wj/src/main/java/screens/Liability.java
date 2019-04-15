package screens;

import org.apache.log4j.Logger;

import base.DLG;
import base.DLGHealthCheckException;
import common.Common;
import utility.WaitUtil;

public class Liability extends DLG {
	
	private static final Logger log = Logger.getLogger(Liability.class.getName());

	public Liability pickLiability (String publicLiabilityIndex, String productsIndex) throws NumberFormatException, DLGHealthCheckException{
		log.debug("Entering in pickInterestedParties (int interestedPartiesIndex) method of Liability class");
		Common.printFunctionName("Liability Coverage - Enter Liability information");		
		Common.interactListbox("lbltyPblcSlct", Integer.parseInt(publicLiabilityIndex));		
		//Common.checkLoadingImage();	//Method to check whether loading image exists or not.
		Common.interactListbox("lbltyPrdctsSlct", Integer.parseInt(productsIndex));		
		//Common.checkLoadingImage();	//Method to check whether loading image exists or not.
		log.debug("Exit from pickInterestedParties (int interestedPartiesIndex) method of Liability class");
		return this;
	}	

	public ImportantStatements clickOnNextButton() throws DLGHealthCheckException{
		WaitUtil.waitForSpinner(driver);
		log.debug("Entering in clickOnNextButton() method of Liability class");
		Common.printFunctionName("Liability Coverage - Click on Next button");		
		Common.interactButton("lbltyNextBtn");
		WaitUtil.waitForSpinner(driver);
		log.info("Liability Coverage - Click on Next button");
		ImportantStatements importantStatements = new ImportantStatements();
		//DLG.setImportantStatements(importantStatements);
		log.debug("Exit from clickOnNextButton() method of Liability class");
		WaitUtil.waitForSpinner(driver);
		return importantStatements;
	}	
	
}