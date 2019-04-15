package pages;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import org.apache.commons.io.FileUtils;
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

public class JobsDashboard extends LoadableComponent<JobsDashboard> {


  WebDriver driver;
  boolean isPageLoaded;

  @FindBy(id = "widgets")
  WebElement dashboardWidget;

  public JobsDashboard(WebDriver driver) {
    this.driver = driver;
    PageFactory.initElements(driver, this);
  }

  public void takeScreenshotOfDashboard(String fileWithPath) throws IOException {
    TakesScreenshot screenshot = (TakesScreenshot) driver;
    File src = (File) screenshot.getScreenshotAs(OutputType.FILE);
    File DestFile = new File(fileWithPath);
    FileUtils.copyFile(src, DestFile);
  }

  @Override
  protected void isLoaded() throws Error {
    if (!isPageLoaded) {
      System.out.println("Page is not loading");
    }
    (new WebDriverWait(driver, 180).pollingEvery(Duration.ofMillis(200))
        .ignoring(NoSuchElementException.class).withMessage("Dashboard page did not open up."))
            .until(ExpectedConditions.visibilityOf(dashboardWidget));
  }

  @Override
  protected void load() {
    isPageLoaded = true;
    WaitUtils.waitForPageLoad(driver);
  }

}
