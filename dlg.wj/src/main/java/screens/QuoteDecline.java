package screens;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import base.DLG;

public class QuoteDecline extends DLG {
	Logger log = Logger.getLogger(QuoteDecline.class.getName());
	By goBackAndEditQuoteBtn = By.xpath(OR.getProperty("goBackandEditQuoteBtn"));
	static int count = 0;

	public boolean isQuoteDecline() {
		log.info("Quote Decline");
		List<WebElement> listOfElement = driver.findElements(goBackAndEditQuoteBtn);
		if (listOfElement.size() > 0)
			return true;
		return false;
	}

	public GeneralInformation clickOnGoBackAndEditQuoteBtn() {
		driver.findElement(goBackAndEditQuoteBtn).click();
		return new GeneralInformation();
	}

	public int numberOfCountsQuoteDecline() {
		count++;
		return count;
	}
}