package screens;



import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriverException;

import UXP.Common;
import UXP.DLGGlobal;
import base.DLG;
import exception.DLGHealthCheckException;
import utility.WaitUtil;

public class Login extends DLG {
  private static final Logger log = Logger.getLogger(Login.class.getName());

  public Login(String environment) {
    super(environment);
  }

  public static String username = "//div/input[@id='email_address']";
  public static String password = "//div/input[@id='password']";
  public static String signInButton = "//div/button[@id='sign_in']";
 

  public Login enterCredentials(String username, String password) throws DLGHealthCheckException{
	  try{
		  WaitUtil.waitForSpinner(driver);
		  log.debug(
		        "Entering in enterCredentials(String username, String password) method of Login Class ");
		    Common.printFunctionName("Login Screen - Enter login credentials");
		    Common.InteractInput(Login.username, username, "username");
		    Common.InteractInput(Login.password, password, "password");
		    log.info("Username is " + username + " and password is " + password
		        + ". Credentials entered on webpage");
		    log.debug(
		        "Exit from enterCredentials(String username, String password) method of Login Class ");
		    return this;    
			
	  }
	  catch (WebDriverException e) {
		  DLGGlobal.getDriver().close();
		  throw new DLGHealthCheckException("Unable to enter credentials", e);	 
	  }catch(Exception e){
		  DLGGlobal.getDriver().close();
		  e.printStackTrace();
		  return null;
		}  
  }

  public CustomerDashboard clickOnSignInButton() throws DLGHealthCheckException{
	  try{
	    WaitUtil.waitForSpinner(driver);
	    log.debug("Entering in clickOnSignInButton() method of Login Class ");
	    Common.printFunctionName("Login Screen - Click on SignIn button");
	    Common.InteractButton(signInButton, "SignIn");
	    log.info("Clicked on SignIn button");
	    Common.addDelay();
	    CustomerDashboard customerDashboard = new CustomerDashboard();
	    DLG.setCustomerDashboard(customerDashboard); 
	    log.debug("Exit from clickOnSignInButton() method of Login Class ");
	    return customerDashboard;}
	  catch (WebDriverException e) {
		  DLGGlobal.getDriver().close();
		  throw new DLGHealthCheckException("Unable to click on SignInButton - LoginPage", e);	 
	  }catch(Exception e){
		  DLGGlobal.getDriver().close();
		  e.printStackTrace();
		  return null;
		}
	   
  }

}
