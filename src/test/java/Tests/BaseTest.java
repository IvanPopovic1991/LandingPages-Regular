package Tests;

import Selenium_Core.DriverManager;
import Selenium_Core.DriverManagerFactory;
import org.openqa.selenium.WebDriver;

public class BaseTest {
    WebDriver driver;
    DriverManager driverManager;

    public void baseSetup(String browser, String version) {
        driverManager = DriverManagerFactory.getDriverManager(browser);
        driver = driverManager.getWebDriver(version);
        driver.get("");
    }

    public void baseTearDown() {
        driverManager.quitDriver();
    }
}
