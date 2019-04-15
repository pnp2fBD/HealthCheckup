package screens;

import org.apache.log4j.Logger;
import base.DLG;
import base.DLGHealthCheckException;
import common.Common;
import utility.WaitUtil;

public class PreviousLosses extends DLG {
	private static final Logger log = Logger.getLogger(PreviousLosses.class.getName());
	public static GeneralInformation generalInformation;
	private static YourBusiness yourBusiness;
	public static People people;
	public static Liability liability;
	public static ImportantStatements importantStatements;
	public static PreviousLosses previousLosses;

	public PreviousLosses selectPreviousLosses(String anyPreviousLoss, String anyPreviousLossIndex) throws DLGHealthCheckException {
		log.debug(
				"Entering in selectPreviousLosses(String anyPreviousLoss,String anyPreviousLossIndex) method of PreviousLosses class");
		Common.printFunctionName("Previous Losses - Enter any previous loss");
		Common.interactButtonUsingJavaScript("previousLossesNo");
		WaitUtil.waitForSpinner(driver);
		log.debug(
				"Exit from selectPreviousLosses(String anyPreviousLoss,String anyPreviousLossIndex) method of PreviousLosses class");
		return this;
	}

	public QuoteSummary clickOnGetQuoteButton() throws DLGHealthCheckException {
		WaitUtil.waitForSpinner(driver);
		log.debug("Entering in  clickOnGetQuoteButton() method of PreviousLosses class");
		Common.printFunctionName("Previous Losses - Click on Get Quote");
		Common.interactButton("gtQtBtn");
		log.info("Clicked on Get Quote button");
		try {
			Thread.sleep(7000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		WaitUtil.waitForSpinner(driver);
		QuoteSummary quoteSummary = new QuoteSummary();
		QuoteDecline quoteDecline = new QuoteDecline();
		if (quoteDecline.isQuoteDecline() && quoteDecline.numberOfCountsQuoteDecline() < 2) {
			generalInformation = quoteDecline.clickOnGoBackAndEditQuoteBtn();
			yourBusiness = generalInformation.clickOnNextButton();
			people = yourBusiness.clickOnNextButton();
			liability = people.clickOnNextButton();
			importantStatements = liability.clickOnNextButton();
			previousLosses = importantStatements.clickOnNextButton();
			previousLosses.clickOnGetQuoteButton();
		}
		WaitUtil.waitForSpinner(driver);
		log.debug("Exit from  clickOnGetQuoteButton() method of PreviousLosses class");
		return quoteSummary;
	}
}
