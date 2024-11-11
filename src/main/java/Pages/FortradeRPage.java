package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.awt.*;
import java.io.IOException;

public class FortradeRPage extends BasePage {
    public FortradeRPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public @FindBy(id = "FirstName")
    WebElement firstName;

    public @FindBy(id = "LastName")
    WebElement lastName;

    public @FindBy(xpath = "(//div[@class='LcWidgetTopWrapper ClField-Email lcFieldWrapper']//input[@name='Email'])[position()=2]")
    WebElement email;

    public @FindBy(xpath = "//input[@name='PhoneCountryCode']")
    WebElement countryCode;

    public @FindBy(xpath = "//div[@class='phoneWrapper']//input[@placeholder='Phone']")
    WebElement phoneNumber;

    public @FindBy(xpath = "//input[@class='Send-Button']")
    WebElement submitButton;

    public @FindBy(xpath = "//div[@class='userExistsLabelInner']")
    WebElement alrdRegEmailPopUp;

    public void enterFirstName(String firstNameData) {
        typeText(firstName, firstNameData, "first name");
    }

    public void enterLastName(String lastNameData) {
        typeText(lastName, lastNameData, "last name");
    }

    public void enterEmail(String emailData) {
        typeText(email, emailData, "email");
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

    public void successfullyRegistration(String firstNameData, String lastNameData, String emailData, String countryCodeData, String phoneNumberData) {
        enterFirstName(firstNameData);
        enterLastName(lastNameData);
        enterEmail(emailData);
        enterCountryCode(countryCodeData);
        enterPhoneNumber(phoneNumberData);
        //assertColor("green"); - razlikuje se od stranice do stranice
        clickOnSubmitButton();
    }
    public void unsuccessfullyRegistrationWithWrongData(String firstNameData, String lastNameData, String emailData, String countryCode, String phoneNumberData){
        enterFirstName(firstNameData);
        enterLastName(lastNameData);
        enterEmail(emailData);
        enterCountryCode(countryCode);
        enterPhoneNumber(phoneNumberData);
        clickOnSubmitButton();
    }

    /**
     * Izvlaci tekst iz DOM-a i poredi ih sa ocekivanim porukama definisanih u nizu errorMessages
     * @param errorMessages
     */
    public void assertErrorMessages(String[] errorMessages){
        for (int i = 1; i <=4; i++){
            Assert.assertEquals(getTextBy(By.xpath("(//div[@class='errorValidationIn'])[position()=number]".replace("number", String.valueOf(i))),
                    "error message " + errorMessages[i-1]), errorMessages[i-1]);
        }
    }

    /**
     * Metod assertColor koristimo za poredjenje boja input polja na formi za registraciju demo naloga
     * Izvlaci rgb vrednost i razbija ga na tri vrednosti (red, green i blue), i na osnovu vrednosti parametra koji mu
     * prosledis poredi ih sa definisanim vrednostima u metodi.
     * @param color
     */
    public void assertColor (String color){
        WebElement[] fields = {firstName, lastName, email, countryCode, phoneNumber};
        for (int i = 0; i < fields.length; i++){
            /**
             * Ako prosledis color vrednost kao "rgb(123, 123, 132)" onda ukljuci ovaj kod
             */
            /*System.out.println("This is the border color: " + fields[i].getCssValue("border-color"));
            Assert.assertEquals(fields[i].getCssValue("border-color"), color);*/
            /**
             * U suprotnom ako uneses vrednost kao "blue" onda ukljuci ovaj kod
             */
            String borderColor = fields[i].getCssValue("border-color");

// Split the RGB value
            String[] rgbValues = borderColor.replace("rgb(", "").replace(")", "").split(",");
            int red = Integer.parseInt(rgbValues[0].trim());
            int green = Integer.parseInt(rgbValues[1].trim());
            int blue = Integer.parseInt(rgbValues[2].trim());

// Assert if it has a 'red' tone (adjust threshold values as needed)
            if (color.equalsIgnoreCase("red")){
                System.out.println("This is the border color of " +fields[i].getAttribute("name") + " field: " + borderColor);
                Assert.assertTrue(red > 150 && green < 100 && blue < 100, "Border color is not approximately red.");
            } else if (color.equalsIgnoreCase("blue")){
                System.out.println("This is the border color of " +fields[i].getAttribute("name") + " field: " + borderColor);
                Assert.assertTrue(blue > 200 && green > 100 && red < 50, "Border color is not approximately blue.");
            } else if (color.equalsIgnoreCase("green")){
                System.out.println("This is the border color of " +fields[i].getAttribute("name") + " field: " + borderColor);
                Assert.assertTrue(green < 200 && red > 50 && red < 120 && blue > 50 && blue < 100, "Border color is not approximately green.");
            }
        }
    }
    public void assertURL(String url){
        WebDriverWait wait = new WebDriverWait(driver,waitTime);
        wait.until(ExpectedConditions.urlContains(url));
        Assert.assertEquals(driver.getCurrentUrl(),url);
    }
    public void alreadyRegisteredAccount(String firstNameData, String lastNameData, String emailData, String countryCodeData, String phoneNumberData) {
        enterFirstName(firstNameData);
        enterLastName(lastNameData);
        enterEmail(emailData);
        enterCountryCode(countryCodeData);
        enterPhoneNumber(phoneNumberData);
        clickOnSubmitButton();
    }
    private String expTextForPopUp = "Invalid email. Please try another or proceed to log in. If needed, reset your " +
            "password in case it's forgotten.";

    public void assertPopUpForAlreadyRegisteredAccount(String fileName) throws IOException, AWTException {
        Assert.assertEquals(getTextBy(alrdRegEmailPopUp, "alrdRegEmailPopUp"), expTextForPopUp);
        new BasePage(driver).takeScreenshot(fileName,alrdRegEmailPopUp);
    }
}
