package pages;

import java.time.Duration;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.WebDriverWait;
import libraries.WaitUtils;

public class HomePage extends LoadableComponent<HomePage> {


  WebDriver driver;
  boolean isPageLoaded;


  @FindBy(xpath = "//div[@class='tabBar']//a[text()='Dashboard']")
  WebElement tabDashboard;

  public HomePage(WebDriver driver) {
    this.driver = driver;
    PageFactory.initElements(driver, this);
  }

  public void clicktabDashboard() {
    tabDashboard.click();
  }

  public Dashboard flowOfHomePage() {
    this.clicktabDashboard();
    return new Dashboard(driver);
  }

  @Override
  protected void isLoaded() throws Error {
    if (!isPageLoaded) {
      System.out.println("Page is not loading");
    }
    (new WebDriverWait(driver, 180).pollingEvery(Duration.ofMillis(200))
        .ignoring(NoSuchElementException.class).withMessage("Jenkins page did not open up."))
            .until(ExpectedConditions.visibilityOf(tabDashboard));
  }

  @Override
  protected void load() {
    isPageLoaded = true;
    WaitUtils.waitForPageLoad(driver);
  }

}
