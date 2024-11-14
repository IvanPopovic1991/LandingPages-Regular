package TestsFortradeR;

import Pages.CrmPage;
import Pages.FortradeRPage;
import faker.TestData;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;


public class EducationLp extends BaseTestFortradeR {
    String email = TestData.emailGenerator();
    String phoneNumber = TestData.phoneNumberGenerator();

    @BeforeMethod
    public void setUp() {
        baseSetup("Chrome", "130");
    }

    @AfterMethod
    public void tearDown() {
        baseTearDown();
    }

    @Test
    public void urlAssert() throws IOException, AWTException {
        FortradeRPage fortradeRPage = new FortradeRPage(driver);
        fortradeRPage.assertURL("https://www.fortrader.com/minilps/en/education/");
        //last element uploaded - input field with attribute value='quick'
        String quickValue = driver.findElement(By.xpath("//form[@class='LC-QuickRegistrationMultiTraderWithVerify" +
                "PhoneWidget']/input[@value='quick']")).getAttribute("value");
        Assert.assertEquals("quick", quickValue);
        fortradeRPage.takeScreenshot("Page url - FortradeR - verified");
    }

    @Test
    public void demoAccountRegistration() throws IOException, AWTException {
        FortradeRPage fortradeRPage = new FortradeRPage(driver);
        fortradeRPage.successfullyRegistration("Testq", "Testa", email, "381", phoneNumber);
        fortradeRPage.assertURL("https://ready.fortrade.com/#chartticket");
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
        demoAccountRegistration();
        driver.get("https://www.fortrader.com/minilps/en/education/");
        FortradeRPage fortradeRPage = new FortradeRPage(driver);
        fortradeRPage.alreadyRegisteredAccount("Testq", "Testa", email, "381", phoneNumber);
        fortradeRPage.assertPopUpForAlreadyRegisteredAccount("Already registered account - FortradeR - pop-up");
    }

    @Test
    public void checkingTagsInTheCrm() throws IOException, AWTException {
        driver.get("https://www.fortrader.com/minilps/en/education/?tg=ivanA1389&tag1=ivanB@1389&tag2=ivanL1389&tag3=" +
                "ivanM1389&gid=ivanC@1389&G_GEO=ivanD1389&G_GEOint=ivanE1389&G_Device=ivanF1389&G_DeviceModel=ivanG1389&G_" +
                "AdPos=ivanH1389&g_Track=ivanI1389&Track=ivanj1389&gclid=ivanK1389");
        demoAccountRegistration();
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
        fortradeRPage.checkLogoClickability("https://www.fortrader.com/minilps/en/education/");
        fortradeRPage.takeScreenshot("Logo is not clickable - FortradeR");
    }
    @Test
    public void checkForCountryCodeErrorMessage() throws IOException, AWTException {
        FortradeRPage fortradeRPage = new FortradeRPage(driver);
        fortradeRPage.checkCountryCodeErrorMessage("01852833kdkd");
        fortradeRPage.takeScreenshot("Country code error message - FortradeR");
    }
}
