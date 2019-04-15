package utility;


import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import UXP.DLGGlobal;


public class TestUtil {
	
	public static String mailscreenshotpath;
	public static String screenshotName;
		
	public static void captureScreenShot() throws IOException{
		 Calendar cal = new GregorianCalendar();
		  int month = cal.get(Calendar.MONTH); //4
		  int year = cal.get(Calendar.YEAR); //2013
		  int sec =cal.get(Calendar.SECOND);
		  int min =cal.get(Calendar.MINUTE);
		  int date = cal.get(Calendar.DATE);
		  int day =cal.get(Calendar.HOUR_OF_DAY);
		  screenshotName=year+"_"+date+"_"+(month+1)+"_"+day+"_"+min+"_" +sec+".jpeg"; 
		  mailscreenshotpath = System.getProperty("user.dir")+"\\target\\surefire-reports\\html\\"+year+"_"+date+"_"+(month+1)+"_"+day+"_"+min+"_" +sec+".jpeg";
		  if (DLGGlobal.getDriver()!=null) {
			  File scr = ((TakesScreenshot) DLGGlobal.getDriver()).getScreenshotAs(OutputType.FILE);
			  FileUtils.copyFile(scr, new File(mailscreenshotpath));
		  }				  
		  
		  //File scr = ((TakesScreenshot) DLGGlobal.getDriver()).getScreenshotAs(OutputType.FILE);
		  //FileUtils.copyFile(scr, new File(mailscreenshotpath));
	}	
}
