package test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;
import pages.Dashboard;
import pages.HomePage;
import pages.JobsDashboard;
import pages.LoginPage;

public class RunClass {
	WebDriver driver;
	LoginPage login;
	HomePage homePage;
	Dashboard dashboard;
	JobsDashboard jobsDashboard;

	@Test
	public void runTestCase() {
		try {
			System.setProperty("webdriver.chrome.driver", ".\\Driver\\chromedriver.exe");
			Properties property = new Properties();
			property.load(new FileInputStream("config.properties"));
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.get(property.getProperty("Jenkins-URL"));
			login = new LoginPage(driver);
			homePage = login.flowOfLoginPage(property.getProperty("Jenkins-username"),
					property.getProperty("Jenkins-password"));
			dashboard = homePage.flowOfHomePage();
			jobsDashboard = dashboard.flowOfDashboardPage(".\\Screenshot\\Dashboard.png");
			jobsDashboard.takeScreenshotOfDashboard(".\\Screenshot\\Jobs_Dashboard.png");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (driver != null)
				driver.quit();
		}
	}
}
