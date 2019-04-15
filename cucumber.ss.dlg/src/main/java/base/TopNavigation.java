package base;



import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;

import UXP.Common;
import exception.DLGHealthCheckException;

public class TopNavigation {

  public static String options =
      "//div/ul[@class='nav navbar-nav navbar-right white-text']/li[@id='ITM_B50EDC385FD6975585018']";
  public static String optionsNew = "//li[@id='ITM_4B30B4C0CA2951D419310']";
  public static String logOut = "//ul/li/a[@id='ITM_B50EDC385FD6975585021']";
  public static String logOutNew = "//a[@id='ID_SignOut']";
  public String env = System.getProperty("environment");

  public void logOut() throws DLGHealthCheckException{
	  try{
	    Common.printFunctionName("Logout from the application");
	    if (env.equalsIgnoreCase("CD") || env.equalsIgnoreCase("CT")) {
	      Common.InteractLink(options, "options");
	      Common.InteractLink(logOut, "logout");
	    } else {
	      Common.InteractLink(optionsNew, "optionsNew");
	      Common.InteractLink(logOutNew, "logoutNew");
	    }
    } catch (NoSuchElementException e) {
		throw new DLGHealthCheckException("Element not found.",e);
	}
	catch(TimeoutException e){
		throw new DLGHealthCheckException("Time Out",e);
	}
  }

  public void changePassword() {

  }

  public void closeBrowser() throws DLGHealthCheckException{
	  try{
	    Common.printFunctionName("Close the browser");
	    Common.quitDriver();}
	  catch (NoSuchElementException e) {
			throw new DLGHealthCheckException("Element not found.",e);
		}
		catch(TimeoutException e){
			throw new DLGHealthCheckException("Time Out",e);
		}
  	}

}
