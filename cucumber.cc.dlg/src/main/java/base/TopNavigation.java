package base;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriverException;

import UXP.Common;
import UXP.DLGGlobal;

public class TopNavigation extends DLG{

	public static String options = "//li[@id='C1__ITM_5866E32A0412B1F918363']";
	public static String logOut = "//div[@id='C1__ITM_5866E32A0412B1F918367']";
	public static String logOutOld = "//a[@id='signOut']";
	public static final Logger log = Logger.getLogger(TopNavigation.class.getName());
	 
	public void logOut() throws DLGHealthCheckException {
		try {
			log.debug("Entered in logOut() method of TopNavigation class");
			Common.printFunctionName("Logout from the application");
			Common.InteractLink(options, "options");
			String env = System.getProperty("environment");
			if (env.equalsIgnoreCase("CT"))
				Common.InteractLink(logOut, "logout");
			else
				Common.InteractLink(logOut, "logout");
			log.info("Clicked on Logout link");
			log.debug("Exit from logOut() method of TopNavigation class");
		} catch (WebDriverException e) {
			DLGGlobal.getDriver().close();
			throw new DLGHealthCheckException("Unable to Logout.", e);
		} catch (Exception e) {
			DLGGlobal.getDriver().close();
			e.printStackTrace();
		}
	}

	public void changePassword() {

	}

	public void closeBrowser() {
		log.debug("Entered in closeBrowser() method of TopNavigation class");
		Common.printFunctionName("Close the browser");
		Common.quitDriver();
		log.debug("Exit from closeBrowser() method of TopNavigation class");
	}

}