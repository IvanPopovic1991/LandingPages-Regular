package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

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
}
