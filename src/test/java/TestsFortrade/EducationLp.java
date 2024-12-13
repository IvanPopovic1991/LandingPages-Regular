package TestsFortrade;

import Pages.CrmPage;
import Pages.FortradePage;
import Pages.FortradeRPage;
import Pages.Mailinator;
import faker.TestData;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;

public class EducationLp extends BaseTestFortrade {

    @Test
    @Parameters({"regulation", "tag"})
    @BeforeMethod
    public void setUp(String regulation, String tag) {
        baseSetup("Chrome", "131");
        driver.get("https://www.fortrade.com/minilps/en/education/" + tag);
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(webDriver -> ((JavascriptExecutor) webDriver)
                .executeScript("return document.readyState").equals("complete"));
//      JavascriptExecutor js = (JavascriptExecutor) driver;
//      js.executeScript("arguments[0].setAttribute('data-lcreg', arguments[1]);",
//      driver.findElement(By.xpath("//body[@data-lcreg='FSC']")), regulation);
        //https://www.fortrade.com/minilps/en/pro-dark-2024-dlp/?tg=skip&G_GEO=1006453&regulation=FCA&fts=age-annual-saving-knowledge
    }

    @AfterMethod
    public void tearDown() {
        baseTearDown();
    }

    @Test
    @Parameters({"regulation", "tag"})
    public void urlAssert(String regulation, String tag) throws IOException, AWTException {
        FortradePage fortradePage = new FortradePage(driver);
        if (fortradePage.bodyTag.getAttribute("data-lcreg").contains(regulation)) {
            fortradePage.assertURL("https://www.fortrade.com/minilps/en/education/" + tag);
        }
        //last element uploaded - input field with attribute value='quick'
        String quickValue = driver.findElement(By.xpath("//form[@class='LC-QuickRegistrationMultiTraderWithVerify" +
                "PhoneWidget']/input[@value='quick']")).getAttribute("value");
        Assert.assertEquals("quick", quickValue);

        if (fortradePage.logo.isDisplayed()) {
            fortradePage.takeScreenshot("Page url - " + regulation + " - verified", fortradePage.logo);
        } else if (fortradePage.logoIiroc.isDisplayed()) {
            fortradePage.takeScreenshot("Page url - " + regulation + " - verified", fortradePage.logoIiroc);
        } else {
            fortradePage.takeScreenshot("Page url - " + regulation + " - verified", fortradePage.logoCysec);
        }
    }

    @Test
    @Parameters({"regulation", "countryCode"})
    public void demoAccountRegistration(String regulation, String countryCode) throws IOException, AWTException, InterruptedException {
        String email = TestData.emailGenerator();
        String phoneNumber = TestData.phoneNumberGenerator();
        FortradePage fortradePage = new FortradePage(driver);
        fortradePage.successfullyRegistration("Testq", "Testa", email, countryCode, phoneNumber);
        fortradePage.assertURL("https://ready.fortrade.com/#chartticket");
        fortradePage.clickContinueBtn();
        if (regulation.equals("Asic")) {
            fortradePage.clickConsentBtn();
        }
        fortradePage.clickMenuBtn();
        fortradePage.checkRegulation(regulation);
    }

    @Test
    @Parameters({"countryCode", "regulation"})
    public void alreadyRegisteredAccountTest(String countryCode, String regulation) throws IOException, AWTException {
        String email = TestData.emailGenerator();
        String phoneNumber = TestData.phoneNumberGenerator();
        FortradePage fortradePage = new FortradePage(driver);
        fortradePage.successfullyRegistration("Testq", "Testa", email, countryCode, phoneNumber);
        driver.get("https://www.fortrade.com/minilps/en/education/");
        fortradePage.alreadyRegisteredAccount("Testq", "Testa", email, countryCode, phoneNumber);
        fortradePage.assertPopUpForAlreadyRegisteredAccount("Already registered account - pop-up " + regulation);
    }

    @Test
    @Parameters({"regulation", "countryCode"})
    public void checkingTagsInTheCRM(String regulation, String countryCode) throws IOException, AWTException {
        String email = TestData.emailGenerator();
        String phoneNumber = TestData.phoneNumberGenerator();
        driver.get("https://www.fortrade.com/minilps/en/education/?tg=ivanA1389&tag1=ivanB@1389&tag2=ivanL1389&tag3=" +
                "ivanM1389&gid=ivanC@1389&G_GEO=ivanD1389&G_GEOint=ivanE1389&G_Device=ivanF1389&G_DeviceModel=ivanG1389" +
                "&G_AdPos=ivanH1389&g_Track=ivanI1389&Track=ivanj1389&gclid=ivanK1389");
        FortradePage fortradePage = new FortradePage(driver);
        fortradePage.successfullyRegistration("Testq", "Testa", email, countryCode, phoneNumber);
        String urlCRM = System.getenv("URLForCrm");
        String usernameCRM = System.getenv("UsernameForCrm");
        String passwordCRM = System.getenv("PasswordForCrm");
        driver.get(urlCRM);
        CrmPage crmPage = new CrmPage(driver);
        crmPage.checkCrmData(usernameCRM, passwordCRM, email, "Testq Testa", regulation);
        crmPage.takeScreenshot("Account Details " + regulation + " regulation", crmPage.accFullNameCrm);
        crmPage.checkCrmTags();
        crmPage.takeScreenshot("Marketing Tags " + regulation + " regulation", crmPage.marketingSection);
    }

    @Test
    @Parameters({"regulation"})
    public void unsuccessfullyDemoAccountRegistration(String regulation) throws IOException, AWTException {
        FortradePage fortradePage = new FortradePage(driver);
        fortradePage.unsuccessfullyRegistrationWrongData("12/*", "+-*5", "test#123", "123456", "/*-+");
        fortradePage.assertErrorMessages();
        fortradePage.assertColor("red");
        fortradePage.takeScreenshot("Unsuccessfully demo account registration " + regulation + " regulation", fortradePage.firstName);
    }

    @Test
    @Parameters({"regulation"})
    public void emptyDemoAccountRegistration(String regulation) throws IOException, AWTException {
        FortradePage fortradePage = new FortradePage(driver);
        fortradePage.unsuccessfullyRegistrationWrongData("", "", "", "", "");
        fortradePage.assertErrorMessages();
        fortradePage.assertColor("red");
        fortradePage.takeScreenshot("Demo account registration - no data " + regulation + " regulation", fortradePage.submitButton);
    }

    @Test
    @Parameters({"regulation"})
    public void sameFNameAndLName(String regulation) throws IOException, AWTException {
        FortradePage fortradePage = new FortradePage(driver);
        fortradePage.enterFirstName("Test");
        fortradePage.enterLastName("Test");
        fortradePage.clickElement(fortradePage.firstName, "on the first name field");
        fortradePage.clickElement(fortradePage.lastName, "on the last name field");
        fortradePage.assertSameNameErrorMsgs();
        fortradePage.takeScreenshot("Error messages for the same first and last name - " + regulation + " regulation");
    }

    @Test
    @Parameters({"regulation", "tag"})
    public void checkingLogoClickability(String regulation, String tag) throws IOException, AWTException {
        FortradePage fortradePage = new FortradePage(driver);
        fortradePage.checkLogoClickability(regulation, "https://www.fortrade.com/minilps/en/education/" + tag);
        fortradePage.takeScreenshot("Logo is not clickable - " + regulation + " regulation");
    }

    @Test
    @Parameters({"regulation"})
    public void checkForCountryCodeErrorMessage(String regulation) throws IOException, AWTException {
        FortradePage fortradePage = new FortradePage(driver);
        fortradePage.checkCountryCodeErrorMessage("01852833kdkd");
        fortradePage.takeScreenshot("Country code error message - " + regulation + " regulation");
    }

    @Test
    @Parameters({"regulation", "countryCode"})
    public void emailIsReceivedSuccessfully(String regulation, String countryCode) throws IOException, AWTException {
        String email = TestData.emailGenerator();
        String phoneNumber = TestData.phoneNumberGenerator();
        FortradeRPage fortradeRPage = new FortradeRPage(driver);
        fortradeRPage.successfullyRegistration("Testq", "Testa", email, countryCode, phoneNumber);
        driver.get("https://www.mailinator.com/");
        Mailinator mailinator = new Mailinator(driver);
        mailinator.findEmail(email);
        mailinator.zoomOutMethod();
        mailinator.takeScreenshot("Email is received successfully - " + regulation + " regulation", mailinator.emailTitle);
    }

    @Test
    @Parameters({"regulation"})
    public void privacyPolicyTest(String regulation) throws IOException, AWTException, InterruptedException {
        FortradePage fortradePage = new FortradePage(driver);
        fortradePage.clickOnSelectedLink(fortradePage.privacyPolicyLinkBy, fortradePage.setRegulation(regulation),
                "Privacy policy", regulation);
        fortradePage.rightClickOnSelectedLink(fortradePage.privacyPolicyLinkBy, fortradePage.setRegulation(regulation));
    }

    @Test
    @Parameters({"regulation"})
    public void termsAndConditionsTest(String regulation) throws IOException, AWTException, InterruptedException {
        FortradePage fortradePage = new FortradePage(driver);
        fortradePage.clickOnSelectedLink(fortradePage.termsAndConditionsLinkBy, fortradePage.termsAndCondition(regulation),
                "Terms and conditions", regulation);
        fortradePage.rightClickOnSelectedLink(fortradePage.termsAndConditionsLinkBy, fortradePage.termsAndCondition(regulation));
    }

    @Test
    @Parameters({"regulation"})
    public void howToUnsubscribeTest(String regulation) throws IOException, InterruptedException, AWTException {
        FortradePage fortradePage = new FortradePage(driver);
        fortradePage.clickOnSelectedLink(fortradePage.clickHereLink, fortradePage.howToUnsubscribeURL, "How to unsubscribe", regulation);
        fortradePage.rightClickOnSelectedLink(fortradePage.clickHereLink, fortradePage.howToUnsubscribeURL);
    }

    @Test
    @Parameters({"regulation"})
    public void loginRedirectionTest(String regulation) throws IOException, AWTException {
        FortradePage fortradePage = new FortradePage(driver);
        fortradePage.loginRedirection(regulation);
        if (driver.getCurrentUrl().contains(fortradePage.alrHaveAccount)) {
            fortradePage.takeScreenshot("Login page - the user is successfully redirected " + regulation +
                    " regulation", fortradePage.loginToFotrade);
        } else {
            System.out.println("Wrong link redirection");
        }
    }

    @Test
    @Parameters({"regulation"})
    public void riskWarningTest(String regulation) throws IOException, InterruptedException, AWTException {
        FortradePage fortradePage = new FortradePage(driver);
        fortradePage.clickDenyBtn();
        fortradePage.scrollToAnElement(driver.findElement(By.xpath("//div[@class='footerRiskDisclaimer']" +
                "//a[contains(text(),'Risk warning')]")));
        fortradePage.clickOnSelectedLink(fortradePage.footerRiskWarningLinkBy, fortradePage.riskWarning(regulation),
                "Risk warning", regulation);
        fortradePage.rightClickOnSelectedLink(fortradePage.footerRiskWarningLinkBy, fortradePage.riskWarning(regulation));
    }

    @Test
    @Parameters({"regulation"})
    public void footerPrivacyPolicy(String regulation) throws IOException, InterruptedException, AWTException {
        FortradePage fortradePage = new FortradePage(driver);
        fortradePage.clickDenyBtn();
        fortradePage.scrollToAnElement(driver.findElement(By.xpath("//div[@class='fscClass']//a[contains" +
                "(text(), 'Privacy policy')]")));
        fortradePage.clickOnSelectedLink(fortradePage.footerPrivacyPolicyLinkBy, fortradePage.footerPrivacyPolicy(regulation),
                "Privacy policy - footer", regulation);
        fortradePage.rightClickOnSelectedLink(fortradePage.footerPrivacyPolicyLinkBy, fortradePage.footerPrivacyPolicy(regulation));
    }

    @Test
    @Parameters({"regulation"})
    public void fcaRegulationTest(String regulation) throws IOException, InterruptedException, AWTException {
        FortradePage fortradePage = new FortradePage(driver);
        fortradePage.clickDenyBtn();
        fortradePage.scrollToAnElement(driver.findElement(By.xpath("//a[text()='FRN: 609970']")));
        fortradePage.clickOnSelectedLink(fortradePage.fcaRegulationLinkBy, fortradePage.fcaLink,
                "Financial conduct authority page", regulation);
        fortradePage.scrollToAnElement(driver.findElement(By.xpath("//a[text()='FRN: 609970']")));
        fortradePage.rightClickOnSelectedLink(fortradePage.fcaRegulationLinkBy, fortradePage.fcaLink);
    }

    @Test
    @Parameters({"regulation"})
    public void iirocRegulationTest(String regulation) throws IOException, InterruptedException, AWTException {
        FortradePage fortradePage = new FortradePage(driver);
        fortradePage.clickDenyBtn();
        fortradePage.scrollToAnElement(driver.findElement(By.xpath("//a[text()='CRN: BC1148613']")));
        fortradePage.clickOnSelectedLink(fortradePage.ciroRegulationLinkBy, fortradePage.iirocLink,
                "Canadian Investment Regulatory Organization page", regulation);
        fortradePage.scrollToAnElement(driver.findElement(By.xpath("//a[text()='CRN: BC1148613']")));
        fortradePage.rightClickOnSelectedLink(fortradePage.ciroRegulationLinkBy, fortradePage.iirocLink);
    }

    @Test
    @Parameters({"regulation"})
    public void asicRegulationTest(String regulation) throws IOException, InterruptedException, AWTException {
        FortradePage fortradePage = new FortradePage(driver);
        fortradePage.clickDenyBtn();
        fortradePage.scrollToAnElement(driver.findElement(By.xpath("//a[text()='ABN: 33 614 683 831 | AFSL: 493520']")));
        fortradePage.clickOnSelectedLink(fortradePage.asicRegulationLinkBy, fortradePage.asicLink,
                "Australian Securities and Investments Commission page", regulation);
        fortradePage.scrollToAnElement(driver.findElement(By.xpath("//a[text()='ABN: 33 614 683 831 | AFSL: 493520']")));
        fortradePage.rightClickOnSelectedLink(fortradePage.asicRegulationLinkBy, fortradePage.asicLink);
    }

    @Test
    @Parameters({"regulation"})
    public void cysecRegulationTest(String regulation) throws IOException, InterruptedException, AWTException {
        FortradePage fortradePage = new FortradePage(driver);
        fortradePage.clickDenyBtn();
        fortradePage.scrollToAnElement(driver.findElement(By.xpath("//a[text()='CIF license number 385/20']")));
        fortradePage.clickOnSelectedLink(fortradePage.cysecRegulationLinkBy, fortradePage.cysecLink,
                "Cyprus Securities and Exchange Commission page", regulation);
        fortradePage.scrollToAnElement(driver.findElement(By.xpath("//a[text()='CIF license number 385/20']")));
        fortradePage.rightClickOnSelectedLink(fortradePage.cysecRegulationLinkBy, fortradePage.cysecLink);
    }

    @Test
    @Parameters({"regulation"})
    public void fscRegulationTest(String regulation) throws IOException, InterruptedException, AWTException {
        FortradePage fortradePage = new FortradePage(driver);
        fortradePage.clickDenyBtn();
        fortradePage.scrollToAnElement(driver.findElement(By.xpath("//a[text()=' GB21026472']")));
        fortradePage.clickOnSelectedLink(fortradePage.fscRegulationLinkBy, fortradePage.fscLink,
                "Financial Services Commission page", regulation);
        fortradePage.scrollToAnElement(driver.findElement(By.xpath("//a[text()=' GB21026472']")));
        fortradePage.rightClickOnSelectedLink(fortradePage.fscRegulationLinkBy, fortradePage.fscLink);
    }

    @Test
    @Parameters({"regulation"})
    public void fsgDocumentTest(String regulation) throws IOException, InterruptedException, AWTException {
        FortradePage fortradePage = new FortradePage(driver);
        fortradePage.clickDenyBtn();
        fortradePage.scrollToAnElement(driver.findElement(By.xpath("//div[@class='footerRiskDisclaimer']" +
                "//div[@class='asicClass']//a[contains(text(),'(FSG)')]")));
        fortradePage.clickOnSelectedLink(fortradePage.fsgDocument, fortradePage.fsgDocumentLink,
                "Financial Service Guide ", regulation);
        fortradePage.scrollToAnElement(driver.findElement(By.xpath("//div[@class='footerRiskDisclaimer']" +
                "//div[@class='asicClass']//a[contains(text(),'(TMD)')]")));
        fortradePage.rightClickOnSelectedLink(fortradePage.fsgDocument, fortradePage.fsgDocumentLink);
    }

    @Test
    @Parameters({"regulation"})
    public void pdsDocumentTest(String regulation) throws IOException, InterruptedException, AWTException {
        FortradePage fortradePage = new FortradePage(driver);
        fortradePage.clickDenyBtn();
        fortradePage.scrollToAnElement(driver.findElement(By.xpath("//div[@class='footerRiskDisclaimer']" +
                "//div[@class='asicClass']//a[contains(text(),'(PDS)')]")));
        fortradePage.clickOnSelectedLink(fortradePage.pdsDocument, fortradePage.pdsDocumentLink,
                "Product Disclosure Statement document", regulation);
        fortradePage.scrollToAnElement(driver.findElement(By.xpath("//div[@class='footerRiskDisclaimer']" +
                "//div[@class='asicClass']//a[contains(text(),'(PDS)')]")));
        fortradePage.rightClickOnSelectedLink(fortradePage.pdsDocument, fortradePage.pdsDocumentLink);
    }

    @Test
    @Parameters({"regulation"})
    public void tmdDocumentTest(String regulation) throws IOException, InterruptedException, AWTException {
        FortradePage fortradePage = new FortradePage(driver);
        fortradePage.clickDenyBtn();
        fortradePage.scrollToAnElement(driver.findElement(By.xpath("//div[@class='footerRiskDisclaimer']" +
                "//div[@class='asicClass']//a[contains(text(),'(TMD)')]")));
        fortradePage.clickOnSelectedLink(fortradePage.tmdDocument, fortradePage.tmdDeterminationLink,
                "Target Market Determination ", regulation);
        fortradePage.scrollToAnElement(driver.findElement(By.xpath("//div[@class='footerRiskDisclaimer']" +
                "//div[@class='asicClass']//a[contains(text(),'(TMD)')]")));
        fortradePage.rightClickOnSelectedLink(fortradePage.tmdDocument, fortradePage.tmdDeterminationLink);
    }

    @Test
    @Parameters({"regulation"})
    public void fbLinkRedirection(String regulation) throws IOException, InterruptedException, AWTException {
        FortradePage fortradePage = new FortradePage(driver);
        fortradePage.clickDenyBtn();
        fortradePage.clickOnSelectedLink(fortradePage.facebookLinkBy, fortradePage.fbPage(regulation), "Facebook page", regulation);
        fortradePage.rightClickOnSelectedLink(fortradePage.facebookLinkBy, fortradePage.fbPage(regulation));
    }

    @Test
    @Parameters({"regulation"})
    public void insLinkRedirection(String regulation) throws IOException, InterruptedException, AWTException {
        FortradePage fortradePage = new FortradePage(driver);
        fortradePage.clickDenyBtn();
        fortradePage.clickOnSelectedLink(fortradePage.instagramLinkBy, fortradePage.insURL, "Instagram page", regulation);
        fortradePage.rightClickOnSelectedLink(fortradePage.instagramLinkBy, fortradePage.insURL);
    }

    @Test
    @Parameters({"regulation"})
    public void ytLinkRedirection(String regulation) throws IOException, InterruptedException, AWTException {
        FortradePage fortradePage = new FortradePage(driver);
        fortradePage.clickDenyBtn();
        fortradePage.clickOnSelectedLink(fortradePage.youtubeLinkBy, fortradePage.ytURL, "Instagram page", regulation);
        fortradePage.rightClickOnSelectedLink(fortradePage.youtubeLinkBy, fortradePage.ytURL);
    }

    @Test
    @Parameters({"regulation"})
    public void contactUsLink(String regulation) throws IOException, AWTException, InterruptedException {
        FortradePage fortradePage = new FortradePage(driver);
        fortradePage.clickOnMailLink("contactUs");
        Thread.sleep(1500);
        fortradePage.takeScreenshot("Fortrade " + regulation + " - contact us redirection");
        fortradePage.closeOutlook();
    }

    @Test
    @Parameters({"regulation"})
    public void supportLink(String regulation) throws IOException, AWTException, InterruptedException {
        FortradePage fortradePage = new FortradePage(driver);
        fortradePage.clickDenyBtn();
        if (!regulation.equalsIgnoreCase("cysec")) {
            fortradePage.clickOnMailLink("support");
        } else {
            System.out.println("Cysec regulation does not contain support.fortrade.com address");
        }
        Thread.sleep(2000);
        fortradePage.takeScreenshot("Fortrade " + regulation + " - support redirection");
        fortradePage.closeOutlook();
    }
}
