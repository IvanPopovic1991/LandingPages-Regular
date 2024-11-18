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
import java.util.ArrayList;
import java.util.List;

public class FortradePage extends BasePage {

    public FortradePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//body[@data-lcreg]")
    public WebElement bodyTag;

    @FindBy(xpath = "//div[contains(@class,'logo')]")
    public WebElement logo;

    @FindBy(xpath = "//div[contains(@class,'logo iirocClass')]")
    public WebElement logoIiroc;

    @FindBy(xpath = "//div[contains(@class,'logo cysecClass')]")
    public WebElement logoCysec;

    @FindBy(xpath = "//input[@name='FirstName']")
    public WebElement firstName;

    @FindBy(xpath = "//input[@name='LastName']")
    public WebElement lastName;

    @FindBy(xpath = "(//div[@class='LcWidgetTopWrapper ClField-Email lcFieldWrapper']//input[@name='Email'])[position()=2]")
    public WebElement email;

    @FindBy(xpath = "//input[@name='PhoneCountryCode']")
    public WebElement countryCode;

    @FindBy(xpath = "//input[@name='Phone']")
    public WebElement phoneNumber;

    @FindBy(xpath = "//input[@class='Send-Button']")
    public WebElement submitButton;

    @FindBy(xpath = "//button[@id='CybotCookiebotDialogBodyButtonDecline']")
    public WebElement denyBtn;

    @FindBy(xpath = "//div[@id='startTradingButton']")
    public WebElement continueBtn;

    @FindBy(xpath = "//div[@data-cmd='menu']")
    public WebElement menuBtn;

    @FindBy(xpath = "//div[@id='platformRegulation']")
    public WebElement regulationMsg;

    @FindBy(xpath = "//div[@class='userExistsLabelInner']")
    public WebElement alrdRegEmailPopUp;

    @FindBy(xpath = "//iframe[@id='myWidgetIframe']")
    public WebElement iFrameIConsent;

    @FindBy(xpath = "//input[@value='I Consent']")
    public WebElement iConsentBtn;

    @FindBy(xpath = "//div[@class='pushcrew-chrome-style-notification pushcrew-chrome-style-notification-safari']")
    public WebElement popUpNotification;

    @FindBy(xpath = "(//div[@class='errorValidationIn'])[last()]")
    public WebElement countryCodeErrorMessage;

    public By privacyPolicyLinkBy = By.xpath("//div[@class='form-wrapper']//a[text()='Privacy Policy']");
    public By termsAndConditionsLinkBy = By.xpath("//div[@class='form-wrapper']//a[contains(text(), 'Terms and Conditions')]");
    public By clickHereLinkBy = By.xpath("//div[@class='MarketingMaterials2']//a[text()='click here']");
    public By alreadyHaveAnAccountLinkBy = By.xpath("//*[@class='alreadyHaveAcc']//a[contains(text(), 'Already have an account?')]");
    public By contactUsLinkBy = By.xpath("//*[@class='needHelp']//a[text()='Contact Us']");

    public By facebookLinkBy = By.xpath("//a[@href='https://www.facebook.com/Fortrade.International']");
    public By instagramLinkBy = By.xpath("//a[@href='https://www.instagram.com/fortrade_online_trading/?hl=en']");
    public By youtubeLinkBy = By.xpath("//a[@href='https://www.youtube.com/channel/UCNCrGhrDTEN1Hx_20-kFxwg']");

    public By infoLinkBy = By.xpath("//div[@class='col-md-12 text-center']//a[text()='info@fortrade.com']");
    public By supportLinkBy = By.xpath("//a[text()='support@fortrade.com']");
    public By footerRiskWarningLinkBy = By.xpath("//div[@class='footerRiskDisclaimer']//a[contains(text(), 'Risk warning')]");
    public By footerPrivacyPolicyLinkBy = By.xpath("//div[@class='footerRiskDisclaimer']//a[contains(text(), 'Privacy policy')]");
    public By footerPrivacyPolicyFortradeRLinkBy = By.xpath("//div[@class='fscClass']//a[contains(text(), 'Privacy policy')]");

    public By fcaRegulationLinkBy = By.xpath("//a[text()='FRN: 609970']");
    public By ciroRegulationLinkBy = By.xpath("//a[text()='CRN: BC1148613']");
    public By asicRegulationLinkBy = By.xpath("//a[text()='ABN: 33 614 683 831 | AFSL: 493520']");
    public By cysecRegulationLinkBy = By.xpath("//a[text()='CIF license number 385/20']");
    public By fscRegulationLinkBy = By.xpath("//a[text()=' GB21026472']");


    String[] errorMessages = {"Please enter all your given first name(s)",
            "Please enter your last name in alphabetic characters",
            "Invalid email format.",
            "Invalid phone format."};

    String[] sameNamesErrorMessages = {"Your first name must be different from your last name",
            "Your first name must be different from your last name"};

    // Privacy Policy document link - all regulations
public String privacyPolicyFSC = "https://www.fortrade.com/fortrade-ma-privacy-policy/" ;
public String privacyPolicyFCA = "https://www.fortrade.com/wp-content/uploads/legal/Fortrade_Privacy_Policy.pdf" ;
public String privacyPolicyIiroc = "https://www.fortrade.com/wp-content/uploads/legal/IIROC/Privacy_Policy.pdf" ;
public String privacyPolicyCysec = "https://www.fortrade.com/wp-content/uploads/legal/CySEC/Privacy_Policy.pdf" ;

    public String setRegulation(String regulation){
        String text = "";
        switch (regulation){
            case "FSC": {
                text = "fortrade-ma-privacy-policy/";
            }
                break;
            case "FCA": {
                text = "wp-content/uploads/legal/Fortrade_Privacy_Policy.pdf";
            }
            break;
            case "iiroc": {
                text = "wp-content/uploads/legal/IIROC/Privacy_Policy.pdf";
            }
            break;
            case "cysec": {
                text = "wp-content/uploads/legal/CySEC/Privacy_Policy.pdf";
            }
            break;
        }
        String privacyPolicy = "https://www.fortrade.com/" + text;
        return privacyPolicy;
    }

    // Terms and conditions document link - all regulation
public String termsAndConditionsFSC = "https://www.fortrade.com/fortrade-mauritius-client-agreement/";
public String termsAndConditionsFCA = "https://www.fortrade.com/wp-content/uploads/legal/Fortrade_Terms_and_Conditions.pdf";
public String termsAndConditionsIiroc = "https://www.fortrade.com/wp-content/uploads/legal/IIROC/Client_Agreement.pdf";
public String termsAndConditionsCysec = "https://www.fortrade.com/wp-content/uploads/legal/CySEC/Client_Agreement.pdf";

    /*How to unsubscribe document link - same for all regulations.
    Iiroc regulation doesn't have the How to unsubscribe document*/
public String howToUnsubscribe = "https://www.fortrade.com/wp-content/uploads/legal/How_to_guides/How_to_unsubscribe.pdf";

    // Already have an account
public String alrHaveAccount= "https://ready.fortrade.com/?lang=en#login";

    // Risk Warning document link - all regulations
public String riskWarningFSC = "https://www.fortrade.com/wp-content/uploads/legal/FSC/Fortrade_MA_Risk_Disclosure.pdf";
public String riskWarningFCA = "https://www.fortrade.com/wp-content/uploads/legal/Fortrade_Risk_Disclosure.pdf";
public String riskWarningIiroc = "https://www.fortrade.com/wp-content/uploads/legal/IIROC/Relationship_Disclosure.pdf";
public String riskWarningCysec = "https://www.fortrade.com/wp-content/uploads/legal/CySEC/Risk_Disclosure.pdf";
public String riskWarningAsic = "https://www.fortrade.com/wp-content/uploads/legal/ASIC/Fort_Securities_AU_Product_Disclosure_Statement-ASIC.pdf";

    // Privacy policy document link in the footer - FSC and Asic regulations;
public String privacyPolicyFooterFSC = "https://www.fortrade.com/wp-content/uploads/legal/FSC/Fortrade_MA_Privacy_Policy.pdf";
public String privacyPolicyFooterAsic = "https://www.fortrade.com/wp-content/uploads/legal/ASIC/Fort_Securities_AU_Privacy_Policy-ASIC.pdf";

    // Financial Conduct Authority (FCA) link
public String fcaLink = "https://register.fca.org.uk/s/firm?id=001b000000NMdUwAAL";

    // Canadian Investment Regulatory Organization (CIRO) link
public String iirocLink = "https://www.ciro.ca/investors/choosing-investment-advisor/dealers-we-regulate/fortrade-canada-limited";

    // Australian Securities and Investments Commission (ASIC) link
public String asicLink = "https://connectonline.asic.gov.au/RegistrySearch/faces/landing/panelSearch.jspx?searchType=OrgAndBusNm&searchText=614683831";

    // Cyprus Securities and Exchange Commission (CySEC) link
public String cysecLink = "https://www.cysec.gov.cy/en-GB/entities/investment-firms/cypriot/86639/";

    // Financial Services Commission, Mauritius (FSC) link
public String fscLink = "https://www.fscmauritius.org/en/supervision/register-of-licensees/register-of-licensees-details?licence_no=GB21026472&key=&cat=_GB&code=";

    // Asic regulation - financial service guide document link
public String fsgDocumentLink = "https://www.fortrade.com/wp-content/uploads/legal/ASIC/Fort_Securities_AU_Financial_Services_Guide-ASIC.pdf";

    // Asic regulation - product disclosure statement document link
public String pdsDocumentLink = "https://www.fortrade.com/wp-content/uploads/legal/ASIC/Fort_Securities_AU_Product_Disclosure_Statement-ASIC.pdf";

    // Asic regulation - target market determination document link
public String tmdDeterminationLink = "https://www.fortrade.com/wp-content/uploads/legal/ASIC/Fort_Securities_AU-TMD_Policy.pdf";

    public void enterFirstName(String firstNameData) {
        typeText(firstName, firstNameData, "first name");
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

    public void clickDenyBtn() {
        clickElement(denyBtn, "deny cookies button");
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

    public void assertURL(String url) {
        WebDriverWait wait = new WebDriverWait(driver, waitTime);
        wait.until(ExpectedConditions.urlContains(url));
        Assert.assertEquals(driver.getCurrentUrl(), url);
    }

    public void clickContinueBtn() {
        clickElement(continueBtn, "continue button");
    }

    public void clickMenuBtn() {
        clickElement(menuBtn, "menu button");
    }

    public void checkRegulation(String regulation) throws IOException, AWTException {
        String actualText = getText(regulationMsg, "regulation text");
        clickDenyBtn();
        switch (regulation) {
            case "FCA": {
                Assert.assertEquals(actualText, "Broker: Fortrade Ltd. (FCA)");
                new BasePage(driver).takeScreenshot("Broker Fortrade Ltd FCA - successfully registered demo account", regulationMsg);
            }
            break;
            case "cyses": {
                Assert.assertEquals(actualText, "Broker: Fortrade Cyprus Ltd. (CySec)");
                new BasePage(driver).takeScreenshot("Broker Fortrade Cyprus Ltd CySec - successfully registered demo account", regulationMsg);
            }
            break;
            case "Asic": {
                Assert.assertEquals(actualText, "Broker: Fort Securities Australia Pty Ltd. (ASIC)");
                new BasePage(driver).takeScreenshot("Broker Fort Securities Australia Pty Ltd ASIC - successfully registered demo account", regulationMsg);
            }
            break;
            case "iiroc": {
                Assert.assertEquals(actualText, "Broker: Fortrade Canada Limited (CIRO)");
                new BasePage(driver).takeScreenshot("Broker Fortrade Canada Limited CIRO - successfully registered demo account", regulationMsg);
            }
            break;
            case "FSC":
            default: {
                Assert.assertEquals(actualText, "Broker: Fortrade (Mauritius) Ltd (FSC)");
                new BasePage(driver).takeScreenshot("Broker Fortrade Mauritius Ltd FSC - successfully registered demo account", regulationMsg);
            }
            break;
        }
    }

    public void alreadyRegisteredAccount(String firstNameData, String lastNameData, String emailData, String countryCodeData, String phoneNumberData) {
        enterFirstName(firstNameData);
        enterLastName(lastNameData);
        enterEmail(emailData);
        enterCountryCode(countryCodeData);
        enterPhoneNumber(phoneNumberData);
        clickOnSubmitButton();
    }

    private String expTextForPopUp = "Invalid email. Please try another or proceed to log in. If needed, reset your password in case it's forgotten.";

    public void assertPopUpForAlreadyRegisteredAccount(String fileName) throws IOException, AWTException {
        Assert.assertEquals(getTextBy(alrdRegEmailPopUp, "alrdRegEmailPopUp"), expTextForPopUp);
        new BasePage(driver).takeScreenshot(fileName, alrdRegEmailPopUp);
    }

    public void clickConsentBtn() throws InterruptedException {
        driver.switchTo().frame(iFrameIConsent);
        try {
            clickElement(iConsentBtn, "I Consent button");
        } catch (Exception e) {
            System.out.println(e);
        }
        driver.switchTo().defaultContent();
        Thread.sleep(2500);
    }

    public void unsuccessfullyRegistrationWrongData(String firstNameData, String lastNameData, String emailData, String countryCode, String phoneNumberData) {
        enterFirstName(firstNameData);
        enterLastName(lastNameData);
        enterEmail(emailData);
        enterCountryCode(countryCode);
        enterPhoneNumber(phoneNumberData);
        clickOnSubmitButton();
    }

    public void assertErrorMessages() {
        for (int i = 1; i <= 4; i++) {
            Assert.assertEquals(getTextBy(By.xpath("(//div[@class='errorValidationIn'])[position()=number]".replace("number", String.valueOf(i))), "error message " + errorMessages[i - 1]), errorMessages[i - 1]);
        }
    }

    public void assertSameNameErrorMsgs(){
        for (int i = 1; i <=2 ; i++ ){
            Assert.assertEquals(getTextBy(By.xpath("(//div[@class='errorValidationIn'])[position()=number]".replace("number",String.valueOf(i))),"Error message : " + sameNamesErrorMessages[i-1]), sameNamesErrorMessages[i-1]);
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
                System.out.println("This is the border color of " + fields[i].getAttribute("name") + "field: " + borderColor);
                Assert.assertTrue(green < 200 && red > 50 && red < 120 && blue > 50 && blue < 100, "Border color is not approximately green.");
            }
        }
    }
    public void checkLogoClickability(String regulation, String url){
        if (regulation.equalsIgnoreCase("fsc") || regulation.equalsIgnoreCase("fca")
        || regulation.equalsIgnoreCase("asic")){
                WebDriverWait driverWait = new WebDriverWait(driver, 10);
                driverWait.until(ExpectedConditions.visibilityOf(logo));
                try {
                    logo.click();
                    System.out.println("Log is not clickable.");
                } catch (Exception e){
                    System.out.println("Logo is not clickable, as expected.");
                }
                assertURL(url);
                List<String> tabs = new ArrayList<>(driver.getWindowHandles());
                Assert.assertEquals(tabs.size(), 1);
        } else if (regulation.equalsIgnoreCase("iiroc")){
            WebDriverWait driverWait = new WebDriverWait(driver, 10);
            driverWait.until(ExpectedConditions.visibilityOf(logoIiroc));
            try {
                logoIiroc.click();
                System.out.println("Log is not clickable.");
            } catch (Exception e){
                System.out.println("Logo is not clickable, as expected.");
            }
            assertURL(url);
            List<String> tabs = new ArrayList<>(driver.getWindowHandles());
            Assert.assertEquals(tabs.size(), 1);
        } else if (regulation.equalsIgnoreCase("cysec")){
            WebDriverWait driverWait = new WebDriverWait(driver, 10);
            driverWait.until(ExpectedConditions.visibilityOf(logoCysec));
            try {
                logoCysec.click();
                System.out.println("Log is not clickable.");
            } catch (Exception e){
                System.out.println("Logo is not clickable, as expected.");
            }
            assertURL(url);
            List<String> tabs = new ArrayList<>(driver.getWindowHandles());
            Assert.assertEquals(tabs.size(), 1);
        }
    }

    public void checkCountryCodeErrorMessage(String wrongCountryCodeDataText) {
        WebDriverWait driverWait = new WebDriverWait(driver, 10);
        driverWait.until(ExpectedConditions.visibilityOf(popUpNotification));
        enterCountryCode(wrongCountryCodeDataText);
        clickElement(phoneNumber, "phone number field");
        Assert.assertEquals(getTextBy(countryCodeErrorMessage, "country code error message: " + countryCodeErrorMessage.getText())
                , "Please enter a valid country code");
    }
    public void clickOnSelectedLink(By element, String url, String regulation) throws IOException, AWTException {
        WebElement displayedElement = returnDisplayedElement(element);
        if (displayedElement != null) {
            clickElement(displayedElement, "link " + displayedElement.getText());
        } else {
            System.out.println("Element is not found!");
        }
        List<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
        assertURL(url);
        takeScreenshot("Privacy Policy document - " + regulation + " regulation");
        driver.close();
        driver.switchTo().window(tabs.get(0));
    }

    public void rightClickOnSelectedLink(By element, String url) {
        performRightClick(returnDisplayedElement(element), url, "link" + returnDisplayedElement(element).getText());
    }
}
