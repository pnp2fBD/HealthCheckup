package screens;

import org.apache.log4j.Logger;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriverException;

import UXP.Common;
import UXP.DLGGlobal;
import base.DLG;
import base.DLGHealthCheckException;
import utility.WaitUtil;

public class AgentDashboard extends DLG {

	public static String takeCall = "//div/a[@id='C2__BUT_8A211A5516D895E938601']";
	public static String myDashboardLogo = "//div[@id = 'C2__FMT_EF285CEB836D4C46171056']/div/div/div/div/h2[@class='page-title' and text() = 'My Dashboard']";
	public static String welcomeMessageLogo = "//h4[contains(text(),'Welcome')]";
	public static String welcomeMessage = "Welcome " + DLGGlobal.getContactCentreLoginUser();
	public static final Logger log = Logger.getLogger(AgentDashboard.class.getName());
	String env = System.getProperty("environment");
	String title = "AgentDashboard";

	public void hasScreenLoaded() throws DLGHealthCheckException {
		try {
			log.debug("Entered in hasScreenLoaded() method of AgentDashboard class");
			WaitUtil.waitForSpinner(driver);
			Common.printFunctionName("AgentDashboard - check screen loading");
			if (DLGGlobal.getDriver().getTitle().equals(title)) {
				Common.doesElementExists(welcomeMessage, welcomeMessageLogo);
			} else {
				throw new NoSuchElementException("Page title displayed is not as expected. Expected Page title -"
						+ title + ". Current Page title- " + DLGGlobal.getDriver().getTitle()+"Test");
			}
			// Common.doesElementExists(welcomeMessage, welcomeMessageLogo);
			log.info("Screen loaded successfully of Agent Dashboard");
			log.debug("Exit from hasScreenLoaded() method of AgentDashboard class");
		} catch (WebDriverException e) {
			System.out.println(e.getMessage().split("Test")[0]);
			DLGGlobal.getDriver().close();
			throw new DLGHealthCheckException(e.getMessage().split("Test")[0], e);
		} catch (Exception e) {
			DLGGlobal.getDriver().close();
			e.printStackTrace();
		}
	}

	public Search takeCall() throws DLGHealthCheckException {
		try {
			log.debug("Entered in takeCall() method of AgentDashboard class");
			Common.printFunctionName("AgentDashboard - Take call");
			Common.InteractLink(takeCall, "Take Call");
			log.info("Interacted with Take call successfully");
			WaitUtil.waitForSpinner(driver);
			Common.checkLoadingImage();
			Search search = new Search();
			DLG.setSearch(search);
			log.debug("Exit from takeCall() method of AgentDashboard class");
			return search;
		} catch (WebDriverException e) {
			DLGGlobal.getDriver().close();
			throw new DLGHealthCheckException("Unable to click on Take Call.", e);
		} catch (Exception e) {
			DLGGlobal.getDriver().close();
			e.printStackTrace();
			return null;
		}
	}

	public Search makeCall() {
		log.debug("Entered in makeCall() method of AgentDashboard class");
		Common.printFunctionName("AgentDashboard - Make call");
		Common.InteractSpan("");
		Search search = new Search();
		DLG.setSearch(search);
		log.debug("Exit from makeCall() method of AgentDashboard class");
		return search;
	}

	public Search adminTask() {
		log.debug("Entered in adminTask() method of AgentDashboard class");
		Common.printFunctionName("AgentDashboard - Admin task");
		Common.InteractSpan("");
		Search search = new Search();
		DLG.setSearch(search);
		log.debug("Exit from adminTask() method of AgentDashboard class");
		return search;
	}
}