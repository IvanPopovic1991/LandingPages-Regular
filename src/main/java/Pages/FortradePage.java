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

    @FindBy(xpath = "//div[contains(text(),'Login')]")
    public WebElement loginToFotrade;

    public By privacyPolicyLinkBy = By.xpath("//div[@class='form-wrapper']//a[text()='Privacy Policy']");

    public By termsAndConditionsLinkBy = By.xpath("//div[@class='form-wrapper']//a[contains(text(), 'Terms and Conditions')]");

    public By clickHereLink = By.xpath("//div[@class='MarketingMaterials2']//a[text()='click here']");

    public By alreadyHaveAnAccountLinkBy = By.xpath("//*[@class='alreadyHaveAcc']//a[contains(text(), 'Already have an account?')]");

    public By contactUsLinkBy = By.xpath("//*[@class='needHelp']//a[contains(text(), 'Contact Us')]");

    public By facebookLinkBy = By.xpath("//a[@class='facebook-links']");

    public By instagramLinkBy = By.xpath("//a[@href='https://www.instagram.com/fortrade_online_trading/?hl=en']");

    public By youtubeLinkBy = By.xpath("//a[@href='https://www.youtube.com/channel/UCNCrGhrDTEN1Hx_20-kFxwg']");

    public By supportLinkBy = By.xpath("//a[text()='support@fortrade.com']");

    public By footerRiskWarningLinkBy = By.xpath("//div[@class='footerRiskDisclaimer']//a[contains(text(), 'Risk warning')]");

    public By footerPrivacyPolicyLinkBy = By.xpath("//div[@class='footerRiskDisclaimer']//a[contains(text(), 'Privacy policy')]");

    public By fcaRegulationLinkBy = By.xpath("//a[text()='FRN: 609970']");

    public By ciroRegulationLinkBy = By.xpath("//a[text()='CRN: BC1148613']");

    public By asicRegulationLinkBy = By.xpath("//a[text()='ABN: 33 614 683 831 | AFSL: 493520']");

    public By cysecRegulationLinkBy = By.xpath("//a[text()='CIF license number 385/20']");

    public By fscRegulationLinkBy = By.xpath("//a[text()=' GB21026472']");

    public By fsgDocument = By.xpath("//div[@class='footerRiskDisclaimer']//div[@class='asicClass']//a[contains(text(),'(FSG)')]");

    public By pdsDocument = By.xpath("//div[@class='footerRiskDisclaimer']//div[@class='asicClass']//a[contains(text(),'(PDS)')]");

    public By tmdDocument = By.xpath("//div[@class='footerRiskDisclaimer']//div[@class='asicClass']//a[contains(text(),'(TMD)')]");

    String[] errorMessages = {"Please enter all your given first name(s)",
            "Please enter your last name in alphabetic characters",
            "Invalid email format.",
            "Invalid phone format."};

    String[] sameNamesErrorMessages = {"Your first name must be different from your last name",
            "Your first name must be different from your last name"};

    public String setRegulation(String regulation) {
        String text = "";
        switch (regulation) {
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

    public String termsAndCondition(String regulation) {
        String text = "";
        switch (regulation) {
            case "FSC": {
                text = "fortrade-mauritius-client-agreement/";
            }
            break;
            case "FCA": {
                text = "wp-content/uploads/legal/Fortrade_Terms_and_Conditions.pdf";
            }
            break;
            case "iiroc": {
                text = "wp-content/uploads/legal/IIROC/Client_Agreement.pdf";
            }
            break;
            case "cysec": {
                text = "wp-content/uploads/legal/CySEC/Client_Agreement.pdf";
            }
            break;
        }
    String termsAndConditions = "https://www.fortrade.com/"+text;
    return termsAndConditions;
    }

    public String riskWarning(String regulation){
        String text = "";
        switch (regulation){
            case("FSC") : {
                text = "FSC/Fortrade_MA_Risk_Disclosure.pdf";
            }break;
            case("FCA"):{
                text = "Fortrade_Risk_Disclosure.pdf";
            }break;
            case("iiroc"):{
                text = "IIROC/Relationship_Disclosure.pdf";
            }break;
            case("cysec"):{
                text = "CySEC/Risk_Disclosure.pdf";
            }break;
            case("Asic"):{
                text = "ASIC/Fort_Securities_AU_Product_Disclosure_Statement-ASIC.pdf";
            }break;
        }
        String riskWarningURL = "https://www.fortrade.com/wp-content/uploads/legal/"+text;
        return riskWarningURL;
    }

    public String footerPrivacyPolicy(String regulation){
        String text="";
        switch (regulation){
            case "FSC":{
                text = "FSC/Fortrade_MA_Privacy_Policy.pdf";
            }break;
            case "FCA":{
                text="Fortrade_Privacy_Policy.pdf";
            }break;
            case "Asic":{
                text = "ASIC/Fort_Securities_AU_Privacy_Policy-ASIC.pdf";
            }break;
            case "cysec":{
                text ="CySEC/Privacy_Policy.pdf";
            }break;
            case "iiroc":{
                text ="IIROC/Privacy_Policy.pdf";
            }break;
        }
        String privacyPolicyFooterURL = "https://www.fortrade.com/wp-content/uploads/legal/" + text;
        return privacyPolicyFooterURL;
    }

    public String fbPage(String regulation){
        String text="";
        switch (regulation){
            case ("FSC"):{
                text = "Fortrade.International";
            }break;
            case ("FCA"):{
                text = "Fortrade.UK/";
            }break;
            case ("Asic"):{
                text = "Fortrade.AU";
            }break;
            case ("iiroc"):{
                text = "Fortrade.International";
            }break;
            case ("cysec"):{
                text = "Fortrade.EU";
            }break;
        }
        String fbURL = "https://www.facebook.com/" +text;
        return fbURL;
    }

    /*How to unsubscribe document link - same for all regulations.
    Iiroc regulation doesn't have the How to unsubscribe document*/
    public String howToUnsubscribeURL = "https://www.fortrade.com/wp-content/uploads/legal/How_to_guides/How_to_unsubscribe.pdf";

    // Already have an account
    public String alrHaveAccount = "https://ready.fortrade.com/";

    // Financial Conduct Authority (FCA) link
    public String fcaLink = "https://register.fca.org.uk/s/firm?id=001b000000NMdUwAAL";

    // Canadian Investment Regulatory Organization (CIRO) link
    public String iirocLink = "https://www.ciro.ca/investors/choosing-investment-advisor/dealers-we-regulate/fortrade-canada-limited";

    // Australian Securities and Investments Commission (ASIC) link
    public String asicLink = "https://asic.gov.au/online-services/service-availability/";

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

    public String ytURL = "https://www.youtube.com/channel/UCNCrGhrDTEN1Hx_20-kFxwg";

    public String fbURL = "https://www.facebook.com/";

    public String insURL = "https://www.instagram.com/fortrade_online_trading/?hl=en";

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

    public void assertSameNameErrorMsgs() {
        for (int i = 1; i <= 2; i++) {
            Assert.assertEquals(getTextBy(By.xpath("(//div[@class='errorValidationIn'])[position()=number]".replace("number", String.valueOf(i))), "Error message : " + sameNamesErrorMessages[i - 1]), sameNamesErrorMessages[i - 1]);
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

    public void checkLogoClickability(String regulation, String url) {
        if (regulation.equalsIgnoreCase("fsc") || regulation.equalsIgnoreCase("fca")
                || regulation.equalsIgnoreCase("asic")) {
            WebDriverWait driverWait = new WebDriverWait(driver, 10);
            driverWait.until(ExpectedConditions.visibilityOf(logo));
            try {
                logo.click();
                System.out.println("Log is not clickable.");
            } catch (Exception e) {
                System.out.println("Logo is not clickable, as expected.");
            }
            assertURL(url);
            List<String> tabs = new ArrayList<>(driver.getWindowHandles());
            Assert.assertEquals(tabs.size(), 1);
        } else if (regulation.equalsIgnoreCase("iiroc")) {
            WebDriverWait driverWait = new WebDriverWait(driver, 10);
            driverWait.until(ExpectedConditions.visibilityOf(logoIiroc));
            try {
                logoIiroc.click();
                System.out.println("Log is not clickable.");
            } catch (Exception e) {
                System.out.println("Logo is not clickable, as expected.");
            }
            assertURL(url);
            List<String> tabs = new ArrayList<>(driver.getWindowHandles());
            Assert.assertEquals(tabs.size(), 1);
        } else if (regulation.equalsIgnoreCase("cysec")) {
            WebDriverWait driverWait = new WebDriverWait(driver, 10);
            driverWait.until(ExpectedConditions.visibilityOf(logoCysec));
            try {
                logoCysec.click();
                System.out.println("Log is not clickable.");
            } catch (Exception e) {
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

    public void clickOnSelectedLink(By element, String url,String document, String regulation) throws IOException, AWTException, InterruptedException {
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
        takeScreenshot(document +" document - " + regulation + " regulation");
        WebDriverWait wait = new WebDriverWait(driver, 10);
        /*if (!regulation.equalsIgnoreCase("FSC")){
*//*
            wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//body/embed[@original-url='https://www.fortrade.com/wp-content/uploads/legal/Fortrade_Privacy_Policy.pdf']")));
*//*
            Assert.assertTrue(readAttribute(driver.findElement(By.xpath("//body/embed[@original-url='https://www.fortrade.com/wp-content/uploads/legal/Fortrade_Privacy_Policy.pdf']")), "original-url", "attribute url").equals("https://www.fortrade.com/wp-content/uploads/legal/Fortrade_Privacy_Policy.pdf"));
        }*/
        System.out.println("This is the title of the page: " + driver.getTitle());
        //takeScreenshot("Privacy Policy document - " + regulation + " regulation");
        driver.close();
        driver.switchTo().window(tabs.get(0));
    }

    public void rightClickOnSelectedLink(By element, String url) {
        performRightClick(returnDisplayedElement(element), url, "link " + returnDisplayedElement(element).getText());
    }

    public void loginRedirection(String regulation) throws IOException, AWTException {
        clickElement(driver.findElement(alreadyHaveAnAccountLinkBy),"Already have an account?");
        //takeScreenshot("Login widget - " + regulation + " regulation");
    }
/*    public void clickOnMailLink(String mailLink){
        if (mailLink.equalsIgnoreCase("contactUs")){
            clickElementBy(contactUsLinkBy, "contact us link");
        } else if (mailLink.equalsIgnoreCase("support")){
            clickElementBy(supportLinkBy, "support link");
        }
        Assert.assertTrue(isOutlookRunning());
    }*/
public void clickOnMailLink(String mailLink){
    if (mailLink.equalsIgnoreCase("contactUs")){
        clickElement(returnDisplayedElement(contactUsLinkBy), "contact us link");
    } else if (mailLink.equalsIgnoreCase("support")){
        clickElement(returnDisplayedElement(supportLinkBy), "support link");
    }
    Assert.assertTrue(isOutlookRunning());
}
}
