package utility;

import java.util.List;
import java.util.concurrent.TimeUnit;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitUtil {
  static String cssSpinner = ".spinning-on-load-bg-table-active";
  private static By allSpinners = By.cssSelector(cssSpinner);
  private static final Logger log = Logger.getLogger(WaitUtil.class.getName());
  public static ExpectedCondition<Boolean> spinnerLoad;

  static {
    spinnerLoad = new ExpectedCondition<Boolean>() {
      public final Boolean apply(final WebDriver driver) {
        List<WebElement> spinners = driver.findElements(allSpinners);
        for (WebElement spinner : spinners) {
          try {
            if (spinner.isDisplayed()) {
              return false;
            }
          } catch (NoSuchElementException ex) {
            ex.printStackTrace();
          }
        }
        return true;
      }
    };
  }

  public static void waitTillVisibilityOfElementLocated(WebDriver driver, int timeUnitInSeconds,
      WebElement element) {
    new WebDriverWait(driver, timeUnitInSeconds).until(ExpectedConditions.visibilityOf(element));
  }

  public static void waitTillVisibilityOfElementLocated(WebDriver driver, int timeUnitInSeconds,
      By locator) {
    new WebDriverWait(driver, timeUnitInSeconds)
        .until(ExpectedConditions.invisibilityOfElementLocated(locator));
  }

  public static void waitTillElementClickable(WebDriver driver, int timeUnitInSeconds,
      WebElement element) {
    new WebDriverWait(driver, timeUnitInSeconds)
        .until(ExpectedConditions.elementToBeClickable(element));
  }

  public static void waitForSpinner(final WebDriver driver) {
    try {
      (new WebDriverWait(driver, 180).pollingEvery(500, TimeUnit.MILLISECONDS)
          .ignoring(NoSuchElementException.class, StaleElementReferenceException.class)
          .withMessage("Realize spinners/page not loading")).until(spinnerLoad);
    } catch (Exception ex) {
      log.debug("Catched spinner load exception or Spinner is taking lot of time");
    }
    log.debug("Spinner Load Wait: (Sync)");
  }
}
