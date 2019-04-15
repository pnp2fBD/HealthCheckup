package common;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.Reporter;
import base.DLG;
import base.DLGHealthCheckException;
import listeners.CustomListeners;
import utility.WaitUtil;

public class Common extends DLG {

	private static final Logger log = Logger.getLogger(Common.class.getName());
	private static final String codeIssueErrorMessage = "Their is some issue at code level. Please check with automation test suite developers. The exception message is ";
	private static String [] errorMessageToThrow;

	private static void captureMessagesOnFailure(String elementId, String errorMessage) throws DLGHealthCheckException {
		Reporter.log(ReportFormatter.printMessage(ReportFormatter.getFailMessage() + elementId + errorMessage));
		log.error(errorMessage);
		errorMessageToThrow = errorMessage.split(" The exception message is");
		terminateScenario();
		throw new DLGHealthCheckException(elementId + " " + errorMessageToThrow[0]);
	}

	private static void captureMessagesOnFailure(String errorMessage) throws DLGHealthCheckException {
		Reporter.log(ReportFormatter.printMessage(ReportFormatter.getFailMessage() + errorMessage));
		log.error(errorMessage);
		errorMessageToThrow = errorMessage.split(" The exception message is");
		terminateScenario();
		throw new DLGHealthCheckException(errorMessageToThrow[0]);
	}

	public static boolean doesElementExists(String id) throws DLGHealthCheckException {
		int elementSize = driver.findElements(By.id(id)).size();
		if (elementSize > 0) {
			return true;
		} else {
			captureMessagesOnFailure(id, "Element not found on the Page");
			return false;
		}
	}

	public static void policyNoConfirmed(boolean doesElementExists) throws DLGHealthCheckException {
		if (doesElementExists == true) {
			log.info("Policy URN generated");
		} else {
			captureMessagesOnFailure("Policy URN not generated");
		}
	}

	public static void captureElementText(String elementName, String id) {
		String elementText = driver.findElement(By.id(id)).getText();
		log.debug("Entering in captureElementText(String elementName,String id) where elementName is " + elementName
				+ " and id value is " + elementText);
	}

	public static void switchToIframe(String xpath) {
		WebElement element = driver.findElement(By.xpath(xpath));
		driver.switchTo().frame(element);
	}

	public static void moveToMainWindow() {
		driver.switchTo().parentFrame();
	}

	// Function for Interacting with Input box. - This is overridden function
	// where terminate flag can be set to true.
	public static void interactInput(String objectInfo, String inputValue) throws DLGHealthCheckException {
		try {
			String xpath = OR.getProperty(objectInfo);
			By locator = By.xpath(xpath);
			WaitUtil.waitTillVisibilityOfElementLocated(driver, 20, locator);
			log.debug("Xpath is " + xpath);
			WebElement element = driver.findElement(locator);
			element.sendKeys(inputValue);
			Reporter.log(ReportFormatter.printMessage(ReportFormatter.getPassMessage() + " - Able to enter ",
					inputValue, " successfully in Inputbox ", objectInfo));
		} catch (WebDriverException e) {
			captureMessagesOnFailure(objectInfo,
					"Unable to enter the value in Input field. The exception message is " + e.getMessage());
		} catch (Exception e) {
			captureMessagesOnFailure(objectInfo, codeIssueErrorMessage + e.getMessage());
		}
	}

	// Function for Interacting with a button.
	public static void interactButton(String objectInfo) throws DLGHealthCheckException {
		try {
			String xpath = OR.getProperty(objectInfo);
			By locator = By.xpath(xpath);
			WaitUtil.waitTillVisibilityOfElementLocated(driver, 20, locator);
			log.debug("Xpath is " + xpath);
			WebElement element = driver.findElement(locator);
			element.click();
			Reporter.log(ReportFormatter.printMessage(ReportFormatter.getPassMessage() + " - able to click on element",
					objectInfo));
		} catch (WebDriverException e) {
			captureMessagesOnFailure(objectInfo,
					"Issue while clicking on the button. The exception message is " + e.getMessage());
		} catch (Exception e) {
			captureMessagesOnFailure(objectInfo, codeIssueErrorMessage + e.getMessage());
		}
	}

	public static void interactButtonUsingJavaScript(String objectInfo) throws DLGHealthCheckException {
		try {
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("arguments[0].click();", driver.findElement(By.xpath(OR.getProperty(objectInfo))));
		} catch (WebDriverException e) {
			captureMessagesOnFailure(objectInfo,
					"Issue while clicking on the button. The exception message is " + e.getMessage());
		} catch (Exception e) {
			captureMessagesOnFailure(objectInfo, codeIssueErrorMessage + e.getMessage());
		}
	}

	public static void interactListbox(String objectInfo, int listIndex) throws DLGHealthCheckException {
		try {
			By locator = By.xpath(OR.getProperty(objectInfo));
			WaitUtil.waitTillVisibilityOfElementLocated(driver, 20, locator);
			log.debug("Xpath is " + OR.getProperty(objectInfo));
			new Select(driver.findElement(By.xpath(OR.getProperty(objectInfo)))).selectByIndex(listIndex);
			Reporter.log(ReportFormatter.printMessage(ReportFormatter.getPassMessage() + " - Able to select from ",
					objectInfo, " successfully"));
		} catch (WebDriverException e) {
			captureMessagesOnFailure(objectInfo,
					"Unable to select value from listbox. The exception message is " + e.getMessage());
		} catch (Exception e) {
			captureMessagesOnFailure(objectInfo, codeIssueErrorMessage + e.getMessage());
		}
	}

	public static void interactListbox(String objectInfo, String value) throws DLGHealthCheckException {
		try {
			By locator = By.xpath(OR.getProperty(objectInfo));
			WaitUtil.waitTillVisibilityOfElementLocated(driver, 20, locator);
			log.debug("Xpath is " + OR.getProperty(objectInfo));
			new Select(driver.findElement(By.xpath(OR.getProperty(objectInfo)))).selectByVisibleText(value);
			Reporter.log(ReportFormatter.printMessage(ReportFormatter.getPassMessage() + " - Able to select from ",
					objectInfo, " successfully"));
		} catch (WebDriverException e) {
			captureMessagesOnFailure(objectInfo,
					"Unable to select value from listbox. The exception message is " + e.getMessage());
		} catch (Exception e) {
			captureMessagesOnFailure(objectInfo, codeIssueErrorMessage + e.getMessage());
		}
	}

	// Function for Interacting with a Radiobutton.
	public static void interactRadioButton(String radioValue) throws DLGHealthCheckException {
		try {
			By locator = By.xpath(OR.getProperty("genericPPrefix") + radioValue + OR.getProperty("genericPSuffix"));
			WaitUtil.waitTillVisibilityOfElementLocated(driver, 20, locator);
			log.debug("Xpath is " + (OR.getProperty("genericPPrefix") + radioValue + OR.getProperty("genericPSuffix")));
			driver.findElement(locator).click();
			Reporter.log(ReportFormatter.printMessage(
					ReportFormatter.getPassMessage() + " - Able to click radiobutton ", radioValue, " successfully"));
		} catch (WebDriverException e) {
			captureMessagesOnFailure("Unable to select radio button. The exception message is " + e.getMessage());
		} catch (Exception e) {
			captureMessagesOnFailure(codeIssueErrorMessage + e.getMessage());
		}
	}

	// Function for Interacting with a Span.
	public static void interactSpan(String span) throws DLGHealthCheckException {
		try {
			By locator = By.xpath(OR.getProperty("genericSpanPrefix") + span + OR.getProperty("genericSpanSuffix"));
			WaitUtil.waitTillElementClickable(driver, 20, locator);
			WaitUtil.waitForSpinner(driver);
			driver.findElement(locator).click();
			Reporter.log(ReportFormatter.printMessage(ReportFormatter.getPassMessage() + " - Able to click span ", span,
					" successfully"));
		} catch (WebDriverException e) {
			captureMessagesOnFailure("Unable to click on span. The exception message is " + e.getMessage());
		} catch (Exception e) {
			captureMessagesOnFailure(codeIssueErrorMessage + e.getMessage());
		}
	}
	
	public static void interactSpanDirectly(String spanXpath) throws DLGHealthCheckException {
		try {
			By locator = By.xpath(OR.getProperty(spanXpath));
			WaitUtil.waitTillElementClickable(driver, 20, locator);
			WaitUtil.waitForSpinner(driver);
			driver.findElement(locator).click();
			Reporter.log(ReportFormatter.printMessage(ReportFormatter.getPassMessage() + " - Able to click span ", spanXpath,
					" successfully"));
		} catch (WebDriverException e) {
			captureMessagesOnFailure("Unable to click on span. The exception message is " + e.getMessage());
		} catch (Exception e) {
			captureMessagesOnFailure(codeIssueErrorMessage + e.getMessage());
		}
	}

	// Function for Interacting with a Span - Overload 1.
	public static void interactSpan(String span, int index) throws DLGHealthCheckException {
		try {
			By locator = By.xpath(OR.getProperty("genericSpanPrefix") + span + OR.getProperty("genericSpanSuffix") + "["
					+ index + "]");
			WaitUtil.waitTillVisibilityOfElementLocated(driver, 20, locator);
			driver.findElement(locator).click();
			Reporter.log(ReportFormatter.printMessage(ReportFormatter.getPassMessage() + " - Able to click span ", span,
					" successfully"));
		} catch (WebDriverException e) {
			captureMessagesOnFailure("Unable to click on span. The exception message is " + e.getMessage());
		} catch (Exception e) {
			captureMessagesOnFailure(codeIssueErrorMessage + e.getMessage());
		}
	}

	// Function for Interacting for Autocomplete
	public static void interactAutoComplete(String index) throws DLGHealthCheckException {
		try {
			By locator = By
					.xpath(OR.getProperty("professionDivPrefix") + index + OR.getProperty("professionDivSuffix"));
			WaitUtil.waitTillVisibilityOfElementLocated(driver, 20, locator);
			driver.findElement(locator).click();
			Reporter.log(ReportFormatter.printMessage(ReportFormatter.getPassMessage() + " - Able to select index ",
					index, " successfully"));
		} catch (WebDriverException e) {
			captureMessagesOnFailure("Unable to auto-complete field " + e.getMessage());
		} catch (Exception e) {
			captureMessagesOnFailure(codeIssueErrorMessage + e.getMessage());
		}
	}

	// Function for checking browser title.
	public static void checkBrowserTitle(String title) throws DLGHealthCheckException {
		try {
			if (driver.getTitle().equals(title)) {
				Reporter.log(ReportFormatter
						.printMessage(ReportFormatter.getPassMessage() + " - Expected and Actual title are matching.")
						+ ReportFormatter.getNextLineCharacter());
				Reporter.log(ReportFormatter.getSpaceCharacter() + "Expected title - "
						+ ReportFormatter.getValuePrefix() + title + ReportFormatter.getValueSuffix()
						+ ReportFormatter.getNextLineCharacter() + ReportFormatter.getSpaceCharacter()
						+ "Actual title - " + ReportFormatter.getValuePrefix() + driver.getTitle()
						+ ReportFormatter.getValueSuffix());
			} else {
				Reporter.log(ReportFormatter.printMessage(
						ReportFormatter.getFailMessage() + " - Expected and Actual title are not matching.")
						+ ReportFormatter.getNextLineCharacter());
				Reporter.log(ReportFormatter.getSpaceCharacter() + "Expected title - "
						+ ReportFormatter.getValuePrefix() + title + ReportFormatter.getValueSuffix()
						+ ReportFormatter.getNextLineCharacter() + ReportFormatter.getSpaceCharacter()
						+ "Actual title - " + ReportFormatter.getValuePrefix() + driver.getTitle()
						+ ReportFormatter.getValueSuffix());
				throw new WebDriverException("Browser Title not matching: " + driver.getTitle());
			}
		} catch (WebDriverException e) {
			captureMessagesOnFailure("Unable to validate the title" + e.getMessage());
		} catch (Exception e) {
			captureMessagesOnFailure(codeIssueErrorMessage + e.getMessage());
		}
	}

	public static void doesFooterExists(String text) throws DLGHealthCheckException {
		try {
			Assert.assertTrue(!driver
					.findElements(
							By.xpath(OR.getProperty("genericParaPrefix") + text + OR.getProperty("genericParaSuffix")))
					.isEmpty());
			Reporter.log(ReportFormatter.printMessage(ReportFormatter.getPassMessage() + " - Element ", text,
					" exists on the webpage."));
		} catch (WebDriverException e) {
			captureMessagesOnFailure("Unable to validate the footer" + e.getMessage());
		} catch (Exception e) {
			captureMessagesOnFailure(codeIssueErrorMessage + e.getMessage());
		}
	}

	public static void printFunctionName(String functionName) {
		Reporter.log(ReportFormatter.printFunctionName(functionName));
	}

	public static void captureFailureScreenShot() {
		if (driver != null) {
			new CustomListeners().onTestFailure(null);
		}
	}

	public static void quitDriver() {
		driver.quit();
		driver = null;
		ReportFormatter.stepNumber = 1;
	}

	public static void terminateScenario() {
		captureFailureScreenShot();
		quitDriver();
	}
}
