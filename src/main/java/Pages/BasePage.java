package Pages;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;

public class BasePage {
    WebDriver driver;
    int waitTime = 10;

    /**
     * PageFactory- koristi se za direktno kreiranje web elemenata. Omogucava nam da sacuvamo veb element bez koricenja
     * driver.findElement(); Locira elemente na prvi poziv elementa za razliku od driver.findElement-a koji bi prijavljivao
     * noSuchElementException gresku.
     * Svaka stranica u svom konstuktoru ce sadrzati page factory.
     */
    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickElement(WebElement element, String log) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, waitTime);
            wait.until(ExpectedConditions.visibilityOf(element));
            wait.until(ExpectedConditions.elementToBeClickable(element));

            Actions actions = new Actions(driver);
            actions.moveToElement(element).click().build().perform();
            System.out.println("Clicked " + element);
        } catch (StaleElementReferenceException e) {
            Actions actions = new Actions(driver);
            actions.moveToElement(element).click().build().perform();
            System.out.println("Clicked " + element);
        }
    }

    public void typeText(WebElement element, String text, String log) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, waitTime);
            wait.until(ExpectedConditions.visibilityOf(element));
            element.clear();
            element.sendKeys(text);
            System.out.println("Typed " + text + " into" + log + " field");
        } catch (StaleElementReferenceException e) {
            element.clear();
            element.sendKeys(text);
            System.out.println("Typed " + text + " into" + log + " field");
        }
    }

    public void selectFromDropdown(WebElement element, String text, String log) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, waitTime);
            wait.until(ExpectedConditions.visibilityOf(element));
            wait.until(ExpectedConditions.elementToBeClickable(element));

            Select select = new Select(element);
            select.selectByVisibleText(text);
            System.out.println("Selected " + text + " from " + log + " field");
        } catch (StaleElementReferenceException e) {
            Select select = new Select(element);
            select.selectByVisibleText(text);
            System.out.println("Selected " + text + " from " + log + " field");
        }

    }

    public void clickElementBy(By by, String log) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, waitTime);
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));
            wait.until(ExpectedConditions.elementToBeClickable(by));

            Actions actions = new Actions(driver);
            actions.moveToElement(driver.findElement(by)).click().build().perform();
            System.out.println("Clicked " + log);
        } catch (StaleElementReferenceException e) {
            Actions actions = new Actions(driver);
            actions.moveToElement(driver.findElement(by)).click().build().perform();
            System.out.println("Clicked " + log);
        }
    }

    public void typeTextBy(By by, String text, String log) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, waitTime);
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));
            wait.until(ExpectedConditions.elementToBeClickable(by));

            driver.findElement(by).clear();
            driver.findElement(by).sendKeys(text);
            System.out.println("Typed " + text + " into" + log + " field");
        } catch (StaleElementReferenceException e) {
            driver.findElement(by).clear();
            driver.findElement(by).sendKeys(text);
            System.out.println("Typed " + text + " into" + log + " field");
        }
    }

    /**
     * takeScreenShot - metoda za kreiranje screenshot-ova;
     */
    public void takeScreenshot(String fileName) throws IOException {
        /**
         * File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE); - eksplicitna konverzija drivera u fajl
         tipa TakesScreenshot; OutputType.File - Output file tipa FILE
         */
        File file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        /**
         * .copyFile - metoda za kopiranje fajla ( iz klase FileUtils) iz prethodnog koraka u projekat (sadrzi dva parametara;
         * Prvi parametar se odnosi na file iz prethodnog koraka, drugi parametar se odnosi na lokaciju gde ce biti smesten fajl kao i naziv fajla).
         * Prilikom izvrsavanja takesScreenshot metode , metod ce kreirati folder 'screenshot' kao i naziv fajla
         */
        FileUtils.copyFile(file, new File("src/screenshot/" + fileName + ".jpg"));
    }

}
