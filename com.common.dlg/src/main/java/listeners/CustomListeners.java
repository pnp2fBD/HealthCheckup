package listeners;


import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import UXP.Common;
import UXP.DLGGlobal;
import UXP.ReportFormatter;
import utility.TestUtil;


public class CustomListeners implements ITestListener {
//	public static WebDriver driver;

	public void onFinish(ITestContext arg0) {		
//		System.out.println("---- Regression suite has finished ---- ");
/*		System.out.println("*****GET WORKING FOLDER IS "+ DLGGlobal.getWorkingFolder());		
		if (DLGGlobal.getWorkingFolder().equals("cucumber.ss.dlg")) {
			System.out.println("********DLGGlobal.getWorkingFolder()==cucumber.ss.dlg*****");
			Common.createFileFlag("cucumber.ss.dlg");
		}
		if (DLGGlobal.getWorkingFolder().equals("cucumber.cc.dlg")) {
			System.out.println("********DLGGlobal.getWorkingFolder()==cucumber.cc.dlg*****");			
			Common.createFileFlag("cucumber.cc.dlg");			
		}*/		
//		System.out.println("DLGGlobal.getResultsFileFrom()******" + DLGGlobal.getResultsFileFrom());
//		System.out.println("DLGGlobal.getResultsFileTo()*******" + DLGGlobal.getResultsFileTo());
		//System.out.println("DLGGlobal.getResultsFileName()*****" + DLGGlobal.getResultsFileName());		*/
		//Common.moveResultsFileToJenkinsWorkSpace(DLGGlobal.getResultsFileFrom(), DLGGlobal.getResultsFileTo(),DLGGlobal.getResultsFileName());
		// TODO Auto-generated method stub
	/*	monitoringMail mail = new monitoringMail();
		try {
			mail.sendMail(TestConfig.server, TestConfig.from, TestConfig.to, TestConfig.subject, TestConfig.messageBody, TestConfig.attachmentPath, TestConfig.attachmentName);
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
	}

	public void onStart(ITestContext arg0) {
		// TODO Auto-generated method stub
		
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
		// TODO Auto-generated method stub
		
	}

	public void onTestFailure(ITestResult arg0) {		
		System.setProperty("org.uncommons.reportng.escape-output", "false");		
		try {
			TestUtil.captureScreenShot();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		

		// Imp - workingFolder & jenkisWorkSpaceName to be updated for each project.
		String workingFolder = DLGGlobal.getWorkingFolder();// "cucumber.cc.dlg";
		String jenkinsWorkSpaceName = DLGGlobal.getJenkinsWorkSpaceName();//   "DLG-Cucumber-CC";
		//copyFrom is the path of default screenshot repository as per TestUtil.java		
//		String copyFrom = "C:\\Users\\insurej.tester\\Selenium Automation\\"+workingFolder+"\\target\\surefire-reports\\html\\"+TestUtil.screenshotName;
		String copyFrom = "C:\\DLG HealthCheck\\"+workingFolder+"\\target\\surefire-reports\\html\\"+TestUtil.screenshotName;		
		//copyTo is the path of Jenkins workspace
		String machineName = Common.getHostName();
		String copyTo = "";
		//vm-w7-ij-t10 is the master machine. Rest machines are slaves machines.

		/*
		if (machineName.toUpperCase().equals("VM-W7-IJ-T10")) {
			copyTo = "\\\\vm-w7-ij-t10\\Users\\insurej.tester\\.jenkins\\workspace\\"+jenkinsWorkSpaceName+"\\";			
		}else {
//			String copyTo = "\\\\vm-w7-ij-t10\\Users\\insurej.tester\\.jenkins\\workspace\\"+jenkinsWorkSpaceName+"\\";
			copyTo = "\\\\"+machineName+"\\Users\\insurej.tester\\SetUp\\JenkinsSlave\\workspace\\"+jenkinsWorkSpaceName+"\\";
		}
		*/
		copyTo = "\\\\vm-w7-ij-t10\\Users\\insurej.tester\\Jenkins Slave\\workspace\\"+jenkinsWorkSpaceName+"\\";
//		System.out.println("***COPY TO IS **** " + copyTo);
		String JenkinsScreenShotPath = "\\job\\"+ jenkinsWorkSpaceName +"\\ws\\"+TestUtil.screenshotName;;		
		Common.moveScreenShot(copyFrom, copyTo);		
		StringBuilder builder = new StringBuilder();
		builder.append("<a target='_blank' href=\"")		       
		       .append(JenkinsScreenShotPath)
		       .append("\"")
		       .append("\\>"+ReportFormatter.getFailureScreenShotMessage()+"</a>");		
		
		//Below is the code to open file locally currently commented out.
/*		StringBuilder builder = new StringBuilder();
		builder.append("<a target='_blank' href=\"")
		       .append(System.getProperty("user.dir"))
			   .append("\\target\\surefire-reports\\html\\")
		       .append(TestUtil.screenshotName)
		       .append("\"")
		       .append("\\>"+ReportFormatter.getFailureScreenShotMessage()+"</a>");*/		
//		System.out.println("For testing : " + builder.toString());
		Reporter.log(builder.toString());
//		System.out.println("Capture Screenshot");
	
	}

	public void onTestSkipped(ITestResult arg0) {
		// TODO Auto-generated method stub
		
	}

	public void onTestStart(ITestResult arg0) {
		// TODO Auto-generated method stub
		
	}

	public void onTestSuccess(ITestResult arg0) {

//		System.out.println("Test successfully executed");
		
	}
}