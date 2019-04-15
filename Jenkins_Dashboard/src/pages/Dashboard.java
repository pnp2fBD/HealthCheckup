package pages;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.WebDriverWait;
import libraries.WaitUtils;

public class Dashboard extends LoadableComponent<Dashboard> {

	WebDriver driver;
	boolean isPageLoaded;

	@FindBy(xpath = "//div[@class='tabBar']//a[contains(text(),'Jobs Dashboard')]")
	WebElement tabJenkinsDashboard;

	@FindBy(id = "projectstatus")
	WebElement tableDashboard;

	public Dashboard(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void clicktabJenkinsDashboard() {
		tabJenkinsDashboard.click();
	}

	public void scrollToTheElement(WebElement element) {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
	}

	public void takeScreenshotOfDashboard(String fileWithPath) throws IOException {
		TakesScreenshot screenshot = (TakesScreenshot) driver;
		File src = (File) screenshot.getScreenshotAs(OutputType.FILE);
		File DestFile = new File(fileWithPath);
		FileUtils.copyFile(src, DestFile);
	}

	public JobsDashboard flowOfDashboardPage(String fileWithPath) throws IOException {
		this.scrollToTheElement(tableDashboard);
		this.takeScreenshotOfDashboard(fileWithPath);
		((JavascriptExecutor) driver).executeScript("scroll(0, -250);");
		this.clicktabJenkinsDashboard();
		return new JobsDashboard(driver);
	}

	@Override
	protected void isLoaded() throws Error {
		if (!isPageLoaded) {
			System.out.println("Page is not loading");
		}
		(new WebDriverWait(driver, 180).pollingEvery(Duration.ofMillis(200)).ignoring(NoSuchElementException.class)
				.withMessage("Dashboard page did not open up.")).until(ExpectedConditions.visibilityOf(tableDashboard));
	}

	@Override
	protected void load() {
		isPageLoaded = true;
		WaitUtils.waitForPageLoad(driver);
	}

}
