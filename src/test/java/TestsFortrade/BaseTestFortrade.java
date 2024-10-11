package TestsFortrade;

import Pages.BasePage;
import Selenium_Core.DriverManager;
import Selenium_Core.DriverManagerFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.awt.*;
import java.io.IOException;

public class BaseTestFortrade {
    WebDriver driver;
    DriverManager driverManager;

    public void baseSetup(String browser, String version) {
        driverManager = DriverManagerFactory.getDriverManager(browser);
        driver = driverManager.getWebDriver(version);
        driver.get("https://www.fortrade.com/minilps/en/education/");
    }

    public void baseTearDown(String fileName) throws IOException, AWTException {
        new BasePage(driver).takeScreenshot(fileName,driver.findElement(By.xpath("//div[@id='startTradingButton']")));
        driverManager.quitDriver();

    }
}
