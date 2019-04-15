package screens;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriverException;

import UXP.Common;
import UXP.DLGGlobal;
import base.DLG;
import exception.DLGHealthCheckException;
import utility.WaitUtil;

public class CustomerDashboard extends DLG {
	private static final Logger log = Logger.getLogger(CustomerDashboard.class.getName());

	public static String gridPolicy = "//span[@id='QUE_B3A80BADFCD28CFD90822_R1']";
	public static String headerPolicy = "//div[@class='col-md-2 col-sm-2 col-xs-11 noPadLft'][1]";

	public CustomerDashboard verifyPolicyNumbers() throws DLGHealthCheckException {
		try {
			WaitUtil.waitForSpinner(driver);
			log.debug("Entering in VerifyPolicyNumbers() method of CustomerDashBoard class ");
			DLGGlobal.setGridPolicyNumber(Common.captureElementTextByXpath("Grid Policy: ", gridPolicy));
			DLGGlobal.setHeaderPolicyNumber(Common.captureElementTextByXpath("Header ", headerPolicy));
			Common.compareValues(DLGGlobal.getGridPolicyNumber(), DLGGlobal.getHeaderPolicyNumber().substring(8),
					"Policy Number");
			log.info("Verification successful  of the policy number");
			log.debug("Exit from VerifyPolicyNumbers() method of CustomerDashBoard class ");
			return this;
		} catch (WebDriverException e) {
			  DLGGlobal.getDriver().close();
			  throw new DLGHealthCheckException("Unable to verify Policy Number on Customer Dashboard", e);	 
		  }catch(Exception e){
			  DLGGlobal.getDriver().close();
			  e.printStackTrace();
			  return null;
			}
	}

}
