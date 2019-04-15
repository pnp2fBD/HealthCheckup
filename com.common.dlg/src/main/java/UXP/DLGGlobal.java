package UXP;

import org.openqa.selenium.WebDriver;

public class DLGGlobal {

	public static String workingFolder;	
	public static String getWorkingFolder() {
		return workingFolder;
	}
	public static void setWorkingFolder(String workingFolder) {
		DLGGlobal.workingFolder = workingFolder;
	}

	public static String jenkinsWorkSpaceName;
	public static String getJenkinsWorkSpaceName() {
		return jenkinsWorkSpaceName;
	}
	public static void setJenkinsWorkSpaceName(String jenkinsWorkSpaceName) {
		DLGGlobal.jenkinsWorkSpaceName = jenkinsWorkSpaceName;
	}
	
	public static WebDriver driver;
	public static WebDriver getDriver() {
		return driver;
	}
	public static void setDriver(WebDriver driver) {
		DLGGlobal.driver = driver;
	}

	public static int stepNumber;
	public static int getStepNumber() {
		return stepNumber;
	}
	public static void setStepNumber(int stepNumber) {
		DLGGlobal.stepNumber = stepNumber;
	}

	public static String policyNumber; 
	public static String getPolicyNumber() {
		return policyNumber;
	}
	public static void setPolicyNumber(String policyNumber) {
		DLGGlobal.policyNumber = policyNumber;
	}
	
	public static String ContactCentreLoginUser;
	public static String getContactCentreLoginUser() {
		return ContactCentreLoginUser;
	}
	public static void setContactCentreLoginUser(String contactCentreLoginUser) {
		ContactCentreLoginUser = contactCentreLoginUser;
	}
	
	public static String GridPolicyNumber;
	public static String getGridPolicyNumber() {
		return GridPolicyNumber;
	}
	public static void setGridPolicyNumber(String gridPolicyNumber) {
		GridPolicyNumber = gridPolicyNumber;
	}
	public static String HeaderPolicyNumber;	
	public static String getHeaderPolicyNumber() {
		return HeaderPolicyNumber;
	}
	public static void setHeaderPolicyNumber(String headerPolicyNumber) {
		HeaderPolicyNumber = headerPolicyNumber;
	}

	public static String ResultsFileFrom;
	public static String getResultsFileFrom() {
		return ResultsFileFrom;
	}
	public static void setResultsFileFrom(String resultsFileFrom) {
		ResultsFileFrom = resultsFileFrom;
	}

	public static String ResultsFileTo;
	public static String getResultsFileTo() {
		return ResultsFileTo;
	}
	public static void setResultsFileTo(String resultsFileTo) {
		ResultsFileTo = resultsFileTo;
	}
	
	public static String ResultsFileName;
	public static String getResultsFileName() {
		return ResultsFileName;
	}
	public static void setResultsFileName(String resultsFileName) {
		ResultsFileName = resultsFileName;
	}

	public static String flag;
	
	public static String getFlag() {
		return flag;
	}
	public static void setFlag(String flag) {
		DLGGlobal.flag = flag;
	}
	
}
