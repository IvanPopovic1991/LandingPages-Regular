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
        System.setProperty("webdriver.chrome.driver","C:\\Users\\Ivan Popovic\\Desktop\\Documentation\\" +
                "Automation Practice\\Setting_project_structure\\src\\main\\resources\\chromedriver"+version+".exe");
        ChromeOptions options = new ChromeOptions();
        options.setBinary("C:\\Users\\Ivan Popovic\\Desktop\\Documentation\\Automation Practice\\chrome-win64\\chrome.exe");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }
}
