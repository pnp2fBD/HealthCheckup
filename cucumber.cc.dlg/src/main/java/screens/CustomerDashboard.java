package screens;



import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;

import UXP.Common;
import UXP.DLGGlobal;
import base.DLG;
import base.DLGHealthCheckException;
import utility.WaitUtil;

public class CustomerDashboard extends DLG {

	public static final Logger log = Logger.getLogger(CustomerDashboard.class.getName());

	public static String completeButton = "//button[@id='C2__BUT_A591663470A827D3151125']";
	public static String completeButtonOld = "//button[@id='C2__BUT_A5571F6A30C4735F1601285']";
	public static String completeButtonUAT = "//button[@id='C1__Header-Complete']";
	public static String documentsTab = "//div[contains(@id,'ITM_32BF5DA7372009F11482915')]";
	public static String tableForDoc = "#C2__C6__TBL_BD388A52554BC5E72766684 tr";
	WebElement element;
	
	
	public CustomerDashboard clickPassedVerification() {
		// Common.InteractButton("pssdVrfctn");
		return this;
	}

	public CustomerDashboard clickFailedVerification() {
		// Common.InteractButton("fldVrfctn");
		return this;
	}

	public CustomerDashboard clickOnComplete() throws DLGHealthCheckException{
		try{
			log.debug("Entered in clickOnComplete() method of CustomerDashboard class");
			String env = System.getProperty("environment");
			WaitUtil.waitForSpinner(driver);
			if (env.equalsIgnoreCase("CT"))
				Common.InteractButton(completeButtonUAT, "Complete");
			else if((env.equalsIgnoreCase("UAT"))||(env.equalsIgnoreCase("CD")))
				Common.InteractButton(completeButtonUAT, "Complete");
			else
				Common.InteractButton(completeButton, "Complete");
			log.info("Clicks on complete button");
			WaitUtil.waitForSpinner(driver);
			log.debug("Exit from clickOnComplete() method of CustomerDashboard class");
			return this;}
		catch (WebDriverException e) {
			DLGGlobal.getDriver().close();
			throw new DLGHealthCheckException("Customer dashboard not loading. "+e.getMessage(), e);
		} catch (Exception e) {
			DLGGlobal.getDriver().close();
			e.printStackTrace();
			return null;
		}
	}
	
	public CustomerDashboard verifyDocumentGeneration() throws DLGHealthCheckException{
		try{
			log.debug("Entered in verifyDocumentGeneration() method of CustomerDashboard class");
			WaitUtil.waitForSpinner(driver);
			Common.InteractButton(documentsTab, "Documents");
			WaitUtil.waitForSpinner(driver);
			
			element  = driver.findElement(By.cssSelector(tableForDoc));
			
			WaitUtil.waitTillVisibilityOfElementLocated(driver, 180, element);
			List <WebElement> docTable = driver.findElements(By.cssSelector(tableForDoc));
				if(docTable.size()>1){
					return this;
				}
				else{throw new NoSuchElementException("Document Element not present.");}
			}
		catch (WebDriverException e) {
			DLGGlobal.getDriver().close();
			throw new DLGHealthCheckException("Element not found.", e);
		} catch (Exception e) {
			DLGGlobal.getDriver().close();
			e.printStackTrace();
			return null;
		}
		
	}

	/*
	 * Should be part of a generic function. public AgentDashboard
	 * clickOnComplete(){ Common.InteractButton("cmpltBtn"); AgentDashboard
	 * agentDashboard = new AgentDashboard();
	 * DLG.setAgentDashboard(agentDashboard); return agentDashboard; }
	 */
	public AgentDashboard clickOnNewQuote() {
		log.debug("Entered in clickOnNewQuote() method of CustomerDashboard class");
		// Common.InteractButton("nwQt");
		AgentDashboard agentDashboard = new AgentDashboard();
		DLG.setAgentDashboard(agentDashboard);
		WaitUtil.waitForSpinner(driver);
		log.debug("Exit from clickOnNewQuote() method of CustomerDashboard class");
		return agentDashboard;
	}
}
