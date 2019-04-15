package libraries;

import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitUtils {
  public static ExpectedCondition<Boolean> documentLoad;
  public static ExpectedCondition<Boolean> framesLoad;
  public static ExpectedCondition<Boolean> imagesLoad;
  public static ExpectedCondition<Boolean> spinnerLoad;
  public static ExpectedCondition<Boolean> overLayLoad;
  public static int maxPageLoadWait = 180;
  public static int maxElementWait = 30;

  static String cssSpinner = ".spinning-on-load-bg-table-active";
  static String overLay = "#overlay";
  private static By allSpinners = By.cssSelector(cssSpinner);
  private static By overLayBy = By.cssSelector(overLay);

  static {
    documentLoad = new ExpectedCondition<Boolean>() {
      public final Boolean apply(final WebDriver driver) {
        final JavascriptExecutor js = (JavascriptExecutor) driver;
        boolean docReadyState = false;
        try {
          docReadyState = (Boolean) js.executeScript(
              "return (function() { if (document.readyState != 'complete') {  return false; } if (window.jQuery != null && window.jQuery != undefined && window.jQuery.active) { return false;} if (window.jQuery != null && window.jQuery != undefined && window.jQuery.ajax != null && window.jQuery.ajax != undefined && window.jQuery.ajax.active) {return false;}  if (window.angular != null && angular.element(document).injector() != null && angular.element(document).injector().get('$http').pendingRequests.length) return false; return true;})();");
        } catch (WebDriverException e) {
          docReadyState = true;
        }
        return docReadyState;
      }
    };

    imagesLoad = new ExpectedCondition<Boolean>() {
      public final Boolean apply(final WebDriver driver) {
        boolean docReadyState = true;
        try {
          JavascriptExecutor js;
          List<WebElement> images = driver.findElements(By.cssSelector("img[src]"));
          for (int i = 0; i < images.size(); i++) {
            try {
              js = (JavascriptExecutor) driver;
              docReadyState = docReadyState && (Boolean) js.executeScript(
                  "return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0",
                  images.get(i));
              if (!docReadyState) {
                break;
              }
            } catch (StaleElementReferenceException e) {
              images = driver.findElements(By.cssSelector("img[src]"));
              i--;
              continue;
            } catch (WebDriverException e) {

              // setting the true value if any exception arise
              // Ex:: inside frame or switching to new windows or
              // switching to new frames
              docReadyState = true;
            }
          }
        } catch (WebDriverException e) {
          docReadyState = true;
        }
        return docReadyState;
      }
    };

    framesLoad = new ExpectedCondition<Boolean>() {
      public final Boolean apply(final WebDriver driver) {
        boolean docReadyState = true;
        try {
          JavascriptExecutor js;
          List<WebElement> frames = driver.findElements(By.cssSelector("iframe[style*='hidden']"));
          for (WebElement frame : frames) {
            try {
              driver.switchTo().defaultContent();
              driver.switchTo().frame(frame);
              js = (JavascriptExecutor) driver;
              docReadyState = docReadyState
                  && (Boolean) js.executeScript("return (document.readyState==\"complete\")");
              driver.switchTo().defaultContent();
              if (!docReadyState) {
                break;
              }
            } catch (WebDriverException e) {
              docReadyState = true;
            }
          }
        } catch (WebDriverException e) {
          docReadyState = true;
        } finally {
          driver.switchTo().defaultContent();
        }
        return docReadyState;
      }
    };

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
        // To wait click events to trigger
        try {
          Thread.sleep(250);
        } catch (InterruptedException ex) {
          ex.printStackTrace();
        }
        spinners = driver.findElements(allSpinners);
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

    overLayLoad = new ExpectedCondition<Boolean>() {
      public final Boolean apply(final WebDriver driver) {
        List<WebElement> overlays = driver.findElements(overLayBy);
        for (WebElement overlay : overlays) {
          try {
            if (overlay.isDisplayed()) {
              return false;
            }
          } catch (NoSuchElementException ex) {
            ex.printStackTrace();
          }
        }
        // To wait click events to trigger
        try {
          Thread.sleep(250);
        } catch (InterruptedException ex) {
          ex.printStackTrace();
        }
        overlays = driver.findElements(overLayBy);
        for (WebElement overlay : overlays) {
          try {
            if (overlay.isDisplayed()) {
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

  public static void waitForPageLoad(final WebDriver driver) {
    waitForPageLoad(driver, 60);
  }

  /**
   * WaitForPageLoad waits for the page load with custom page load wait time
   * 
   * @param driver : Webdriver
   * @param maxWait : Max wait duration
   */
  public static void waitForPageLoad(final WebDriver driver, int maxWait) {
    FluentWait<WebDriver> wait =
        new WebDriverWait(driver, maxWait).pollingEvery(500, TimeUnit.MILLISECONDS)
            .ignoring(StaleElementReferenceException.class).withMessage("Page Load Timed Out");
    try {
      wait.until(WaitUtils.documentLoad);
    } catch (TimeoutException e) {
      driver.navigate().refresh();
      wait.until(WaitUtils.documentLoad);
    }
  }

}
