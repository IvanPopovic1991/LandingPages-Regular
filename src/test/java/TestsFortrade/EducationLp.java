package TestsFortrade;

import Pages.CrmPage;
import Pages.FortradePage;
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
    String email = TestData.emailGenerator();
    String phoneNumber = TestData.phoneNumberGenerator();

    @Test
    @Parameters({"regulation", "tag"})
    @BeforeMethod
    public void setUp(String regulation, String tag) {
        baseSetup("Chrome", "130");
        driver.get("https://www.fortrade.com/minilps/en/education/" + tag);
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(webDriver -> ((JavascriptExecutor) webDriver)
                .executeScript("return document.readyState").equals("complete"));
//      JavascriptExecutor js = (JavascriptExecutor) driver;
//      js.executeScript("arguments[0].setAttribute('data-lcreg', arguments[1]);",
//      driver.findElement(By.xpath("//body[@data-lcreg='FSC']")), regulation);
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
    public void alreadyRegisteredAccountTest(String countryCode, String regulation) throws IOException, AWTException, InterruptedException {
        demoAccountRegistration(countryCode, regulation);
        driver.get("https://www.fortrade.com/minilps/en/education/");
        FortradePage fortradePage = new FortradePage(driver);
        fortradePage.alreadyRegisteredAccount("Testq", "Testa", email, countryCode, phoneNumber);
        fortradePage.assertPopUpForAlreadyRegisteredAccount("Already registered account - pop-up " + regulation);
    }

    @Test
    @Parameters({"regulation", "countryCode"})
    public void checkingTagsInTheCRM(String regulation, String countryCode) throws IOException, InterruptedException, AWTException {
        driver.get("https://www.fortrade.com/minilps/en/education/?tg=ivanA1389&tag1=ivanB@1389&tag2=ivanL1389&tag3=" +
                "ivanM1389&gid=ivanC@1389&G_GEO=ivanD1389&G_GEOint=ivanE1389&G_Device=ivanF1389&G_DeviceModel=ivanG1389" +
                "&G_AdPos=ivanH1389&g_Track=ivanI1389&Track=ivanj1389&gclid=ivanK1389");
        demoAccountRegistration(regulation, countryCode);
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
}
