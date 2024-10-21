package TestsFortrade;

import Selenium_Core.DriverManager;
import Selenium_Core.DriverManagerFactory;
import org.openqa.selenium.WebDriver;

public class BaseTestFortrade {
    WebDriver driver;
    DriverManager driverManager;

    public void baseSetup(String browser, String version) {
        driverManager = DriverManagerFactory.getDriverManager(browser);
        driver = driverManager.getWebDriver(version);
        driver.get("https://www.fortrade.com/minilps/en/education/");
    }

    public void baseTearDown() {
        driverManager.quitDriver();
    }
}
