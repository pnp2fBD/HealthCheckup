package common;

import java.text.SimpleDateFormat;
import java.util.Date;


import base.DLG;

public class ReportFormatter extends DLG {
	
	public static int stepNumber = 1;	
	public static String subStepNumber;
	public static int count;
	
	public static String getSubStepNumber() {
		subStepNumber = String.valueOf(stepNumber - 1) + "." + String.valueOf(count);
		count++;
		return subStepNumber;
	}

	public static void setSubStepNumber(String subStepNumber) {		
		ReportFormatter.subStepNumber = subStepNumber;
	}	
	
	public static String getPassMessage(){
		return HTMLReporter.getProperty("pass"); 
	}

	public static String getFailMessage(){
		return HTMLReporter.getProperty("fail"); 
	}
	
	public static String getFailureScreenShotMessage(){
		return HTMLReporter.getProperty("failureScreenShot"); 
	}
	
	public static String getNextLineCharacter(){
		return HTMLReporter.getProperty("nextLineCharacter"); 
	}	
	
	public static String getValuePrefix(){
		return HTMLReporter.getProperty("valuePrefix");
	}

	public static String getValueSuffix(){
		return HTMLReporter.getProperty("valueSuffix"); 
	}
	
	public static String getSeparatorCharacter(){
		return HTMLReporter.getProperty("separatorCharacter"); 
	}
	public static String getSpaceCharacter(){
		return HTMLReporter.getProperty("spaceCharacter"); 
	}	
	public static String printMessage(String message){
		return getNextLineCharacter() + getSpaceCharacter() + String.valueOf(getSubStepNumber()) + getSeparatorCharacter() + HTMLReporter.getProperty("reporterHeaderPrefix") + message + HTMLReporter.getProperty("reporterHeaderSuffix");
	}
	
	public static String printMessage(String message, String value){ 
		return getNextLineCharacter() + getSpaceCharacter() + String.valueOf(getSubStepNumber()) + getSeparatorCharacter() + HTMLReporter.getProperty("reporterHeaderPrefix") + message + HTMLReporter.getProperty("reporterHeaderSuffix") + HTMLReporter.getProperty("valuePrefix") + value + HTMLReporter.getProperty("valueSuffix");
	}	
	
	public static String printMessage(String message1, String value, String message2){ 
		return getNextLineCharacter() + getSpaceCharacter() + String.valueOf(getSubStepNumber()) + getSeparatorCharacter() + HTMLReporter.getProperty("reporterHeaderPrefix") + message1 + HTMLReporter.getProperty("reporterHeaderSuffix") + HTMLReporter.getProperty("valuePrefix") + value + HTMLReporter.getProperty("valueSuffix") + HTMLReporter.getProperty("reporterHeaderPrefix")+ message2 + HTMLReporter.getProperty("reporterHeaderSuffix");
	}
	
	public static String printMessage(String message1, String value1, String message2, String value2){ 
		return getNextLineCharacter() + getSpaceCharacter() + String.valueOf(getSubStepNumber()) + getSeparatorCharacter() + HTMLReporter.getProperty("reporterHeaderPrefix") + message1 + HTMLReporter.getProperty("reporterHeaderSuffix") + HTMLReporter.getProperty("valuePrefix") + value1 + HTMLReporter.getProperty("valueSuffix") + HTMLReporter.getProperty("reporterHeaderPrefix")+ message2 + HTMLReporter.getProperty("reporterHeaderSuffix") + HTMLReporter.getProperty("valuePrefix") + value2 + HTMLReporter.getProperty("valueSuffix");
	}
	
	public static String printFunctionName(String functionName){
		setSubStepNumber(String.valueOf(stepNumber));
		count = 1;
		return getNextLineCharacter() + HTMLReporter.getProperty("LogHeaderPrefix") + (stepNumber++)+ getSeparatorCharacter() + functionName + getSeparatorCharacter() +new SimpleDateFormat(HTMLReporter.getProperty("dateFormat")).format(new Date()) + HTMLReporter.getProperty("LogHeaderSuffix");		
	}
}