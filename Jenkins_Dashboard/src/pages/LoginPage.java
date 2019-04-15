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

public class LoginPage extends LoadableComponent<LoginPage> {


  WebDriver driver;
  boolean isPageLoaded;

  @FindBy(css = "input#j_username")
  WebElement userNameElement;

  @FindBy(css = "input[name='j_password']")
  WebElement passwordElement;

  @FindBy(css = "button[id*='yui-gen']")
  WebElement btnLogIn;

  public LoginPage(WebDriver driver) {
    this.driver = driver;
    PageFactory.initElements(driver, this);
  }

  public void enterUserName(String userName) {
    userNameElement.sendKeys(userName);
  }

  public void enterPassword(String password) {
    passwordElement.sendKeys(password);
  }

  public void clickLogin() {
    btnLogIn.click();
  }

  public HomePage flowOfLoginPage(String userName, String password) {
    this.enterUserName(userName);
    this.enterPassword(password);
    this.clickLogin();
    return new HomePage(driver);
  }

  @Override
  protected void isLoaded() throws Error {
    if (!isPageLoaded) {
      System.out.println("Page is not loading");
    }
    (new WebDriverWait(driver, 180).pollingEvery(Duration.ofMillis(200))
        .ignoring(NoSuchElementException.class).withMessage("Jenkins page did not open up."))
            .until(ExpectedConditions.visibilityOf(userNameElement));
  }

  @Override
  protected void load() {
    isPageLoaded = true;
    WaitUtils.waitForPageLoad(driver);
  }

}
