package screens;

import org.apache.log4j.Logger;
import base.DLG;
import base.DLGHealthCheckException;
import common.Common;
import utility.WaitUtil;

public class ConfirmationOfPayment extends DLG {

  private static final Logger log = Logger.getLogger(ConfirmationOfPayment.class.getName());

  public ConfirmationOfPayment capturePolicyNumber(String objectId) throws DLGHealthCheckException {
	
	    log.debug(
	        "Entering in capturePolicyNumber(String objectId) method of ConfirmationOfPayment class");
	    Common.moveToMainWindow();
	    Common.printFunctionName("Confirmation of Payment - Capture Policy number");
	    WaitUtil.waitForSpinner(driver);
	    Common.doesElementExists(objectId);
	    Common.policyNoConfirmed(Common.doesElementExists(objectId));
	    Common.captureElementText("Policy number is ", objectId);
	    log.info("Policy number is generated");
	    log.debug(
	        "Exit from capturePolicyNumber(String objectId) method of ConfirmationOfPayment class");
	    return this;
  }

  public void closeBrowser() {
    log.debug("Entering in closeBrowser() method of ConfirmationOfPayment class");
    Common.quitDriver();
    log.debug("Exit from closeBrowser() method of ConfirmationOfPayment class");
  }
}
