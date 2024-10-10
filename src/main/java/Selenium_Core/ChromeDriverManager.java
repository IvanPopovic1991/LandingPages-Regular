package Selenium_Core;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;


/**
 * ChromeDriverManager - ukazuje na to kako ce se kreirati driver - implementira apstratknu metodu createWebDriver().
 */
public class ChromeDriverManager extends DriverManager{

    @Override
    public void createWebDriver(String version) {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver" + version + ".exe");
        String filePath = System.getenv("ChromeExeFilePath");
        ChromeOptions options = new ChromeOptions();
        options.setBinary(filePath);
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }
}
