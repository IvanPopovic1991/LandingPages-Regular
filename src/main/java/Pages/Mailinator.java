package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Mailinator extends BasePage{
    /**
     * PageFactory- koristi se za direktno kreiranje web elemenata. Omogucava nam da sacuvamo veb element bez koricenja
     * driver.findElement(); Locira elemente na prvi poziv elementa za razliku od driver.findElement-a koji bi prijavljivao
     * noSuchElementException gresku.
     * Svaka stranica u svom konstuktoru ce sadrzati page factory.
     *
     * @param driver
     */
    public Mailinator(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver,this);
    }
    @FindBy(xpath = "//input[@id='search']")
    public WebElement search;

    @FindBy(xpath = "//button[contains(text(),'GO')]")
    public WebElement goBtn;

    @FindBy(xpath = "//td[contains(text(),'Fortrade')][1]")
    public WebElement emailMessage;

    @FindBy(xpath = "//div[contains(text(),'Welcome to Fortrade')]")
    public WebElement emailTitle;

    public void findEmail(String emailValue){
        typeText(search,emailValue,"search input");
        clickElement(goBtn,"go button");
        WebDriverWait wait = new WebDriverWait(driver,15);
        wait.until(ExpectedConditions.visibilityOf(emailMessage));
        wait.until(ExpectedConditions.elementToBeClickable(emailMessage));
        clickElement(emailMessage,"received message in mailbox");
    }

    /**
     * zoomOutMethod() method is used to help the takeScreenshot() method to capture the whole email message (header,
     * body, and footer)
     * @throws AWTException
     */
    public void zoomOutMethod() throws AWTException {
        Robot robot = new Robot();
        for (int i = 0; i < 5; i++) {
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_MINUS);
            robot.keyRelease(KeyEvent.VK_MINUS);
            robot.keyRelease(KeyEvent.VK_CONTROL);
        }
    }

}
