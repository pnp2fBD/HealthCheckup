package screens;

import base.DLG;
import base.DLGHealthCheckException;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriverException;

import UXP.Common;
import UXP.DLGGlobal;

public class Login extends DLG {

	public static String username = "//div/input[@id='C2__QUE_ED508ADA5C44080859323']";
	public static String password = "//div/input[@id='C2__QUE_ED508ADA5C44080859331']";
	public static String loginButton = "//div/button[@id='C2__BUT_ED508ADA5C44080859357']";
	public static String contactCentreLogo = "//div[@id='C2__TXT_19B044C4A657962C224000']";
	public static final Logger log = Logger.getLogger(Login.class.getName());
	private String title = "Login"; 

	String env = System.getProperty("environment");

	public Login() {
		// TODO Constructor added by Purendra

	}

	public Login(String environment) {
		super(environment);
		log.debug("Exit the parametrized constructor of Login class");
	}

	public void hasScreenLoaded() throws DLGHealthCheckException {
		try {
			log.debug("Entered in hasScreenLoaded() method of Login class");
			Common.printFunctionName("Login Screen - check screen loading");
			if(DLGGlobal.getDriver().getTitle().equals(title)){
				Common.doesElementExists("Contact Centre Logo", contactCentreLogo);}
				else{throw new WebDriverException("Page title displayed is not as expected. Expected Page title -"+title
						+ ". Current Page title- "+DLGGlobal.getDriver().getTitle()+"Test");}
			if (!(env.equalsIgnoreCase("CD") || env.equalsIgnoreCase("CT"))) {
				log.debug("Locator of webelement contact centere logo is " + contactCentreLogo);
			}
			log.info("Completed the validation of the Login screen");
			log.debug("Exit from hasScreenLoaded() method of Login class");
		} catch (WebDriverException e) {
			DLGGlobal.getDriver().close();
			throw new DLGHealthCheckException("Issue with screen load- "+e.getMessage().split("Test")[0], e);
		} catch (Exception e) {
			DLGGlobal.getDriver().close();
			e.printStackTrace();
		}
	}

	public Login enterCredentials(String username, String password) throws DLGHealthCheckException {
		try {
			log.debug("Entered in enterCredentials(String username, String password) method of Login class");
			Common.printFunctionName("Login Screen - Enter login credentials");
			log.debug("Locator of webelement username is " + username);
			Common.InteractInput(Login.username, username, "username");
			DLGGlobal.setContactCentreLoginUser(username);
			log.debug("Locator of webelement password is " + password);
			Common.InteractInput(Login.password, password, "password");
			log.info("Entered the username and password on Login screen.");
			log.debug("Exit from enterCredentials(String username, String password) method of Login class");
			return this;
		} catch (WebDriverException e) {
			DLGGlobal.getDriver().close();
			throw new DLGHealthCheckException("Unable to enter credentials", e);
		} catch (Exception e) {
			DLGGlobal.getDriver().close();
			e.printStackTrace();
			return null;
		}
	}

	public AgentDashboard clickOnLoginButton() throws DLGHealthCheckException {
		try {
			log.debug("Entered in clickOnLoginButton() method of Login class");
			Common.printFunctionName("Login Screen - Click on login button");
			log.debug("Locator of Login button is " + loginButton);
			Common.InteractButton(loginButton, "Login");
			log.info("Clicked on Login button");
			AgentDashboard agentDashboard = new AgentDashboard();
			DLG.setAgentDashboard(agentDashboard);
			log.debug("Exit from clickOnLoginButton() method of Login class");
			return agentDashboard;
		} catch (WebDriverException e) {
			DLGGlobal.getDriver().close();
			throw new DLGHealthCheckException("Unable to click on Login button", e);
		} catch (Exception e) {
			DLGGlobal.getDriver().close();
			e.printStackTrace();
			return null;
		}
	}

	public void setPolicyNumber() throws DLGHealthCheckException {
		try {
			log.debug("Entered in setPolicyNumber() method of Login class");
			System.out.println("Inside setpolicy");
			Common.setPolicyNumber();
			log.info("Set the policy Number");
			log.debug("Exit from setPolicyNumber() method of Login class");
		} catch (WebDriverException e) {
			DLGGlobal.getDriver().close();
			throw new DLGHealthCheckException("Unable to set policy number", e);
		} catch (Exception e) {
			DLGGlobal.getDriver().close();
			e.printStackTrace();
		}
	}
}
