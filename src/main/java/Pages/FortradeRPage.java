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
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FortradeRPage extends BasePage {
    public FortradeRPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//input[@id='FirstName']")
    public WebElement firstName;

    @FindBy(xpath = "//input[@id='LastName']")
    public WebElement lastName;

    @FindBy(xpath = "(//div[@class='LcWidgetTopWrapper ClField-Email lcFieldWrapper']//input[@name='Email'])[position()=2]")
    public WebElement email;

    @FindBy(xpath = "//input[@name='PhoneCountryCode']")
    public WebElement countryCode;

    @FindBy(xpath = "//input[@placeholder='Number']")
    public WebElement phoneNumber;

    @FindBy(xpath = "//input[contains(@class,'Send-Button')]")
    public WebElement submitButton;

    @FindBy(xpath = "//div[@class='userExistsLabelInner']")
    public WebElement alrdRegEmailPopUp;

    @FindBy(xpath = "(//div[@class='errorValidationIn'])[last()]")
    public WebElement countryCodeErrorMessage;

    @FindBy(xpath = "//div[@class='logo']")
    public WebElement fortradeLogo;

    @FindBy(xpath = "//div[contains(text(),'Login')]")
    public WebElement loginToFortrade;

    @FindBy(xpath = "//div[@data-cmd='menu']")
    public WebElement menuBtn;

    @FindBy(xpath = "//button[@class='heroBtn']")
    public WebElement startBtn;

    public By privacyPolicyLinkBy = By.xpath("//div[@class='fscClass']//a[text()='Privacy Policy']");

    public By termsAndConditionsLinkBy = By.xpath("//div[@class='fscClass']//a[contains(text(), 'Terms and Conditions')]");

    public By clickHereLinkBy = By.xpath("(//a[text()='click here'])[2]");

    public By alreadyHaveAnAccountLinkBy = By.xpath("//*[@class='alreadyHaveAcc']//a[contains(text(), 'Already have an account?')]");

    public By contactUsLinkBy = By.xpath("//*[@class='needHelp']//a[contains(text(), 'Contact Us')]");

    public By facebookLinkBy = By.xpath("//a[@class='facebook-links']");

    public By instagramLinkBy = By.xpath("//a[@href='https://www.instagram.com/fortrade_online_trading/?hl=en']");

    public By youtubeLinkBy = By.xpath("//a[@href='https://www.youtube.com/channel/UCNCrGhrDTEN1Hx_20-kFxwg']");

    public By infoLinkBy = By.xpath("//div[@class='col-md-12 text-center']//a[text()='info@fortrade.com']");

    public By supportLinkBy = By.xpath("//a[text()='support@fortrade.com']");

    public By footerRiskWarningLinkBy = By.xpath("//div[@class='footerRiskDisclaimer']//a[contains(text(), 'Risk warning')]");

    public By footerPrivacyPolicyLinkBy = By.xpath("//div[@class='fscClass']//a[contains(text(),'Privacy policy')]");

    public By footerPrivacyPolicyFortradeRLinkBy = By.xpath("//div[@class='fscClass']//a[contains(text(), 'Privacy policy')]");

    public By fscRegulationLinkBy = By.xpath("//a[text()=' GB21026472']");


    String[] errorMessages = {"Please enter all your given first name(s)",
            "Please enter your last name in alphabetic characters",
            "Invalid email format.",
            "Invalid phone format."};

    String[] sameNamesErrorMessages = {"Your first name must be different from your last name",
            "Your first name must be different from your last name"};

    // Privacy Policy document link
    public String privacyPolicyFSC = "https://www.fortrade.com/fortrade-ma-privacy-policy/";

    // Terms and conditions document link
    public String termsAndConditionsFSC = "https://www.fortrade.com/fortrade-mauritius-client-agreement/";

    //How to unsubscribe document link
    public String howToUnsubscribeURL = "https://www.fortrade.com/wp-content/uploads/legal/How_to_guides/How_to_unsubscribe.pdf";

    // Already have an account link
    public String alrHaveAccount = "https://pro.fortrade.com/";

    // Privacy policy document Footer link
    public String privacyPolicyFSCFooter = "https://www.fortrade.com/wp-content/uploads/legal/FSC/Fortrade_MA_Privacy_Policy.pdf";

    // Financial Services Commission, Mauritius (FSC) link
    public String fscLink = "https://www.fscmauritius.org/en/supervision/register-of-licensees/register-of-licensees-details?licence_no=GB21026472&key=&cat=_GB&code=";

    public String fbURL = "https://www.facebook.com/Fortrade.International";

    public String insURL = "https://www.instagram.com/fortrade_online_trading/?hl=en";

    public String ytURL = "https://www.youtube.com/channel/UCNCrGhrDTEN1Hx_20-kFxwg";

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

    public void clickMenuBtn(){
        clickElement(menuBtn,"menu button");
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

    public void unsuccessfullyRegistrationWithWrongData(String firstNameData, String lastNameData, String emailData, String countryCode, String phoneNumberData) {
        enterFirstName(firstNameData);
        enterLastName(lastNameData);
        enterEmail(emailData);
        enterCountryCode(countryCode);
        enterPhoneNumber(phoneNumberData);
        clickOnSubmitButton();
    }

    /**
     * Izvlaci tekst iz DOM-a i poredi ih sa ocekivanim porukama definisanih u nizu errorMessages
     */
    public void assertErrorMessages() {
        for (int i = 1; i <= 4; i++) {
            Assert.assertEquals(getTextBy(By.xpath("(//div[@class='errorValidationIn'])[position()=number]".replace("number", String.valueOf(i))),
                    "error message " + errorMessages[i - 1]), errorMessages[i - 1]);
        }
    }

    public void assertSameNameErrorMsgs() {
        for (int i = 1; i <= 2; i++) {
            Assert.assertEquals(getTextBy(By.xpath("(//div[@class='errorValidationIn'])[position()=number]".replace("number", String.valueOf(i))),
                    "error message " + sameNamesErrorMessages[i - 1]), sameNamesErrorMessages[i - 1]);
        }
    }

    /**
     * Metod assertColor koristimo za poredjenje boja input polja na formi za registraciju demo naloga
     * Izvlaci rgb vrednost i razbija ga na tri vrednosti (red, green i blue), i na osnovu vrednosti parametra koji mu
     * prosledis poredi ih sa definisanim vrednostima u metodi.
     *
     * @param color
     */
    public void assertColor(String color) {
        WebElement[] fields = {firstName, lastName, email, countryCode, phoneNumber};
        for (int i = 0; i < fields.length; i++) {
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
            if (color.equalsIgnoreCase("red")) {
                System.out.println("This is the border color of " + fields[i].getAttribute("name") + " field: " + borderColor);
                Assert.assertTrue(red > 150 && green < 100 && blue < 100, "Border color is not approximately red.");
            } else if (color.equalsIgnoreCase("blue")) {
                System.out.println("This is the border color of " + fields[i].getAttribute("name") + " field: " + borderColor);
                Assert.assertTrue(blue > 200 && green > 100 && red < 50, "Border color is not approximately blue.");
            } else if (color.equalsIgnoreCase("green")) {
                System.out.println("This is the border color of " + fields[i].getAttribute("name") + " field: " + borderColor);
                Assert.assertTrue(green < 200 && red > 50 && red < 120 && blue > 50 && blue < 100, "Border color is not approximately green.");
            }
        }
    }

    public void assertURL(String url) {
        WebDriverWait wait = new WebDriverWait(driver, waitTime);
        wait.until(ExpectedConditions.urlContains(url));
        Assert.assertEquals(driver.getCurrentUrl(), url);
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
        new BasePage(driver).takeScreenshot(fileName, alrdRegEmailPopUp);
    }

    public void checkLogoClickability(String url) {
        WebDriverWait driverWait = new WebDriverWait(driver, 10);
        driverWait.until(ExpectedConditions.visibilityOf(fortradeLogo));
        try {
            fortradeLogo.click();
            System.out.println("Logo is not clickable.");
        } catch (Exception e) {
            System.out.println("Logo is not clickable, as expected.");
        }
        assertURL(url);
        List<String> tabs = new ArrayList<>(driver.getWindowHandles());
        Assert.assertEquals(tabs.size(), 1);
    }

    /*
    This method accept instance of Robot class and text. It breaks String to a character, call the method for convert
     a character into a number (keyCode) and type one by one character into the field (for example click on field and call
      this typeString method)
     */
    public void typeString(Robot robot, String text) {
        for (char c : text.toCharArray()) {
            int keyCode = KeyEvent.getExtendedKeyCodeForChar(c);
            if (KeyEvent.CHAR_UNDEFINED != keyCode) {
                robot.keyPress(keyCode);
                robot.keyRelease(keyCode);
            }
        }
    }

    public void checkCountryCodeErrorMessage(String wrongCountryCodeDataText) {
        clickElement(countryCode, "country code field");
        try {
            Robot robot = new Robot();
            typeString(robot, wrongCountryCodeDataText);
            clickElement(phoneNumber, "phone number field");
            Assert.assertEquals(getTextBy(countryCodeErrorMessage, "country code error message: " + countryCodeErrorMessage.getText())
                    , "Please enter a valid country code");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void clickOnSelectedLink(By element, String url,String document) throws IOException, AWTException, InterruptedException {
        WebElement displayedElement = returnDisplayedElement(element);
        if (displayedElement != null) {
            clickElement(displayedElement, "link " + displayedElement.getText());
        } else {
            System.out.println("Element is not found!");
        }
        List<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
        assertURL(url);
        Thread.sleep(2000);
        takeScreenshot(document +" document - FortradeR");
        driver.close();
        driver.switchTo().window(tabs.get(0));
    }

    public void clickOnMailLink(String mailLink){
        if (mailLink.equalsIgnoreCase("contactUs")){
            clickElementBy(contactUsLinkBy, "contact us link");
        } else if (mailLink.equalsIgnoreCase("info")){
            clickElementBy(infoLinkBy, "info link");
        } else if (mailLink.equalsIgnoreCase("support")){
            clickElementBy(supportLinkBy, "support link");
        }
        Assert.assertTrue(isOutlookRunning());
    }

    public void rightClickOnMailLink(String mailLink){
        if (mailLink.equalsIgnoreCase("contactUs")){
            performRightClickForMailLink(driver.findElement(contactUsLinkBy), "contact us link");
        } else if (mailLink.equalsIgnoreCase("info")){
            performRightClickForMailLink(driver.findElement(infoLinkBy), "info link");
        } else if (mailLink.equalsIgnoreCase("support")){
            performRightClickForMailLink(driver.findElement(supportLinkBy), "support link");
        }
        Assert.assertTrue(isOutlookRunning());
    }

    public void rightClickOnSelectedLink(By element, String url) {
        performRightClick(returnDisplayedElement(element), url, "link" + returnDisplayedElement(element).getText());
    }

    public void loginRedirection() throws IOException, AWTException {
        clickElement(driver.findElement(alreadyHaveAnAccountLinkBy),"An already have account?");
    }

    public void clickStartBtn()
    {
        clickElement(startBtn,"Start button");
    }
}
