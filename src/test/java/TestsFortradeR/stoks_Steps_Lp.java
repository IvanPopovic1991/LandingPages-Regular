package TestsFortradeR;

import Pages.CrmPage;
import Pages.FortradeRPage;
import Pages.Mailinator;
import faker.TestData;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;


public class stoks_Steps_Lp extends BaseTestFortradeR {


    @BeforeMethod
    public void setUp() {
        baseSetup("Chrome", "133");
        FortradeRPage fortradeRPage = new FortradeRPage(driver);
        fortradeRPage.clickStartBtn();
    }

    @AfterMethod
    public void tearDown() {
        baseTearDown();
    }

    @Test
    public void urlAssert() throws IOException, AWTException {
        FortradeRPage fortradeRPage = new FortradeRPage(driver);
        fortradeRPage.assertURL("https://www.fortrader.com/minilps/en/stocks-steps/");
        //last element uploaded - input field with attribute value='quick'
        String quickValue = driver.findElement(By.xpath("//form[@class='LC-QuickRegistrationMultiTraderWithVerify" +
                "PhoneWidget']/input[@value='quick']")).getAttribute("value");
        Assert.assertEquals("quick", quickValue);
        fortradeRPage.takeScreenshot("Page url - FortradeR - verified");
    }

    @Test
    public void demoAccountRegistration() throws IOException, AWTException {
        String email = TestData.emailGenerator();
        String phoneNumber = TestData.phoneNumberGenerator();
        FortradeRPage fortradeRPage = new FortradeRPage(driver);
        fortradeRPage.successfullyRegistration("Testq", "Testa", email, "381", phoneNumber);
        fortradeRPage.assertURL("https://ready.fortrade.com/#chartticket");
        fortradeRPage.clickDenyBtn();
        fortradeRPage.clickIAmNotSerBtn();
        fortradeRPage.clickMenuBtn();
        fortradeRPage.checkRegulation();
        fortradeRPage.takeScreenshot("Demo account registration - FortradeR");
    }

    @Test
    public void unsuccessfullyDemoAccountRegistration() throws IOException, AWTException {
        FortradeRPage fortradeRPage = new FortradeRPage(driver);
        fortradeRPage.unsuccessfullyRegistrationWithWrongData("123", "456", "345342=--=/.,><",
                "123456", "1234567890123456");
        fortradeRPage.assertErrorMessages();
        fortradeRPage.assertColor("red");
        fortradeRPage.takeScreenshot("Unsuccessfully demo account registration - FortradeR", fortradeRPage.submitButton);
    }

    @Test
    public void emptyDemoAccountRegistration() throws IOException, AWTException {
        FortradeRPage fortradeRPage = new FortradeRPage(driver);
        fortradeRPage.unsuccessfullyRegistrationWithWrongData("", "", "", "", "");
        fortradeRPage.assertErrorMessages();
        fortradeRPage.assertColor("red");
        fortradeRPage.takeScreenshot("Demo account registration - no data - Fortrader", fortradeRPage.submitButton);
    }

    @Test
    public void alreadyRegisteredAccountTest() throws IOException, AWTException {
        String email = TestData.emailGenerator();
        String phoneNumber = TestData.phoneNumberGenerator();
        FortradeRPage fortradeRPage = new FortradeRPage(driver);
        fortradeRPage.successfullyRegistration("Testq", "Testa", email, "381", phoneNumber);
        driver.get("https://www.fortrader.com/minilps/en/stocks-steps/");
        fortradeRPage.alreadyRegisteredAccount("Testq", "Testa", email, "381", phoneNumber);
        fortradeRPage.assertPopUpForAlreadyRegisteredAccount("Already registered account - FortradeR - pop-up");
    }

    @Test
    public void checkingTagsInTheCrm() throws IOException, AWTException {
        String email = TestData.emailGenerator();
        String phoneNumber = TestData.phoneNumberGenerator();
        driver.get("https://www.fortrader.com/minilps/en/stocks-steps/?tg=ivanA1389&tag1=ivanB@1389&tag2=ivanL1389&" +
                "tag3=ivanM1389&gid=ivanC@1389&G_GEO=ivanD1389&G_GEOint=ivanE1389&G_Device=ivanF1389&G_DeviceModel=" +
                "ivanG1389&G_AdPos=ivanH1389&g_Track=ivanI1389&Track=ivanj1389&gclid=ivanK1389");
        FortradeRPage fortradeRPage = new FortradeRPage(driver);
        fortradeRPage.successfullyRegistration("Testq", "Testa", email, "381", phoneNumber);
        String urlCrm = System.getenv("URLForCrm");
        String usernameCrm = System.getenv("UsernameForCrm");
        String passwordCrm = System.getenv("PasswordForCrm");
        driver.get(urlCrm);
        CrmPage crmPage = new CrmPage(driver);
        crmPage.checkCrmData(usernameCrm, passwordCrm, email, "Testq Testa", "FSC");
        crmPage.takeScreenshot("Account details Fortrader page", crmPage.accFullNameCrm);
        crmPage.checkCrmTags();
        crmPage.takeScreenshot("Marketing tags Fortrader page", crmPage.accFullNameCrm);
    }

    @Test
    public void sameFNameAndLName() throws IOException, AWTException {
        FortradeRPage fortradeRPage = new FortradeRPage(driver);
        fortradeRPage.enterFirstName("Test");
        fortradeRPage.enterLastName("Test");
        fortradeRPage.clickElement(fortradeRPage.firstName, "on first name field");
        fortradeRPage.clickElement(fortradeRPage.lastName, "on last name field");
        fortradeRPage.assertSameNameErrorMsgs();
        fortradeRPage.takeScreenshot("Error messages for the same first and last name - FortradeR");
    }

    @Test
    public void checkingForLogoClickability() throws IOException, AWTException {
        FortradeRPage fortradeRPage = new FortradeRPage(driver);
        fortradeRPage.checkLogoClickability("https://www.fortrader.com/minilps/en/stocks-steps/");
        fortradeRPage.takeScreenshot("Logo is not clickable - FortradeR");
    }

    @Test
    public void checkForCountryCodeErrorMessage() throws IOException, AWTException {
        FortradeRPage fortradeRPage = new FortradeRPage(driver);
        fortradeRPage.checkCountryCodeErrorMessage("01852833kdkd");
        fortradeRPage.takeScreenshot("Country code error message - FortradeR");
    }

    @Test
    public void emailIsReceivedSuccessfully() throws IOException, AWTException {
        String email = TestData.emailGenerator();
        String phoneNumber = TestData.phoneNumberGenerator();
        FortradeRPage fortradeRPage = new FortradeRPage(driver);
        fortradeRPage.successfullyRegistration("Testq", "Testa", email, "381", phoneNumber);
        driver.get("https://www.mailinator.com/");
        Mailinator mailinator = new Mailinator(driver);
        mailinator.findEmail(email);
        mailinator.zoomOutMethod();
        mailinator.takeScreenshot("Email is received successfully - FortradeR", mailinator.emailTitle);
    }

    @Test
    public void privacyPolicyTest() throws IOException, AWTException, InterruptedException {
        FortradeRPage fortradeRPage = new FortradeRPage(driver);
        fortradeRPage.clickOnSelectedLink(fortradeRPage.privacyPolicyLinkBy, fortradeRPage.privacyPolicyFSC,
                "Privacy policy");
        fortradeRPage.rightClickOnSelectedLink(fortradeRPage.privacyPolicyLinkBy, fortradeRPage.privacyPolicyFSC);
    }

    @Test
    public void termsAndConditionsTest() throws IOException, AWTException, InterruptedException {
        FortradeRPage fortradeRPage = new FortradeRPage(driver);
        fortradeRPage.clickOnSelectedLink(fortradeRPage.termsAndConditionsLinkBy, fortradeRPage.termsAndConditionsFSC,
                "Terms and conditions");
        fortradeRPage.rightClickOnSelectedLink(fortradeRPage.termsAndConditionsLinkBy, fortradeRPage.termsAndConditionsFSC);
    }

    @Test
    public void howToUnsubscribeTest() throws IOException, AWTException, InterruptedException {
        FortradeRPage fortradeRPage = new FortradeRPage(driver);
        fortradeRPage.clickOnSelectedLink(fortradeRPage.clickHereLinkBy, fortradeRPage.howToUnsubscribeURL,
                "How to unsubscribe");
        fortradeRPage.rightClickOnSelectedLink(fortradeRPage.clickHereLinkBy, fortradeRPage.howToUnsubscribeURL);
    }

    @Test
    public void footerPrivacyPolicy() throws IOException, InterruptedException, AWTException {
        FortradeRPage fortradeRPage = new FortradeRPage(driver);
        fortradeRPage.scrollToAnElement(driver.findElement(By.xpath("//div[@class='fscClass']//a[contains" +
                "(text(), 'Privacy policy')]")));
        fortradeRPage.clickOnSelectedLink(fortradeRPage.footerPrivacyPolicyLinkBy, fortradeRPage.privacyPolicyFSCFooter,
                "Privacy policy footer - FortradeR");
        fortradeRPage.rightClickOnSelectedLink(fortradeRPage.footerPrivacyPolicyLinkBy, fortradeRPage.privacyPolicyFSCFooter);
    }

    @Test
    public void fscRegulationTest() throws IOException, InterruptedException, AWTException {
        FortradeRPage fortradeRPage = new FortradeRPage(driver);
        fortradeRPage.scrollToAnElement(driver.findElement(By.xpath("//a[text()=' GB21026472']")));
        fortradeRPage.clickOnSelectedLink(fortradeRPage.fscRegulationLinkBy, fortradeRPage.fscLink,
                "Financial Services Commission page");
        fortradeRPage.scrollToAnElement(driver.findElement(By.xpath("//a[text()=' GB21026472']")));
        fortradeRPage.rightClickOnSelectedLink(fortradeRPage.fscRegulationLinkBy, fortradeRPage.fscLink);
    }

    @Test
    public void infoLink() throws IOException, AWTException, InterruptedException {
        FortradeRPage fortradeRPage = new FortradeRPage(driver);
        fortradeRPage.clickOnMailLink("info");
        Thread.sleep(1000);
        fortradeRPage.takeScreenshot("FortradeR - info redirection");
        fortradeRPage.closeOutlook();
    }

    @Test
    public void supportLink() throws IOException, AWTException, InterruptedException {
        FortradeRPage fortradeRPage = new FortradeRPage(driver);
        fortradeRPage.clickOnMailLink("support");
        Thread.sleep(1000);
        fortradeRPage.takeScreenshot("FortradeR - support redirection");
        fortradeRPage.closeOutlook();
    }
}
