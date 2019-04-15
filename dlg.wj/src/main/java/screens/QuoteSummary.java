package screens;


import base.DLG;
import base.DLGHealthCheckException;
import common.Common;
import utility.WaitUtil;

public class QuoteSummary extends DLG{
	
	public QuoteSummary clickOnSaveExit() throws DLGHealthCheckException{
		Common.printFunctionName("Quote Summary - Click on Save and Exit");			
		Common.interactButton("svAndExtBtn");
		//Common.checkLoadingImage();
		return this;
	}
	
	public QuoteSummary enterDataPostSaveExit(String contactEmailAddress, String telephoneNumber, String marketingPreference) throws DLGHealthCheckException{
		Common.printFunctionName("Quote Summary - Enter policyholder details");		
		Common.interactInput("contactEmailAddress",contactEmailAddress + "@ssp-uk.com");		
		Common.interactInput("confirmEmail", contactEmailAddress + "@ssp-uk.com");
		Common.interactInput("telephoneNumber", telephoneNumber);
		Common.interactRadioButton(marketingPreference);
		Common.interactButton("svAndExtPBtn");
		return this;
	}
	
	
	
	
	public YourDirectLineforBusinessAccount clickOnReviewConfirm() throws DLGHealthCheckException{
		Common.printFunctionName("Quote Summary - Click on Review and Confirm");			
		Common.interactButton("rvwAndCnfrm");
		WaitUtil.waitForSpinner(driver);
		YourDirectLineforBusinessAccount details = new YourDirectLineforBusinessAccount();
		return details;
	}
	
		
}