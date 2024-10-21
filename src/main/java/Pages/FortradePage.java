package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.awt.*;
import java.io.IOException;

public class FortradePage extends BasePage{

    public FortradePage(WebDriver driver) {
            super(driver);
            PageFactory.initElements(driver, this);
        }

        @FindBy(xpath = "//input[@name='FirstName']")
        WebElement firstName;

        @FindBy(xpath = "//input[@name='LastName']")
        WebElement lastName;

        @FindBy(xpath = "(//div[@class='LcWidgetTopWrapper ClField-Email lcFieldWrapper']//input[@name='Email'])[position()=2]")
        WebElement email;

        @FindBy(xpath = "//input[@name='PhoneCountryCode']")
        WebElement countryCode;

        @FindBy(xpath = "//input[@name='Phone']")
        WebElement phoneNumber;

        @FindBy(xpath = "//input[@class='Send-Button']")
        WebElement submitButton;

        @FindBy(xpath = "//button[@id='CybotCookiebotDialogBodyButtonDecline']")
        WebElement denyBtn;

        @FindBy (xpath = "//div[@id='startTradingButton']")
        WebElement continueBtn;

        @FindBy (xpath = "//div[@data-cmd='menu']")
        WebElement menuBtn;

        @FindBy (xpath = "//div[@id='platformRegulation']")
        WebElement regulationMsg;

        public void enterFirstName(String firstNameData) {
            typeText(firstName, firstNameData, "fist name");
        }

        public void enterLastName(String lastNameData) {
            typeText(lastName, lastNameData, "last name");
        }

        public void enterEmail(String emailData) {
            typeText(email, emailData, "email name");
        }

        public void enterCountryCode(String countryCodeData) {
            typeText(countryCode, countryCodeData, "country code");
        }

        public void enterPhoneNumber(String phoneNumberData) {
            typeText(phoneNumber, phoneNumberData, "phone number");
        }

        public void clickOnSubmitButton() {
            clickElement(submitButton, "get started button");
        }

        public void clickDenyBtn(){
            clickElement(denyBtn,"deny cookies button");
        }

        public void successfullyRegistration(String firstNameData, String lastNameData, String emailData, String countryCodeData, String phoneNumberData) {
            enterFirstName(firstNameData);
            enterLastName(lastNameData);
            enterEmail(emailData);
            enterCountryCode(countryCodeData);
            enterPhoneNumber(phoneNumberData);
            clickDenyBtn();
            clickOnSubmitButton();
        }
    public void assertURL(String url){
        WebDriverWait wait = new WebDriverWait(driver,waitTime);
        wait.until(ExpectedConditions.urlContains(url));
        Assert.assertEquals(driver.getCurrentUrl(),url);
    }
    public void clickContinueBtn(){
            clickElement(continueBtn,"continue button");
    }
    public void clickMenuBtn(){
            clickElement(menuBtn,"menu button");
    }
    public void checkRegulation(String regulation) throws IOException, AWTException {
            String actualText = getText(regulationMsg, "regulation text");
            clickDenyBtn();
            switch (regulation){
                case "FCA" : {
                    Assert.assertEquals(actualText,"Broker: Fortrade Ltd. (FCA)");
                    new BasePage(driver).takeScreenshot("Broker Fortrade Ltd FCA - successfully registered demo account",regulationMsg);
                }
                break;
                case "cyses" : {
                    Assert.assertEquals(actualText,"Broker: Fortrade Cyprus Ltd. (CySec)");
                    new BasePage(driver).takeScreenshot("Broker Fortrade Cyprus Ltd CySec - successfully registered demo account", regulationMsg);
                }
                break;
                case "Asic" : {
                    Assert.assertEquals(actualText,"Broker: Fort Securities Australia Pty Ltd. (ASIC)");
                    new BasePage(driver).takeScreenshot("Broker Fort Securities Australia Pty Ltd ASIC - successfully registered demo account",regulationMsg);
                }break;
                case "iiroc" : {
                    Assert.assertEquals(actualText,"Broker: Fortrade Canada Limited (CIRO)");
                    new BasePage(driver).takeScreenshot("Broker Fortrade Canada Limited CIRO - successfully registered demo account",regulationMsg);
                }break;
                case "FSC" :
                default: {
                    Assert.assertEquals(actualText,"Broker: Fortrade (Mauritius) Ltd (FSC)");
                    new BasePage(driver).takeScreenshot("Broker Fortrade Mauritius Ltd FSC - successfully registered demo account",regulationMsg);
                }break;
            }
    }
}
