package screens;

import org.apache.log4j.Logger;

import base.DLG;
import base.DLGHealthCheckException;
import common.Common;
import utility.WaitUtil;



public class YourBusiness extends DLG {

	private static final Logger log = Logger.getLogger(YourBusiness.class.getName());

	public YourBusiness yourBusinessDetails(String typeOfBusinessIndex,String yearsInBusiness,String businessGeographyIndex ) throws NumberFormatException, DLGHealthCheckException {
		Common.interactListbox("typeOfBusinessSlct", Integer.parseInt(typeOfBusinessIndex));    
		//Common.checkLoadingImage();
		WaitUtil.waitForSpinner(driver);
		Common.interactListbox("yearsInBusinessSlct", Integer.parseInt(yearsInBusiness));
		WaitUtil.waitForSpinner(driver);
		log.info("Years in Business " + yearsInBusiness);
		Common.interactListbox("businessGeographySlct", Integer.parseInt(businessGeographyIndex));   
		log.info("Business Geography is " + businessGeographyIndex);
	//	Common.checkLoadingImage();
		WaitUtil.waitForSpinner(driver);
		Common.interactButton("anotherOccupation");
		WaitUtil.waitForSpinner(driver);
		Common.interactButton("haveSubsidiary");
		WaitUtil.waitForSpinner(driver);
		return this;
	}
	
	public People clickOnNextButton() throws DLGHealthCheckException {
		WaitUtil.waitForSpinner(driver);
		log.debug("Entering in clickOnNextButton() method of your Business class");
		Common.printFunctionName("Your Business - Click on next button");
		Common.interactButton("yourBusinessNxtBtn");
		log.info("Clicked on the next button");
		WaitUtil.waitForSpinner(driver);
		People people = new People();
	//	DLG.setPeople(people);
		log.debug("Exit from clickOnNextButton() method of your Business class");
		return people;
	}

}


