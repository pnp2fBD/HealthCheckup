package testcases;

import base.DLGHealthCheckException;
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
import screens.QuoteSummary;
import screens.SecurePaymentPage;
import screens.SecurityCheck;
import screens.SucessfulAccountCreation;
import screens.WorldPay;
import screens.YourBusiness;
import screens.YourDetails;
import screens.YourDirectLineforBusinessAccount;
import utility.EnvironmentStatusUtils;

import java.io.FileInputStream;

import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriverException;
import org.testng.annotations.Test;

public class WorkflowWJ {

	private String securityKey, trade, index, premises, employee, coverage, businessName, title, firstName, lastName, typeOfBusinessIndex, yearsInBusiness, postCode, businessGeographyIndex, addressListIndex, employeesListIndex,
			publicLiabilityIndex, productsIndex, consent, anyPreviousLoss, anyPreviousLossIndex, telephoneNumber,
			marketingPreference;

	private String errorMessage = "NA";
	private int testcase = 1;
	private EnvironmentStatusUtils status;
	private String environment = System.getProperty("environment");

	private Properties prop = new Properties();
	public static FileInputStream fis;
	private SucessfulAccountCreation sucessfulAccountCreation;
	private String email;
	private SecurityCheck securityCheck;
	private BusinessDetails businessDetails;
	private GeneralInformation generalInformation;
	private People people;
	private YourBusiness yourBusiness;
	private Liability liability;
	private ImportantStatements importantStatements;
	private PreviousLosses previousLosses;
	private QuoteSummary quoteSummary;
	private YourDetails yourdetails;
	private InterestedParties interestedParties;
	private Contacts contacts;
	private Payment payment;
	private PaymentInformation paymentInformation;
	private SecurePaymentPage securePaymentPage;
	private WorldPay worldPay;
	private ConfirmationOfPayment confirmationOfPayment;
	private YourDirectLineforBusinessAccount details;
	private String titleHomePage = "Business Insurance - Direct Line for Business";
	private String footerText = "Direct Line insurance policies are underwritten by U K Insurance Limited, "
			+ "Registered office: The Wharf, Neville Street, Leeds LS1 4AZ. Registered in England and Wales No 1179980. U K Insurance Limited is authorised by the Prudential Regulation Authority and regulated by the Financial Conduct Authority and the Prudential Regulation Authority, registration number 202810. Calls may be recorded.";

	public WorkflowWJ() throws DLGHealthCheckException, IOException {

		fis = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\main\\resources\\properties\\testdata.properties");

		prop.load(fis);

		securityKey = prop.getProperty("SecurityKey");
		trade = prop.getProperty("Trade");
		index = prop.getProperty("Index");
		premises = prop.getProperty("Premises");
		employee = prop.getProperty("Employee");
		coverage = prop.getProperty("Coverage");
		prop.getProperty("AnyOtherBusiness");
		prop.getProperty("AnyOtherBusinessIndex");
		businessName = prop.getProperty("BusinessName");
		title = prop.getProperty("Title");
		firstName = prop.getProperty("FirstName");
		lastName = prop.getProperty("LastName");
		typeOfBusinessIndex = prop.getProperty("TypeOfBusiness");
		prop.getProperty("HaveSubsidiary");
		prop.getProperty("HaveSubsidiaryIndex");
		yearsInBusiness = prop.getProperty("YearsInBusiness");
		postCode = prop.getProperty("PostCode");
		businessGeographyIndex = prop.getProperty("BusinessGeography");
		addressListIndex = prop.getProperty("AddressListIndex");
		employeesListIndex = prop.getProperty("EmployeesListIndex");
		publicLiabilityIndex = prop.getProperty("PublicLiabilityIndex");
		productsIndex = prop.getProperty("ProductsIndex");
		consent = prop.getProperty("Consent");
		anyPreviousLoss = prop.getProperty("AnyPreviousLoss");
		anyPreviousLossIndex = prop.getProperty("AnyPreviousLossIndex");
		telephoneNumber = prop.getProperty("TelephoneNumber");
		marketingPreference = prop.getProperty("MarketingPreference");
	}

	@Test
	public void createAnnualPolicyWJ() throws IOException, DLGHealthCheckException {

		try {

			// Enter Security key
			securityCheck = new SecurityCheck(environment);
			status = new EnvironmentStatusUtils();

			securityCheck.checkBrowserTitle(titleHomePage);
			securityCheck.checkFooterText(footerText);
			securityCheck.enterSecurityKey(securityKey);

			// Navigated to Business details page
			businessDetails = securityCheck.clickOnProceedButton();

			// Input data for step 1 in excel output
			status.writeExcel(errorMessage, environment, testcase);

			// Step 2
			testcase++;

			// Fill business details screen 2
			businessDetails.whatDoYouDo(trade);
			businessDetails.clickOnProfession(index);
			businessDetails.whereDoYouRunYourBusinessFrom(premises);
			businessDetails.clickOnNextButton();
			businessDetails.doYouHaveEmployees(employee);
			businessDetails.getPolicyCoverage(coverage);

			// Navigated to General Information page
			generalInformation = businessDetails.clickOnContinueButton();

			// Input data for step 2 in excel output
			status.writeExcel(errorMessage, environment, testcase);

			// Step 3
			testcase++;

			generalInformation.enterGeneralInformation(businessName, firstName, lastName);
			generalInformation.contactEmailAddress(telephoneNumber, marketingPreference, postCode);
			generalInformation.clickOnFindAddress();
			generalInformation.pickAddress(addressListIndex);
			yourBusiness = generalInformation.clickOnNextButton();

			yourBusiness.yourBusinessDetails(typeOfBusinessIndex, yearsInBusiness, businessGeographyIndex);
			people = yourBusiness.clickOnNextButton();

			// Navigated to People details page
			people.pickTypeOfEmployees(employeesListIndex);

			// Navigated to Liability details page
			liability = people.clickOnNextButton();
			liability.pickLiability(publicLiabilityIndex, productsIndex);

			// Navigated to Important Statements details page
			importantStatements = liability.clickOnNextButton();
			importantStatements.consent(consent);

			// Navigated to Previous Losses details page
			previousLosses = importantStatements.clickOnNextButton();
			previousLosses.selectPreviousLosses(anyPreviousLoss, anyPreviousLossIndex);

			// Input data for step 3 in excel output
			status.writeExcel(errorMessage, environment, testcase);

			// Step 4
			testcase++;

			// Navigated to Quote Summary details page
			quoteSummary = previousLosses.clickOnGetQuoteButton();

			// Input data for step 4 in excel output
			status.writeExcel(errorMessage, environment, testcase);

			// Step 5
			testcase++;

			details = quoteSummary.clickOnReviewConfirm();
			email = GeneralInformation.getEmail();
			sucessfulAccountCreation = details.yourDetail(title, email);

			yourdetails = sucessfulAccountCreation.clickOk();

			/*
			 * /yourdetails.clickOnCreateAccount();
			 * yourdetails.enterDataPostSaveClose(telephoneNumber,
			 * marketingPreference);
			 */

			interestedParties = yourdetails.clickOnNextButton();

			interestedParties.pickInterestedParties(3);

			contacts = interestedParties.clickOnNextButton();

			contacts.pickAuthorisedPerson(4);

			payment = contacts.clickOnCheckOutButton();

			payment.CompletePayment("annlPymntBtn", "ve read and understood the information and conditions above.");

			paymentInformation = payment.clickOnNextButton();

			paymentInformation.CompletePaymentInformation();

			securePaymentPage = paymentInformation.clickOnNextButton();

			securePaymentPage.EnterCardInformation();

			worldPay = securePaymentPage.clickOnBuyPolicyButton();

			confirmationOfPayment = worldPay.clickOnContinueButton();

			// Input data for step 5 in excel output
			status.writeExcel(errorMessage, environment, testcase);

			// Step 6
			testcase++;

			// confirmationOfPayment.capturePolicyNumber("C1__QUE_4AB6451013067184536351");

			// Input data for step 6 in excel output
			status.writeExcel(errorMessage, environment, testcase);

			confirmationOfPayment.closeBrowser();

		} catch (DLGHealthCheckException e) {
			// Common.terminateScenario();
			errorMessage = e.getMessage();
			status.writeExcel(errorMessage, environment, testcase);
			throw e;
		} catch (WebDriverException e) {
			// Common.terminateScenario();
			errorMessage = e.getMessage();
			status.writeExcel(errorMessage, environment, testcase);
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			testcase = 1;
		}

	}
}
