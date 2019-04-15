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
import screens.AgentDashboard;
import screens.CustomerDashboard;
import screens.Login;
import screens.Search;

public class DLG {

  // Business related object variables
  private final static Logger log = Logger.getLogger(DLG.class.getName());

  private static Login login;

  public static Login getLogin() {
    return login;
  }

  public static void setLogin(Login login) {
    DLG.login = login;
  }

  private static AgentDashboard agentDashboard;

  public static AgentDashboard getAgentDashboard() {
    return agentDashboard;
  }

  public static void setAgentDashboard(AgentDashboard agentDashboard) {
    DLG.agentDashboard = agentDashboard;
  }

  private static Search search;

  public static Search getSearch() {
    return search;
  }

  public static void setSearch(Search search) {
    DLG.search = search;
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
  public static Properties OR = new Properties();
  public static Properties Config = new Properties();
  public static Properties HTMLReporter = new Properties();
  public static FileInputStream fis;

  public DLG() {

  }

  // TODO Work of Purendra
  public DLG(String environment) {
    PropertyConfigurator.configure(
        System.getProperty("user.dir") + "\\src\\main\\resources\\properties\\log4j.properties");
    if (DLGGlobal.driver == null) {
      log.debug("driver object equals to null.");
      try {
        fis = new FileInputStream(System.getProperty("user.dir")
            + "\\src\\main\\resources\\properties\\Config.properties");
        System.out.println(fis.toString());
        System.out.println(System.getProperty("user.dir"));
      } catch (FileNotFoundException e) {
        log.error(e.getMessage(), e);
        e.printStackTrace();
      }
      try {
        Config.load(fis);
      } catch (IOException e) {
        log.error(e.getMessage(), e);
        e.printStackTrace();
      }
      try {
        fis = new FileInputStream(
            System.getProperty("user.dir") + "\\src\\main\\resources\\properties\\OR.properties");
      } catch (FileNotFoundException e) {
        log.error(e.getMessage(), e);
        e.printStackTrace();
      }
      try {
        OR.load(fis);
      } catch (IOException e) {
        log.error(e.getMessage(), e);
        e.printStackTrace();
      }
      try {
        fis = new FileInputStream(System.getProperty("user.dir")
            + "\\src\\main\\resources\\properties\\HTMLReporter.properties");
      } catch (FileNotFoundException e) {
        log.error(e.getMessage(), e);
        e.printStackTrace();
      }
      try {
        HTMLReporter.load(fis);
      } catch (IOException e) {
        log.error(e.getMessage(), e);
        e.printStackTrace();
      }
      System.setProperty("org.uncommons.reportng.escape-output", "false");
      DLGGlobal.setStepNumber(1);
      DLGGlobal.setWorkingFolder("cucumber.cc.dlg");
      log.info("Setting the working folder name to " + DLGGlobal.getWorkingFolder());
      DLGGlobal.setJenkinsWorkSpaceName("DLG-" + environment + "-Master");
      log.info("Setting the Jenkins work space name to " + DLGGlobal.getJenkinsWorkSpaceName());
      driver = new FirefoxDriver();
      log.info("Setting the driver to " + driver);
      DLGGlobal.setDriver(driver);
      System.out.println("*********WORKING FOLDER IS********" + DLGGlobal.getWorkingFolder());
      driver.manage().window().maximize();
      log.info("Maximize the browser");
      String testURL = null;
      log.info("Environment selected: " + environment.toUpperCase());
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
      else
        log.error("No URL found to assigned or no environment passed at run time in maven");
      log.info("URL set to as per the environment : " + testURL);
      driver.get(testURL);
      topNavigation = new TopNavigation();
      log.info("Web site launched successfully");
      driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }
  }

}
