package UXP;

import java.text.SimpleDateFormat;
import java.util.Date;
public class ReportFormatter {
	
	public static String pass = "<font color = green><b>Pass</b></font>";	
	public static String fail = "<font color = red><b>Fail</b></font>";
	public static String failureScreenShot = "<font color = red><b><u>Failure Screenshot</u></b></font>";
	public static String nextLineCharacter = "<br>";
	public static String reporterHeaderPrefix = "<font color = black><b>";
	public static String reporterHeaderSuffix = "</b></font>";
	public static String valuePrefix = "<font color = blue><b><i>";
	public static String valueSuffix = "</i></b></font>";
	public static String LogHeaderPrefix = "<font color = grey><b><u>";
	public static String LogHeaderSuffix = "</u></b></font>";
	public static String dateFormat = "dd.MM.yyyy.HH.mm.ss";
	public static String separatorCharacter = "&#8201&#8259&#8201;";
	public static String spaceCharacter = "&#8201&#8201&#8201&#8201&#8201&#8201;";
	
	
	public static int stepNumber = DLGGlobal.getStepNumber();
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
		return pass; 
	}

	public static String getFailMessage(){
		return fail;
	}
	
	public static String getFailureScreenShotMessage(){
		return failureScreenShot;
	}
	
	public static String getNextLineCharacter(){
		return nextLineCharacter;
	}	
	
	public static String getValuePrefix(){
		return valuePrefix;
	}

	public static String getValueSuffix(){
		return valueSuffix;
	}
	
	public static String getSeparatorCharacter(){
		return separatorCharacter;
	}
	public static String getSpaceCharacter(){
		return spaceCharacter;
	}	
	public static String printMessage(String message){
		return getNextLineCharacter() + getSpaceCharacter() + String.valueOf(getSubStepNumber()) + getSeparatorCharacter() + reporterHeaderPrefix + message + reporterHeaderSuffix;
	}
	
	public static String printMessage(String message, String value){ 
		return getNextLineCharacter() + getSpaceCharacter() + String.valueOf(getSubStepNumber()) + getSeparatorCharacter() + reporterHeaderPrefix + message + reporterHeaderSuffix + valuePrefix + value + valueSuffix;
	}	
	
	public static String printMessage(String message1, String value, String message2){ 
		return getNextLineCharacter() + getSpaceCharacter() + String.valueOf(getSubStepNumber()) + getSeparatorCharacter() + reporterHeaderPrefix + message1 + reporterHeaderSuffix + valuePrefix + value + valueSuffix + reporterHeaderPrefix+ message2 + reporterHeaderSuffix;
	}
	
	public static String printMessage(String message1, String value1, String message2, String value2){ 
		return getNextLineCharacter() + getSpaceCharacter() + String.valueOf(getSubStepNumber()) + getSeparatorCharacter() + reporterHeaderPrefix + message1 + reporterHeaderSuffix + valuePrefix + value1 + valueSuffix + reporterHeaderPrefix+ message2 + reporterHeaderSuffix + valuePrefix + value2 + valueSuffix;
	}
	
	public static String printFunctionName(String functionName){
		setSubStepNumber(String.valueOf(stepNumber));
		count = 1;
		return getNextLineCharacter() + LogHeaderPrefix + (stepNumber++)+ getSeparatorCharacter() + functionName + getSeparatorCharacter() +new SimpleDateFormat(dateFormat).format(new Date()) + LogHeaderSuffix;		
	}
}