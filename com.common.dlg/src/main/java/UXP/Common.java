package UXP;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import com.sun.jna.platform.FileUtils;

import listeners.CustomListeners;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class Common {
	
	public static void addDelay(){
		try {
			TimeUnit.SECONDS.sleep(30);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}		
	
	public static void setPolicyNumber(){
		DLGGlobal.setPolicyNumber("");		
	}
	
	public static String getPolicyNumber(){
		return DLGGlobal.getPolicyNumber();		
	}
	public static void doesElementExists(String elementName,String elementId){
	    try
	    {
	    	DLGGlobal.getDriver().findElement(By.xpath(elementId));
	        Reporter.log(ReportFormatter.printMessage(ReportFormatter.getPassMessage() + " - " +elementName, " exists on the web page"));
//	        return true;
	    }
	    catch (Exception e)
	    {
//	    	System.out.println("*******************************************************************");
//	    	System.out.println("***********Exception is ***************************"+ e.toString());
//	    	System.out.println("*******************************************************************");	    	
	        Reporter.log(ReportFormatter.printMessage(ReportFormatter.getFailMessage() + " - " +elementName, " does not exists on the web page"));
			terminateScenario();
//	        return false;
	    }
	}
	
	public static void captureElementText(String elementName,String id){        
		Reporter.log(ReportFormatter.printMessage(elementName + DLGGlobal.getDriver().findElement(By.id(id)).getText()));		
	}
	
	public static String captureElementTextByXpath(String elementName,String xPath){
		Reporter.log(ReportFormatter.printMessage(elementName + DLGGlobal.getDriver().findElement(By.xpath(xPath)).getText()));
		return DLGGlobal.getDriver().findElement(By.xpath(xPath)).getText();
	}	
	
	public static void compareValues(String value1,String value2, String value){
//		System.out.println("Value 1 is************* " + value1);
//		System.out.println("Value 2 is************* " + value2);
		if (value1.equals(value2)) {
			Reporter.log(ReportFormatter.printMessage(ReportFormatter.getPassMessage() + " - " + value +" are matching"));
		} else {
			Reporter.log(ReportFormatter.printMessage(ReportFormatter.getFailMessage() + " - " + value +" are not matching"));
			terminateScenario();
		}		
	}
	
	public static void checkLoadingImage(){
		long startTime = System.currentTimeMillis();
		while(System.currentTimeMillis()-startTime<5000){			
//			System.out.println("Difference is " + (System.currentTimeMillis()-startTime));
		}
	}
	//Function for Interacting with Input box. - This is default function with terminate flag as false.
	public static void InteractInput (String objectInfo, String inputValue, String objectName){
		InteractInput(objectInfo, inputValue, objectName, true);
	}
	
	public static void switchToIframe(String xpath) {
		WebElement element = DLGGlobal.getDriver().findElement(By.xpath(xpath));
		DLGGlobal.getDriver().switchTo().frame(element);
	}
	
	public static void switchToIframe(int frameId) {
		DLGGlobal.getDriver().switchTo().frame(frameId);
	}	
	public static String getWindowHandle() {
		return DLGGlobal.getDriver().getWindowHandle();
	}
	public static void moveToMainWindow() {		
		DLGGlobal.getDriver().switchTo().parentFrame();
	}	
	public static void moveToWindow(String winHandle) {
		DLGGlobal.getDriver().switchTo().window(winHandle);

	}	
	
	/*
	 * 		try {
			Actions actions = new Actions(driver);			
			for (int i = 1; i <=5; i++) {		
				actions.moveToElement(driver.findElement(By.xpath(OR.getProperty(objectInfo)))).click().perform();				
			}
			Reporter.log(ReportFormatter.printMessage(ReportFormatter.getPassMessage() + " - Able to click button ", objectInfo, " successfully"));	
		} catch (Exception e) {
			Reporter.log(ReportFormatter.printMessage(ReportFormatter.getFailMessage() + " - Unable to click " + objectInfo));
			System.out.println("Exception is "+ e);
			terminateScenario();
		}
	 */

	
	public static void InteractLink (String objectInfo, String objectName){		
		try {
			WebElement element = (new WebDriverWait(DLGGlobal.getDriver(), 60)).until(ExpectedConditions.elementToBeClickable(By.xpath(objectInfo)));
			DLGGlobal.getDriver().findElement(By.xpath(objectInfo)).click();			
//			DLGGlobal.getDriver().findElement(By.xpath(OR.getProperty(objectInfo))).click();			
			Reporter.log(ReportFormatter.printMessage(ReportFormatter.getPassMessage() + " - Able to click ", objectName, " successfully."));					
		} catch (Exception e) {			
			Reporter.log(ReportFormatter.printMessage(ReportFormatter.getFailMessage() + " - Unable to click ", objectName));
			terminateScenario();
		}		
	}	
	
	
	//Function for Interacting with Input box. - This is overridden function where terminate flag can be set to true.
	public static void InteractInput (String objectInfo, String inputValue, String objectName, boolean terminateFlag){		
		try {
			WebElement element = (new WebDriverWait(DLGGlobal.getDriver(), 60)).until(ExpectedConditions.presenceOfElementLocated(By.xpath(objectInfo)));
			DLGGlobal.getDriver().findElement(By.xpath(objectInfo)).sendKeys(inputValue);
//			DLGGlobal.getDriver().findElement(By.xpath(OR.getProperty(objectInfo))).sendKeys(inputValue);			
			Reporter.log(ReportFormatter.printMessage(ReportFormatter.getPassMessage() + " - Able to enter ", inputValue, " successfully in ",objectName + " Inputbox"));					
		} catch (Exception e) {			
			Reporter.log(ReportFormatter.printMessage(ReportFormatter.getFailMessage() + " - Unable to enter ", inputValue, " in ",objectName + " Inputbox"));
			captureFailureScreenShot();
			isTerminateFlagTrue(terminateFlag);
		}		
	}

	//Function for Interacting with a button.
	public static void InteractButton (String objectInfo, String objectName){		
		try {
			WebElement element = (new WebDriverWait(DLGGlobal.getDriver(), 60)).until(ExpectedConditions.elementToBeClickable(By.xpath(objectInfo)));
			DLGGlobal.getDriver().findElement(By.xpath(objectInfo)).click();
//			DLGGlobal.getDriver().findElement(By.xpath(ORs.getProperty(objectInfo))).click();			
			Reporter.log(ReportFormatter.printMessage(ReportFormatter.getPassMessage() + " - Able to click ", objectName, " button successfully"));					
		} catch (Exception e) {
			Reporter.log(ReportFormatter.printMessage(ReportFormatter.getFailMessage() + " - Unable to click ",objectName," button"));
//			System.out.println(objectInfo +" exception is "+e);
			terminateScenario();
		}
	}	
	
	public static void InteractButton(String objectInfo, String action, String but){
		try {
			Actions actions = new Actions(DLGGlobal.getDriver());			
			for (int i = 1; i <=5; i++) {		
				actions.moveToElement(DLGGlobal.getDriver().findElement(By.xpath(""))).click().perform();				
//				actions.moveToElement(DLGGlobal.getDriver().findElement(By.xpath(OR.getProperty(objectInfo)))).click().perform();				
			}
			Reporter.log(ReportFormatter.printMessage(ReportFormatter.getPassMessage() + " - Able to click button ", objectInfo, " successfully"));	
		} catch (Exception e) {
			Reporter.log(ReportFormatter.printMessage(ReportFormatter.getFailMessage() + " - Unable to click " + objectInfo));
//			System.out.println("Exception is "+ e);
			terminateScenario();
		}
	}		
	
	public static void scrollToBottom(){
		JavascriptExecutor jse = (JavascriptExecutor)DLGGlobal.getDriver();
		jse.executeScript("scroll(0, document.body.scrollHeight);");	
	}

	//Function for Interacting with List box. - This is default function with terminate flag as false.
	public static void InteractListbox (String objectInfo, int listIndex){
		InteractListbox(objectInfo, listIndex, false);
	}
		
	
	//Function for Interacting with a listbox. - This is overridden function where terminate flag can be set to true.
	public static void InteractListbox (String objectInfo, int listIndex, boolean terminateFlag){
		try {
			new Select(DLGGlobal.getDriver().findElement(By.xpath(""))).selectByIndex(listIndex);
			Reporter.log(ReportFormatter.printMessage(ReportFormatter.getPassMessage() + " - Able to select from ", objectInfo, " successfully"));			
		} catch (Exception e) {
			Reporter.log(ReportFormatter.printMessage(ReportFormatter.getFailMessage() + " - Unable to select value from listbox ",objectInfo));
			captureFailureScreenShot();
			isTerminateFlagTrue(terminateFlag);
		}		
	}
	
	//Function for Interacting with List box. - This is default function with terminate flag as false.
	public static void InteractListbox (String objectInfo, String value){
		InteractListbox(objectInfo, value, false);
	}
	
	
	//Function for Interacting with a listbox. - This is overridden function where terminate flag can be set to true.
	public static void InteractListbox (String objectInfo, String value, boolean terminateFlag){
		try {
			new Select(DLGGlobal.getDriver().findElement(By.xpath(""))).selectByVisibleText(value);
//			new Select(DLGGlobal.getDriver().findElement(By.xpath(OR.getProperty(objectInfo)))).selectByVisibleText(value);			
			Reporter.log(ReportFormatter.printMessage(ReportFormatter.getPassMessage() + " - Able to select from ", objectInfo, " successfully"));			
		} catch (Exception e) {
			Reporter.log(ReportFormatter.printMessage(ReportFormatter.getFailMessage() + " - Unable to select value from listbox ",objectInfo));
			captureFailureScreenShot();
			isTerminateFlagTrue(terminateFlag);
		}		
	}
	
	//Function for Interacting with a Radiobutton.
	public static void InteractRadioButton (String radioValue){
		try {
			DLGGlobal.getDriver().findElement(By.xpath("")).click();		
//			DLGGlobal.getDriver().findElement(By.xpath(OR.getProperty("genericPPrefix")+radioValue+OR.getProperty("genericPSuffix"))).click();			
			Reporter.log(ReportFormatter.printMessage(ReportFormatter.getPassMessage() + " - Able to click radiobutton ", radioValue, " successfully"));			
		} catch (Exception e) {
			Reporter.log(ReportFormatter.printMessage(ReportFormatter.getFailMessage() + " - Unable to click value " + radioValue + "from radio button"));
			terminateScenario();
		}		
	}	
	

	//Function for Interacting with a Span.
	public static void InteractSpan (String span){
		try {
			DLGGlobal.getDriver().findElement(By.xpath("")).click();		
//			DLGGlobal.getDriver().findElement(By.xpath(OR.getProperty("genericSpanPrefix")+span+OR.getProperty("genericSpanSuffix"))).click();			
			Reporter.log(ReportFormatter.printMessage(ReportFormatter.getPassMessage() + " - Able to click span ", span, " successfully"));					
		} catch (Exception e) {
			Reporter.log(ReportFormatter.printMessage(ReportFormatter.getFailMessage() + " - Unable to click " + span));
			terminateScenario();
		}
	}

	//Function for Interacting with a Span - Overload 1.
	public static void InteractSpan (String span, int index){
		try {
			DLGGlobal.getDriver().findElement(By.xpath("")).click();		
//			DLGGlobal.getDriver().findElement(By.xpath(OR.getProperty("genericSpanPrefix")+span+OR.getProperty("genericSpanSuffix")+"["+index+"]")).click();			
			Reporter.log(ReportFormatter.printMessage(ReportFormatter.getPassMessage() + " - Able to click span ", span, " successfully"));					
		} catch (Exception e) {
			Reporter.log(ReportFormatter.printMessage(ReportFormatter.getFailMessage() + " - Unable to click " + span));
			terminateScenario();
		}	

	}
	
	//Function for Interacting with a Span. Overload 2. 
	public static void InteractSpan (String span, String action){
		try {
			Actions actions = new Actions(DLGGlobal.getDriver());			
			for (int i = 1; i <=5; i++) {		
				actions.moveToElement(DLGGlobal.getDriver().findElement(By.xpath(""))).click().perform();				
//				actions.moveToElement(DLGGlobal.getDriver().findElement(By.xpath(OR.getProperty("genericSpanPrefix")+span+OR.getProperty("genericSpanSuffix")))).click().perform();				
			}
			Reporter.log(ReportFormatter.printMessage(ReportFormatter.getPassMessage() + " - Able to click span ", span, " successfully"));	
		} catch (Exception e) {
			Reporter.log(ReportFormatter.printMessage(ReportFormatter.getFailMessage() + " - Unable to click " + span));
			terminateScenario();
		}
	}	
		
	//Function for Interacting for Autocomplete
	public static void InteractAutoComplete (String index){
		try {
			DLGGlobal.getDriver().findElement(By.xpath("")).click();		
//			DLGGlobal.getDriver().findElement(By.xpath(OR.getProperty("professionDivPrefix")+index+OR.getProperty("professionDivSuffix"))).click();			
			Reporter.log(ReportFormatter.printMessage(ReportFormatter.getPassMessage() + " - Able to select index ", index, " successfully"));			
		} catch (Exception e) {
			Reporter.log(ReportFormatter.printMessage(ReportFormatter.getFailMessage() + " - Unable to autocomplete"));
			terminateScenario();
		}		
	}
	
	//Function for checking browser title.
	public static void checkBrowserTitle(String title){
		try {
			if (DLGGlobal.getDriver().getTitle().equals(title)) {
				Reporter.log(ReportFormatter.printMessage(ReportFormatter.getPassMessage() + " - Expected and Actual title are matching.") + ReportFormatter.getNextLineCharacter());
				Reporter.log(ReportFormatter.getSpaceCharacter() + "Expected title - " + ReportFormatter.getValuePrefix() + title + ReportFormatter.getValueSuffix() + ReportFormatter.getNextLineCharacter() + ReportFormatter.getSpaceCharacter() +  "Actual title - "+ ReportFormatter.getValuePrefix() + DLGGlobal.getDriver().getTitle() + ReportFormatter.getValueSuffix());			
			}else{
				Reporter.log(ReportFormatter.printMessage(ReportFormatter.getFailMessage() + " - Expected and Actual title are not matching.") + ReportFormatter.getNextLineCharacter());
				Reporter.log(ReportFormatter.getSpaceCharacter() + "Expected title - " + ReportFormatter.getValuePrefix() + title + ReportFormatter.getValueSuffix() + ReportFormatter.getNextLineCharacter() + ReportFormatter.getSpaceCharacter() +  "Actual title - "+ ReportFormatter.getValuePrefix() + DLGGlobal.getDriver().getTitle() + ReportFormatter.getValueSuffix());
				captureFailureScreenShot();
			}
		} catch (Exception e) {
			Reporter.log(ReportFormatter.printMessage(ReportFormatter.getFailMessage() + " - Unable to perform checking for browser title."));
			terminateScenario();
		}
	}
	
	//Function for checking footer message on a page. Expected format of the footer is  (//div/div[@class='footer']/p[contains(.,'TextOfTheFooter')])
	public static void doesFooterExists(String text){		
		try {
			Assert.assertTrue(!DLGGlobal.getDriver().findElements(By.xpath("")).isEmpty());
//			Assert.assertTrue(!DLGGlobal.getDriver().findElements(By.xpath(OR.getProperty("genericParaPrefix")+text+OR.getProperty("genericParaSuffix"))).isEmpty());			
			Reporter.log(ReportFormatter.printMessage(ReportFormatter.getPassMessage() + " - Element ",text," exists on the webpage."));			
		} catch (Exception e) {
			Reporter.log(ReportFormatter.printMessage(ReportFormatter.getFailMessage() + " - Unable to perform checking for footer text."));
			captureFailureScreenShot();			
			terminateScenario();
		}
	}
	
	public static void printFunctionName(String functionName){
		Reporter.log(ReportFormatter.printFunctionName(functionName));		
	}
	
	public static void captureFailureScreenShot(){
		if (DLGGlobal.getDriver() != null) {
			new CustomListeners().onTestFailure(null);
		}		
//		new CustomListeners().onTestFailure(null);
	}
	
	public static void quitDriver(){
		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		DLGGlobal.getDriver().close();
		DLGGlobal.driver = null;	
		ReportFormatter.stepNumber = 1;
	}
	
	public static void isTerminateFlagTrue(boolean terminateFlag){
		if (terminateFlag == true) {
			quitDriver();
		}		
	}
	
	public static void setDriverToNull(){		
		DLGGlobal.driver = null;
	}
	
	public static void terminateScenario(){
		captureFailureScreenShot();
		quitDriver();
	}
	

	public static void createFileFlag(String flag){
		
//		System.out.println("*************INSIDE CREATE FLAG FILE FUNCTION*************");		
		String environment = null;
		if (flag.toString().trim().equals("cucumber.ss.dlg")) {
			environment = "1";			
		}
		if (flag.toString().trim().equals("cucumber.cc.dlg")) {
			environment = "2";			
		}		
//		System.out.println("DLGGlobal.getJenkinsWorkSpaceName() is ***********"+ DLGGlobal.getJenkinsWorkSpaceName());
//		File myFile = new File("\\\\vm-w7-ij-t10\\Users\\insurej.tester\\Jenkins Slave\\workspace\\"+DLGGlobal.getJenkinsWorkSpaceName()+"\\Flag.txt");
		File myFile = new File("C:\\Users\\insurej.tester\\Jenkins Slave\\workspace\\Flag.txt");		
		try {
			if (myFile.createNewFile()) {
//				  System.out.println("File is created!");
			} else {
//				 System.out.println("File already exists.");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(myFile));
//			System.out.println("******************Value of environment is ******************"+ environment);
			writer.write(environment);
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void deleteExistingFiles(String copyResultsFileTo){
		try {
			org.apache.commons.io.FileUtils.cleanDirectory(new File(copyResultsFileTo));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	public static void moveScreenShot(String copyFrom, String copyTo){
//		System.out.println("CopyFrom is "+ copyFrom);
//		System.out.println("CopyTo is "+ copyTo);		
		try {
			File myFile = new File(copyFrom);
			if(myFile.renameTo(new File(copyTo + myFile.getName()))) {
//				System.out.println("File is moved successful!");
			} else {
//				System.out.println("File is failed to move!");
			}			
			} catch (Exception e) {
				e.printStackTrace();
			}		
	}
	
	public static void moveResultsFileToJenkinsWorkSpace(String copyResultsFileFrom, String copyResultsFileTo){
//		System.out.println("CopyFrom is "+ copyResultsFileFrom);
//		System.out.println("CopyTo is "+ copyResultsFileTo);
		deleteExistingFiles(copyResultsFileTo);
		try {
			File myFile = new File(copyResultsFileFrom);
			if(myFile.renameTo(new File(copyResultsFileTo + myFile.getName()))) {
//				System.out.println("File is moved successful!");
			} else {
//				System.out.println("File is failed to move!");
			}			
			} catch (Exception e) {
				e.printStackTrace();
			}		
	}	
	
	
	public static void copyResultFile(String flag){
//		System.out.println("***********************Inside CopyResultFile***************");
		if (flag.trim().equals("1")) {
//			System.out.println("********************Inside flag 1***************equals");
			moveResultsFileToJenkinsWorkSpace("C:\\DLG HealthCheck\\cucumber.ss.dlg\\target\\surefire-reports\\html\\suite1_test1_results.html", "\\\\vm-w7-ij-t10\\Users\\insurej.tester\\Jenkins Slave\\workspace\\DLG-UAT-Master\\");	
		}				
		if (flag.trim().equals("2")) {
//			System.out.println("********************Inside flag 2***************equals");
			moveResultsFileToJenkinsWorkSpace("C:\\DLG HealthCheck\\cucumber.cc.dlg\\target\\surefire-reports\\html\\suite1_test1_results.html", "\\\\vm-w7-ij-t10\\Users\\insurej.tester\\Jenkins Slave\\workspace\\DLG-UAT-Master\\");	                                          
		}		
	}	
	
	public static String getHostName(){		
		    InetAddress addr;
		    String hostName = "UnKnown";
		    try {
				addr = InetAddress.getLocalHost();
				hostName = addr.getHostName();
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    return hostName;		
	}
	
	public static void InteractTable (String objectInfo, String objectName){		
		try {
			DLGGlobal.getDriver().findElement(By.xpath(objectInfo)).click();			
			Reporter.log(ReportFormatter.printMessage(ReportFormatter.getPassMessage() + " - Able to click ", objectName, " successfully."));					
		} catch (Exception e) {			
			Reporter.log(ReportFormatter.printMessage(ReportFormatter.getFailMessage() + " - Unable to click ", objectName));
			terminateScenario();
		}		
	}		
	
}