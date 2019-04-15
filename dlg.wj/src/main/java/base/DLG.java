package base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import screens.BusinessDetails;
import screens.ConfirmationOfPayment;
import screens.Contacts;
import screens.GeneralInformation;
import screens.ImportantStatements;
import screens.InterestedParties;
import screens.Liability;
import screens.Payment;
import screens.PaymentInformation;
import screens.People;
import screens.PreviousLosses;
import screens.QuoteDecline;
import screens.QuoteSummary;
import screens.SecurePaymentPage;
import screens.SecurityCheck;
import screens.WorldPay;
import screens.YourBusiness;
import screens.YourDirectLineforBusinessAccount;
import utility.ExcelReader;

public class DLG {

	private static final Logger log = Logger.getLogger(DLG.class.getName());
	// Business related object variables
	public static WebDriver driver;
	public static Properties OR = new Properties();
	public static Properties Config = new Properties();
	public static Properties HTMLReporter = new Properties();
	public static FileInputStream fis;
	public static ExcelReader excel = new ExcelReader(
			System.getProperty("user.dir") + "\\src\\main\\resources\\testdata\\DLGTestData.xlsx");

	public DLG() {
		log.debug("Entered and Exit from DLG constructor");
	}

	public DLG(String environment) {
		PropertyConfigurator
				.configure(System.getProperty("user.dir") + "\\src\\main\\resources\\properties\\log4j.properties");
		log.debug("Entering in DLG parameterized constructor of DLG class");
		if (driver == null) {
			try {
				fis = new FileInputStream(
						System.getProperty("user.dir") + "\\src\\main\\resources\\properties\\Config.properties");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			try {
				Config.load(fis);
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				fis = new FileInputStream(
						System.getProperty("user.dir") + "\\src\\main\\resources\\properties\\OR.properties");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			try {
				OR.load(fis);
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				fis = new FileInputStream(
						System.getProperty("user.dir") + "\\src\\main\\resources\\properties\\HTMLReporter.properties");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			try {
				HTMLReporter.load(fis);
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.setProperty("org.uncommons.reportng.escape-output", "false");
			driver = new FirefoxDriver();
			driver.manage().deleteAllCookies();
			driver.manage().window().maximize();
			String testURL = null;
			if (environment.equalsIgnoreCase("uat"))
				testURL = Config.getProperty("testsiteurl-UAT");
			else if (environment.equalsIgnoreCase("uat2"))
				testURL = Config.getProperty("testsiteurl-UAT2");
			else if (environment.equalsIgnoreCase("cd2"))
				testURL = Config.getProperty("testsiteurl-CD2");
			else if (environment.equalsIgnoreCase("CT2"))
				testURL = Config.getProperty("testsiteurl-CT2");
			else if (environment.equalsIgnoreCase("TRG"))
				testURL = Config.getProperty("testsiteurl-Training");
			else if (environment.equalsIgnoreCase("CD"))
				testURL = Config.getProperty("testsiteurl-CD");
			else if (environment.equalsIgnoreCase("CT"))
				testURL = Config.getProperty("testsiteurl-CT");
			else
				log.error(
						"No environment value pass while running this test and the environment values should be : UAT, UAT2, CD, CT, CD2, CT2, TRG ");
			driver.get(testURL);
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			log.info("Website launched successfully. Environment is " + environment + ". URL is " + testURL);
		}
		log.debug("Exit from DLG parameterized constructor of DLG class");
	}
}
