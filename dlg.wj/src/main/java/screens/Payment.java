package screens;

import org.apache.log4j.Logger;

import base.DLG;
import base.DLGHealthCheckException;
import common.Common;
import utility.WaitUtil;

public class Payment extends DLG {

	private static final Logger log = Logger.getLogger(Payment.class.getName());

	public Payment CompletePayment(String paymentMethod, String consent) throws DLGHealthCheckException {
		log.debug("Entering in CompletePayment (String paymentMethod, String consent) method of Payment class");
		Common.printFunctionName("Payment - Complete Payment");
		WaitUtil.waitForSpinner(driver);
		Common.interactButton(paymentMethod);
		WaitUtil.waitForSpinner(driver); 
		Common.interactRadioButton(consent);
		WaitUtil.waitForSpinner(driver);
		log.info("Interacted with the Payment method button " + paymentMethod);
		log.debug("Exit from CompletePayment (String paymentMethod, String consent) method of Payment class");
		return this;
	}

	public PaymentInformation clickOnNextButton() throws DLGHealthCheckException {
		log.debug("Entering in CompletePayment (String paymentMethod, String consent) method of Payment class");
		Common.printFunctionName("Payment - Click on Next button");
		Common.interactButton("PaymentNextBtn");
		log.info("Clicked on Next button on Payment page");
		WaitUtil.waitForSpinner(driver);
		PaymentInformation paymentInformation = new PaymentInformation();
		//DLG.setPaymentInformation(paymentInformation);
		log.debug("Exit from clickOnNextButton() method of Payment class");
		return paymentInformation;
	}
}
