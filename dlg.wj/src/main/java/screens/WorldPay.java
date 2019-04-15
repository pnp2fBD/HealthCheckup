package screens;

import base.DLG;
import base.DLGHealthCheckException;

public class WorldPay extends DLG {

	public ConfirmationOfPayment clickOnContinueButton() throws DLGHealthCheckException{
//		Common.printFunctionName("World Pay - Click on Continue button");		
//		Common.InteractButton("wpCntn");
//		Common.checkLoadingImage();
		ConfirmationOfPayment confirmationOfPayment = new ConfirmationOfPayment();
		//DLG.setConfirmationOfPayment(confirmationOfPayment);
		return confirmationOfPayment;
	}
}
