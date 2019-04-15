package screens;

import org.apache.log4j.Logger;

import base.DLG;
import base.DLGHealthCheckException;
import common.Common;
import utility.WaitUtil;

public class InterestedParties extends DLG {

	private static final Logger log = Logger.getLogger(InterestedParties.class.getName());
	public InterestedParties pickInterestedParties (int interestedPartiesIndex) throws DLGHealthCheckException{
		log.debug("Entering in pickInterestedParties (int interestedPartiesIndex) method of InterestedParties class");
		Common.printFunctionName("Interested Parties - Select interested parties");		
		Common.interactSpanDirectly("ipNo");
		WaitUtil.waitForSpinner(driver);
		log.info("Interested party Index selects No. index is "+interestedPartiesIndex);
		log.debug("Exit from pickInterestedParties (int interestedPartiesIndex) method of InterestedParties class");
		return this;
	}	

	public Contacts clickOnNextButton() throws DLGHealthCheckException{	
		log.debug("Entering in clickOnNextButton() method of InterestedParties class");
		Common.printFunctionName("Interested Parties - Click on Next button");		
		Common.interactButton("ipNxtBtn");
		log.info("Clicked on Next button - Interested parties");
		WaitUtil.waitForSpinner(driver);
		Contacts contacts = new Contacts();
		//DLG.setContacts(contacts);
		log.debug("Exit from clickOnNextButton() method of InterestedParties class");
		return contacts;
	}
}
