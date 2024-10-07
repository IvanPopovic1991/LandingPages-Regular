package Selenium_Core;

import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

import java.util.concurrent.TimeUnit;

public class EdgeDriverManager extends DriverManager{
    @Override
    public void createWebDriver(String version) {
        System.setProperty("webdriver.edge.driver","C:\\Users\\Ivan Popovic\\Desktop\\Documentation\\Automation Practice\\" +
                "Setting_project_structure\\src\\main\\resources\\msedgedriver"+version+".exe");
        EdgeOptions options = new EdgeOptions();
        driver = new EdgeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    }
}
