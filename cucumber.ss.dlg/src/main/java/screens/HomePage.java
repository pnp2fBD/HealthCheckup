package screens;



import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriverException;

import UXP.Common;
import UXP.DLGGlobal;
import base.DLG;
import exception.DLGHealthCheckException;
import utility.WaitUtil;


public class HomePage extends DLG {
  private static final Logger log = Logger.getLogger(HomePage.class.getName());
  private String title = "Customer Self Service";

  public HomePage(String environment) {
    super(environment);
  }

  public static String SignInButton = "//div/button[@id='BUT_E14D31D21EBB8E7F24944']";

  public Login clickOnSignInButton() throws DLGHealthCheckException{
	  try{
	    WaitUtil.waitForSpinner(driver);
	    if(!DLGGlobal.getDriver().getTitle().equals(title)){
			throw new WebDriverException("Page title displayed is not as expected. Page title is - "+DLGGlobal.getDriver().getTitle()); 
		}else{
		    log.debug("Entering in clickOnSignInButton() method of HomePage Class ");
		    Common.printFunctionName("Home Page - Click on SignIn button");
		    Common.InteractButton(SignInButton, "SignIn");
		    log.info("SignIn button clicked successfully");
		    Common.addDelay();
		    String env = System.getProperty("environment");
		    Login login = new Login(env);
		    DLG.setLogin(login);
		    log.debug("Exit from clickOnSignInButton() method of HomePage Class ");
		    return login;}
	    }
	  catch (WebDriverException e) {
		  DLGGlobal.getDriver().close();
		  throw new DLGHealthCheckException("Unable to click on SignInButton - HomePage", e);	 
	  }catch(Exception e){
		  DLGGlobal.getDriver().close();
		  e.printStackTrace();
		  return null;
		}
  }


}
