package screens;

import org.apache.log4j.Logger;

import base.DLG;
import base.DLGHealthCheckException;
import common.Common;
import utility.WaitUtil;

public class Contacts extends DLG {
	
	private static final Logger log = Logger.getLogger(Contacts.class.getName());

	public Contacts pickAuthorisedPerson (int authorisedPersonIndex) throws DLGHealthCheckException{
		log.debug("Entering in pickAuthorisedPerson (int authorisedPersonIndex) method of Contacts class");
		Common.printFunctionName("Contacts - Select authorised person");
		Common.interactSpanDirectly("cNo");
		//Common.checkLoadingImage();	//Method to check whether loading image exists or not.
		log.info("Contacts - Select authorised person  "+authorisedPersonIndex);
		WaitUtil.waitForSpinner(driver);
		log.debug("Exit from pickAuthorisedPerson (int authorisedPersonIndex) method of Contacts class");
		return this;
	}	

	public Payment clickOnCheckOutButton() throws DLGHealthCheckException{		
		log.debug("Entering in clickOnCheckOutButton() method of Contacts class");
		Common.printFunctionName("Contacts - Click on CheckOUt button");
		Common.interactButton("cGTChckUt");
		log.info("Contacts - Click on CheckOut button");
		WaitUtil.waitForSpinner(driver);
		Payment payment = new Payment();
		//DLG.setPayment(payment);
		log.debug("Exit from clickOnCheckOutButton() method of Contacts class");
		return payment;
	}
}
