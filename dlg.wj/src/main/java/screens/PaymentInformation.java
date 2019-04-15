package screens;

import org.apache.log4j.Logger;

import base.DLG;
import base.DLGHealthCheckException;
import common.Common;
import utility.WaitUtil;

public class PaymentInformation extends DLG {

	private static final Logger log = Logger.getLogger(PaymentInformation.class.getName());
	public PaymentInformation CompletePaymentInformation () throws DLGHealthCheckException{
		log.debug("Entering in CompletePaymentInformation () method of PaymentInformation class");
		Common.printFunctionName("Payment Information - Complete Payment");		
		Common.interactSpanDirectly("piCardPolicyHolder");
		WaitUtil.waitForSpinner(driver);
		//Common.checkLoadingImage();	//Method to check whether loading image exists or not.
		Common.interactSpanDirectly("piCardPermissionOfCard");
		WaitUtil.waitForSpinner(driver);
		//Common.checkLoadingImage();
		Common.interactInput("piCardHolderName", "Cardholder Name");
//		Common.InteractInput("piCardHolderEmail", "cardHolderEmail@ssp-uk.com");		
		Common.interactSpanDirectly("piCardBillingaddress");
		log.info("Select Yes for both the options and entered the cardHolder name");
		log.debug("Exit from CompletePaymentInformation () method of PaymentInformation class");
		return this;
	}	
	public SecurePaymentPage clickOnNextButton() throws DLGHealthCheckException{
		log.debug("Entering in clickOnNextButton () method of PaymentInformation class");
		Common.printFunctionName("Payment Information - Click on Next button");		
		Common.interactButton("piNxtBtn");
		WaitUtil.waitForSpinner(driver);
		log.info("Clicked on next button");
		SecurePaymentPage securePaymentPage = new SecurePaymentPage();
		//DLG.setSecurePaymentPage(securePaymentPage);
		log.debug("Exit from clickOnNextButton () method of PaymentInformation class");
		return securePaymentPage;
	}
}
