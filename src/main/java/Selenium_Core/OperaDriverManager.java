package Selenium_Core;

import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.opera.OperaOptions;

import java.util.concurrent.TimeUnit;


/**
 * ChromeDriverManager - ukazuje na to kako ce se kreirati driver - implementira apstratknu metodu createWebDriver().
 */
public class OperaDriverManager extends DriverManager{
    @Override
    public void createWebDriver(String version) {
        System.setProperty("webdriver.opera.driver","C:\\Users\\Ivan Popovic\\Desktop\\Documentation\\Automation Practice" +
                "\\LandingPages-Regular\\src\\main\\resources\\operadriver"+version+".exe");
        OperaOptions options = new OperaOptions();
        driver = new OperaDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }
}
