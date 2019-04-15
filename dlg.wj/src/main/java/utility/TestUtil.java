package utility;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;

import base.DLG;

public class TestUtil extends DLG {

	public static String mailscreenshotpath;
	public static String screenshotName;

	// get Data by sheetName
	public static Object[][] getData(String sheetName) {
		int rows = excel.getRowCount(sheetName);
		int cols = excel.getColumnCount(sheetName);
		Object[][] data = new Object[rows - 1][cols];
		for (int rowNum = 2; rowNum <= rows; rowNum++) { // 2
			for (int colNum = 0; colNum < cols; colNum++) {
				// data[0][0]
				data[rowNum - 2][colNum] = excel.getCellData(sheetName, colNum, rowNum); // -2
			}
		}
		return data;
	}

	public static void captureScreenShot() throws IOException {
		Calendar cal = new GregorianCalendar();
		int month = cal.get(Calendar.MONTH); // 4
		int year = cal.get(Calendar.YEAR); // 2013
		int sec = cal.get(Calendar.SECOND);
		int min = cal.get(Calendar.MINUTE);
		int date = cal.get(Calendar.DATE);
		int day = cal.get(Calendar.HOUR_OF_DAY);
		String env = System.getProperty("environment");
		screenshotName = env+"_"+year + "_" + date + "_" + (month + 1) + "_" + day + "_" + min + "_" + sec + ".jpeg";
		mailscreenshotpath = System.getProperty("user.dir") + "\\Screenshots\\" + year + "_" + date
				+ "_" + (month + 1) + "_" + day + "_" + min + "_" + sec + "_"  + ".jpeg";
		if (driver != null) {
			File scr = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(scr, new File(mailscreenshotpath));
		}
	}
	
	public static String getEmail() {
		Calendar cal = new GregorianCalendar();
		int month = cal.get(Calendar.MONTH); // 4
		month = month + 1;
		int year = cal.get(Calendar.YEAR); // 2013
		int sec = cal.get(Calendar.SECOND);
		int min = cal.get(Calendar.MINUTE);
		int date = cal.get(Calendar.DATE);
		int day = cal.get(Calendar.HOUR_OF_DAY);
		String env = System.getProperty("environment");
		StringBuffer strbuf = new StringBuffer();
		strbuf.append("HealthCheck").append(env).append(year).append(date).append(month).append(day).append(min)
				.append(sec);
		String emailId = strbuf.toString();
		System.out.println(emailId);
		return emailId;

	}

	public static void javaScriptExecutorUtil(String action, String elementName){
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		String locator = OR.getProperty(elementName);
		WebElement element = driver.findElement(By.xpath(locator));  
		executor.executeScript(action, element);
	}
}
