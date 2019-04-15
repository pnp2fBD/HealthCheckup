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
import UXP.DLGGlobal;
import screens.CustomerDashboard;
import screens.HomePage;
import screens.Login;

public class DLG {

  private final static Logger log = Logger.getLogger(DLG.class.getName());

  private static Login login;

  public static Login getLogin() {
    return login;
  }

  public static void setLogin(Login login) {
    DLG.login = login;
  }

  private static HomePage homePage;

  public static HomePage gethomePage() {
    return homePage;
  }

  public static void setHomePage(HomePage homePage) {
    DLG.homePage = homePage;
  }

  private static CustomerDashboard customerDashboard;

  public static CustomerDashboard getCustomerDashboard() {
    return customerDashboard;
  }

  public static void setCustomerDashboard(CustomerDashboard customerDashboard) {
    DLG.customerDashboard = customerDashboard;
  }

  public static WebDriver driver;
  public static TopNavigation topNavigation;
  public static int stepNumber;

  public static Logger logger = Logger.getLogger("devpinoyLogger");
  public static Properties OR = new Properties();
  public static Properties Config = new Properties();
  public static Properties HTMLReporter = new Properties();
  public static FileInputStream fis;

  public DLG() {

  }

  public DLG(String environment) {
    PropertyConfigurator.configure(
        System.getProperty("user.dir") + "\\src\\main\\resources\\properties\\log4j.properties");
    log.debug("Entering in DLG parameterized constructor of DLG class");
    if (DLGGlobal.driver == null) {
      try {
        fis = new FileInputStream(System.getProperty("user.dir")
            + "\\src\\main\\resources\\properties\\Config.properties");
      } catch (FileNotFoundException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
      try {
        Config.load(fis);
      } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
      try {
        OR.load(fis);
      } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
      try {
        fis = new FileInputStream(System.getProperty("user.dir")
            + "\\src\\main\\resources\\properties\\HTMLReporter.properties");
      } catch (FileNotFoundException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
      try {
        HTMLReporter.load(fis);
      } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
      System.setProperty("org.uncommons.reportng.escape-output", "false");
      DLGGlobal.setStepNumber(1);
      DLGGlobal.setWorkingFolder("cucumber.ss.dlg");
      DLGGlobal.setJenkinsWorkSpaceName("DLG-" + environment + "-Master");
      driver = new FirefoxDriver();
      DLGGlobal.setDriver(driver);
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
        testURL = Config.getProperty("testsiteurl-TRAINING");
      else if (environment.equalsIgnoreCase("CD"))
        testURL = Config.getProperty("testsiteurl-CD");
      else if (environment.equalsIgnoreCase("CT"))
        testURL = Config.getProperty("testsiteurl-CT");
      driver.get(testURL);
      topNavigation = new TopNavigation();
      logger.debug("Web site launched successfully");
      driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
      log.info(driver + " launched successfully. Environment is " + environment
          + " and the URL launched is " + testURL);
    }
    log.debug("Exit from DLG parameteized constructor of DLG class");
  }
}
