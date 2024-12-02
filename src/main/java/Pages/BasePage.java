package Pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class BasePage {
    WebDriver driver;
    int waitTime = 20;

    /**
     * PageFactory- koristi se za direktno kreiranje web elemenata. Omogucava nam da sacuvamo veb element bez koricenja
     * driver.findElement(); Locira elemente na prvi poziv elementa za razliku od driver.findElement-a koji bi prijavljivao
     * noSuchElementException gresku.
     * Svaka stranica u svom konstuktoru ce sadrzati page factory.
     */
    public WebDriverWait driverWait;
    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        driverWait = new WebDriverWait(driver, waitTime);
        driverWait.until(webDriver -> ((JavascriptExecutor) webDriver)
                .executeScript("return document.readyState").equals("complete"));
    }

    public void clickElement(WebElement element, String log) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, waitTime);
            wait.until(ExpectedConditions.visibilityOf(element));
            wait.until(ExpectedConditions.elementToBeClickable(element));

            Actions actions = new Actions(driver);
            actions.moveToElement(element).click().build().perform();
            System.out.println("Clicked " + log);
        } catch (StaleElementReferenceException e) {
            Actions actions = new Actions(driver);
            actions.moveToElement(element).click().build().perform();
            System.out.println("Clicked " + log);
        }
    }

    public void typeText(WebElement element, String text, String log) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, waitTime);
            wait.until(ExpectedConditions.visibilityOf(element));
            element.clear();
            element.sendKeys(text);
            System.out.println("Typed " + text + " into " + log + " field");
        } catch (StaleElementReferenceException e) {
            element.clear();
            element.sendKeys(text);
            System.out.println("Typed " + text + " into " + log + " field");
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
            System.out.println("Typed " + text + " into " + log + " field");
        } catch (StaleElementReferenceException e) {
            driver.findElement(by).clear();
            driver.findElement(by).sendKeys(text);
            System.out.println("Typed " + text + " into " + log + " field");
        }
    }

    public String getTextBy(By by, String log) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, waitTime);
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));

            System.out.println("Got text from " + log + " element.");
            return driver.findElement(by).getText();
        } catch (StaleElementReferenceException e) {
            System.out.println("Got text from " + log + " element.");
            return driver.findElement(by).getText();
        }
    }

    public String getTextBy(WebElement element, String log) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, waitTime);
            wait.until(ExpectedConditions.visibilityOf(element));

            System.out.println("Got text from " + log + " element.");
            return element.getText();
        } catch (StaleElementReferenceException e) {
            System.out.println("Got text from " + log + " element.");
            return element.getText();
        }
    }

    /**
     * takeScreenShot - new method for taking full screenshot
     */
    public void takeScreenshot(String fileName, WebElement element) throws AWTException, IOException {

        FluentWait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(Exception.class);

        wait.until(ExpectedConditions.visibilityOf(element));

        // Using java.awt.Robot and java.awt.Dimension for full screen capture
        Robot robot = new Robot();
        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        java.awt.Rectangle screenRect = new java.awt.Rectangle(screenSize);
        BufferedImage screenFullImage = robot.createScreenCapture(screenRect);

        // Saving the full screen image
        ImageIO.write(screenFullImage, "PNG", new File("src/screenshot/" + fileName + ".png"));
    }

    public void takeScreenshot(String fileName) throws AWTException, IOException {

        // Using java.awt.Robot and java.awt.Dimension for full screen capture
        Robot robot = new Robot();
        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        java.awt.Rectangle screenRect = new java.awt.Rectangle(screenSize);
        BufferedImage screenFullImage = robot.createScreenCapture(screenRect);

        // Saving the full screen image
        ImageIO.write(screenFullImage, "PNG", new File("src/screenshot/"+ fileName + ".png"));
    }

    public String getText(WebElement element, String log) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, waitTime);
            wait.until(ExpectedConditions.visibilityOf(element));

            System.out.println("Get text from " + log + " element");
            return element.getText();
        } catch (StaleElementReferenceException e) {

            System.out.println("Get text from " + log + " element");
            return element.getText();
        }
    }

    public String readAttribute(By elementBy, String attribute, String log) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, waitTime);
            wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(elementBy));

            System.out.println("Get text from " + log + " element");
            return driver.findElement(elementBy).getAttribute(attribute);
        } catch (StaleElementReferenceException e) {

            System.out.println("Get text from " + log + " element");
            return driver.findElement(elementBy).getAttribute(attribute);
        }
    }

    public String readAttribute(WebElement element, String attribute, String log) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, waitTime);
            wait.until(ExpectedConditions.visibilityOf(element));

            System.out.println("Get text from " + log + " element");
            return element.getAttribute(attribute);
        } catch (StaleElementReferenceException e) {

            System.out.println("Get text from " + log + " element");
            return element.getAttribute(attribute);
        }
    }

    public void scrollToAnElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);
        js.executeScript("window.scrollBy(0,300);");
        System.out.println("Scrolled to the " + element.getText());
    }

    public void doubleClick(WebElement element, String log) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, waitTime);
            wait.until(ExpectedConditions.visibilityOf(element));
            wait.until(ExpectedConditions.elementToBeClickable(element));

            Actions actions = new Actions(driver);
            actions.moveToElement(element).doubleClick().build().perform();
            System.out.println("Double clicked " + log);
        } catch (StaleElementReferenceException e) {
            Actions actions = new Actions(driver);
            actions.moveToElement(element).doubleClick().build().perform();
            System.out.println("Double clicked " + log);
        }
    }

    public void performRightClick(WebElement element, String url, String log){
        try {
            WebDriverWait wait = new WebDriverWait(driver, waitTime);
            wait.until(ExpectedConditions.visibilityOf(element));
            wait.until(ExpectedConditions.elementToBeClickable(element));
            String linkUrl = readAttribute(element, "href", "attribute url");
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.open(arguments[0], '_blank');", linkUrl);
            List<String> tabs = new ArrayList<>(driver.getWindowHandles());
            driver.switchTo().window(tabs.get(1));
            Assert.assertTrue(driver.getCurrentUrl().contains(url));
            driver.close();
            driver.switchTo().window(tabs.get(0));

            System.out.println("Right click on " + log);
        } catch (StaleElementReferenceException e) {
            String linkUrl = readAttribute(element, "href", "attribute url");
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.open(arguments[0], '_blank');", linkUrl);
            List<String> tabs = new ArrayList<>(driver.getWindowHandles());
            driver.switchTo().window(tabs.get(1));
            Assert.assertTrue(driver.getCurrentUrl().contains(url));
            driver.close();
            driver.switchTo().window(tabs.get(0));

            System.out.println("Right click on " + log);
        }
    }
    public void performRightClickForMailLink(WebElement element, String log) {
        try {
            // Wait for the element to be visible and clickable
            WebDriverWait wait = new WebDriverWait(driver, waitTime);
            wait.until(ExpectedConditions.visibilityOf(element));
            wait.until(ExpectedConditions.elementToBeClickable(element));

            // Perform a right-click (context click) on the element
            Actions actions = new Actions(driver);
            actions.contextClick(element).perform();

            System.out.println("Right-clicked on " + log);
        } catch (StaleElementReferenceException e) {
            // Handle stale element reference by re-locating the element if necessary
            System.out.println("Encountered StaleElementReferenceException. Retrying...");
            WebDriverWait wait = new WebDriverWait(driver, waitTime);
            wait.until(ExpectedConditions.visibilityOf(element));
            wait.until(ExpectedConditions.elementToBeClickable(element));

            Actions actions = new Actions(driver);
            actions.contextClick(element).perform();

            System.out.println("Right-clicked on " + log + " after retrying.");
        }
    }
    /*
    returnDisplayedElement method accept By parameter and list all By locators and store them into WebElement
    elements. Checks all elements and return that element which is displayed on the page. If first time fails to find
    an element, it tries maximum 5 times.
     */
    public WebElement returnDisplayedElement(By element) {
        int retries = 5;
        int delay = 1000; // 1 second

        for (int i = 0; i < retries; i++) {
            try {
                // Check for element visibility
                List<WebElement> elements = driver.findElements(element);
                for (WebElement displayedElement : elements) {
                    if (displayedElement.isDisplayed()) {
                        return displayedElement;
                    }
                }
                // If element not found, pause briefly and try again
                Thread.sleep(delay);
            } catch (Exception e) {
                System.out.println("Retrying to find element: " + e.getMessage());
            }
        }
        return null; // Return null if not found after retries
    }
    public boolean isOutlookRunning() {
        try {
            // Use ProcessBuilder to run the "tasklist" command
            ProcessBuilder processBuilder = new ProcessBuilder(List.of("tasklist"));
            Process process = processBuilder.start();

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains("HxOutlook.exe")) {
                    return true;  // Outlook process is running
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;  // Outlook is not running
    }
    public void closeOutlook() {
        if (isOutlookRunning()) {
            try {
                // Use ProcessBuilder to run the "taskkill" command to close Outlook
                ProcessBuilder processBuilder = new ProcessBuilder(List.of("taskkill", "/F", "/IM", "HxOutlook.exe"));
                processBuilder.start();
                System.out.println("Outlook has been closed.");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Outlook is not running.");
        }
    }
}
