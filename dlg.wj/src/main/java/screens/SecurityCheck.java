package screens;

import base.DLG;
import base.DLGHealthCheckException;
import common.Common;

public class SecurityCheck extends DLG {

	public SecurityCheck(String environment) {
		super(environment);
	}

	public SecurityCheck enterSecurityKey(String securityKey) throws DLGHealthCheckException {
		Common.printFunctionName("Security check - Enter security key");
		Common.interactInput("securityTxt", securityKey);
		return this;
	}

	public BusinessDetails clickOnProceedButton() throws DLGHealthCheckException {
		Common.printFunctionName("Security check - Click on proceed button");
		Common.interactButton("proceedBtn");
		Common.interactButton("proceedBtn");
		BusinessDetails businessDetails = new BusinessDetails();
		// DLG.setBusinessDetails(businessDetails);
		return businessDetails;
	}

	public void checkBrowserTitle(String title) throws DLGHealthCheckException {
		Common.printFunctionName("Security check - Browser title");
		Common.checkBrowserTitle(title);
	}

	public void checkFooterText(String footerText) throws DLGHealthCheckException {
		Common.printFunctionName("Security check - Footer text");
		Common.doesFooterExists(footerText);
	}
}
