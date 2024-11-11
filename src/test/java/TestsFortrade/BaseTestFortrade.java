package TestsFortrade;

import Selenium_Core.DriverManager;
import Selenium_Core.DriverManagerFactory;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

public class BaseTestFortrade {
    WebDriver driver;
    DriverManager driverManager;

    public void baseSetup(String browser, String version) {
        driverManager = DriverManagerFactory.getDriverManager(browser);
        driver = driverManager.getWebDriver(version);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    public void baseTearDown() {
        driverManager.quitDriver();
    }
}
