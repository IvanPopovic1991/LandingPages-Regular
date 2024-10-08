package Selenium_Core;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import java.util.concurrent.TimeUnit;

public class FirefoxDriverManager extends DriverManager {
    @Override
    public void createWebDriver(String version) {
        System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver" + version + ".exe");
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("--start-maximized");
        driver = new FirefoxDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }
}
