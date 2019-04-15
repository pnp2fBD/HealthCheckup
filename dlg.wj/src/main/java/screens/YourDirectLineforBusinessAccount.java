/**
 * 
 */
package screens;

import org.apache.log4j.Logger;

import base.DLG;
import base.DLGHealthCheckException;
import common.Common;
import utility.WaitUtil;

/**
 * @author Jheelum.Dutta
 *
 */
public class YourDirectLineforBusinessAccount extends DLG {

	private static final Logger log = Logger.getLogger(YourDirectLineforBusinessAccount.class.getName());
	

	public SucessfulAccountCreation yourDetail(String title, String email)
			throws DLGHealthCheckException, NumberFormatException, InterruptedException {
		

		Common.interactListbox("titleSlct", title);
		log.info("Title is " + title);
		WaitUtil.waitForSpinner(driver);
		Common.interactInput("confirmEmail__directLine", email + "@ssp-uk.com");
		log.info("Contact Email Address " + email + "@ssp-uk.com");
		Common.interactButton("heading_yourdirect_line");
		WaitUtil.waitForSpinner(driver);
		Common.interactButton("btnconfirm_yourdirect_line");
		WaitUtil.waitForSpinner(driver);
		SucessfulAccountCreation sucessfulAccountCreation = new SucessfulAccountCreation();
		return sucessfulAccountCreation;
	}

}