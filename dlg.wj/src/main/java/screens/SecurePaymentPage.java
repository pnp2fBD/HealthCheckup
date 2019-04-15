package screens;

import base.DLG;
import base.DLGHealthCheckException;
import common.Common;
import utility.WaitUtil;

public class SecurePaymentPage extends DLG {

	public SecurePaymentPage EnterCardInformation() throws InterruptedException, DLGHealthCheckException {
		Common.printFunctionName("Secure Payment - Click on card");
		Common.switchToIframe("//iframe[@title='Payment Pages']");
		WaitUtil.waitForSpinner(driver);
		Common.interactInput("SPPCardNumber", "4444333322221111");
		Thread.sleep(1000);
		Common.interactInput("SPPExpMonth", "12");
		//Common.interactListbox("SPPExpMonth", "12");
		Thread.sleep(1000);
		Common.interactInput("SPPExpYear", "25");
		//Common.interactListbox("SPPExpYear", "2020");
		Thread.sleep(1000);
		Common.interactInput("SPPCardHolderName", "SSP Card");
		// Common.scrollToBottom();
		Thread.sleep(1000);
		Common.interactInput("SPPCVV", "895");
		return this;
	}

	public WorldPay clickOnBuyPolicyButton() throws DLGHealthCheckException {
		Common.printFunctionName("Secure Payment - Click on Buy Policy button");
		Common.interactButton("SPPBuyPolicy");
		WorldPay worldPay = new WorldPay();
		// DLG.setWorldPay(worldPay);
		return worldPay;
	}
}
