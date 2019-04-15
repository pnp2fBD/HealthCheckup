package screens;

import java.util.Calendar;
import java.util.GregorianCalendar;
import org.apache.log4j.Logger;
import base.DLG;
import base.DLGHealthCheckException;
import common.Common;
import utility.WaitUtil;

public class YourDetails extends DLG {

	private static final Logger log = Logger.getLogger(YourDetails.class.getName());

	public YourDetails clickOnCreateAccount() throws DLGHealthCheckException, InterruptedException {
		String env = System.getProperty("environment");
		if (env.equalsIgnoreCase("trg") || env.equalsIgnoreCase("uat2")) {
			
				Thread.sleep(6000);
		}
		Common.printFunctionName("Your Details - Click on Create an account");
		Common.interactButton("crtAccnt");
		//Common.checkLoadingImage();
		return this;
	}

	public YourDetails enterDataPostSaveClose(String telephoneNumber, String marketingPreference) throws DLGHealthCheckException {
		Common.printFunctionName("Your Details - Enter policyholder details");
		String email = getEmail();
		Common.interactInput("contactEmailAddress", email + "@ssp-uk.com");
		//Common.checkLoadingImage();
		Common.interactInput("confirmEmail", email + "@ssp-uk.com");
		//Common.checkLoadingImage();
		Common.interactInput("telephoneNumber", telephoneNumber);
		//Common.checkLoadingImage();
		Common.interactRadioButton(marketingPreference);
		//Common.checkLoadingImage();
		Common.interactButton("ydSvAndCls");
		//Common.checkLoadingImage();
		WaitUtil.waitForSpinner(driver);
		return this;
	}

	public InterestedParties clickOnNextButton() throws DLGHealthCheckException {
		Common.printFunctionName("Your Details - Click on next button");
		Common.interactButton("ydNxtBtn");
		InterestedParties interestedParties = new InterestedParties();
		//DLG.setInterestedParties(interestedParties);
		return interestedParties;
	}

	public String getEmail() {
		Calendar cal = new GregorianCalendar();
		int month = cal.get(Calendar.MONTH); // 4
		month = month + 1;
		int year = cal.get(Calendar.YEAR); // 2013
		int sec = cal.get(Calendar.SECOND);
		int min = cal.get(Calendar.MINUTE);
		int date = cal.get(Calendar.DATE);
		int day = cal.get(Calendar.HOUR_OF_DAY);
		String env = System.getProperty("environment");
		StringBuffer strbuf = new StringBuffer();
		strbuf.append("HealthCheck").append(env).append(year).append(date).append(month).append(day).append(min)
				.append(sec);
		String emailId = strbuf.toString();
		log.info("Generated Email id is " + emailId);
		System.out.println(emailId);
		return emailId;

	}
}
