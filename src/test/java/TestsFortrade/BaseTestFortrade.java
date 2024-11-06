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
        driver.get("https://www.fortrade.com/minilps/en/education/?tg=ivanA1389&tag1=ivanB@1389&tag2=ivanL1389&tag3=" +
                "ivanM1389&gid=ivanC@1389&G_GEO=ivanD1389&G_GEOint=ivanE1389&G_Device=ivanF1389&G_DeviceModel=ivanG1389" +
                "&G_AdPos=ivanH1389&g_Track=ivanI1389&Track=ivanj1389&gclid=ivanK1389");
    }

    public void baseTearDown() {
        driverManager.quitDriver();
    }
}
