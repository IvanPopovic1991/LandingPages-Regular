package TestsFortrade;

import Pages.CrmPage;
import Pages.FortradePage;
import faker.TestData;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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
    @Parameters({"regulation"})
    @BeforeMethod
    public void setUp(String regulation) {
        baseSetup("Chrome", "130");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].setAttribute('data-lcreg', arguments[1]);",
        driver.findElement(By.xpath("//body[@data-lcreg='FSC']")), regulation);
    }

    @AfterMethod
    public void tearDown(){
        baseTearDown();
    }

    @Test
    @Parameters({"regulation","countryCode"})
    public void demoAccountRegistration(String regulation,String countryCode) throws IOException, AWTException, InterruptedException {
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
    @Parameters({"countryCode","regulation"})
    public void alreadyRegisteredAccountTest( String countryCode,String regulation) throws IOException, AWTException, InterruptedException {
        demoAccountRegistration(countryCode, regulation);
        driver.get("https://www.fortrade.com/minilps/en/education/");
        FortradePage fortradePage = new FortradePage(driver);
        fortradePage.alreadyRegisteredAccount("Testq", "Testa", email, countryCode, phoneNumber);
        fortradePage.assertPopUpForAlreadyRegisteredAccount("Already registered account - pop-up " + regulation);
    }
    @Test
    @Parameters({"regulation","countryCode"})
    public void checkingTagsInTheCRM(String regulation, String countryCode) throws IOException, InterruptedException, AWTException {
        demoAccountRegistration(regulation,countryCode);
        String urlCRM = System.getenv("URLForCrm");
        String usernameCRM = System.getenv("UsernameForCrm");
        String passwordCRM = System.getenv("PasswordForCrm");
        driver.get(urlCRM);
        CrmPage crmPage = new CrmPage(driver);
        crmPage.checkCrmData(usernameCRM,passwordCRM,email, "Testq Testa",regulation);
        crmPage.takeScreenshot("Account Details " + regulation + " regulation",crmPage.accFullNameCrm);
        crmPage.checkCrmTags();
        crmPage.takeScreenshot("Marketing Tags " + regulation + " regulation",crmPage.marketingSection);
        }

    @Test
    @Parameters({"regulation"})
    public void unsuccessfullyDemoAccountRegistration(String regulation) throws IOException, AWTException {
        FortradePage fortradePage = new FortradePage(driver);
        fortradePage.unsuccessfullyRegistrationWrongData("12/*","+-*5","test#123","123456","/*-+");
        fortradePage.assertErrorMessages();
        fortradePage.assertColor("red");
        fortradePage.takeScreenshot("Unsuccessfully demo account registration " + regulation + " regulation", fortradePage.firstName);
    }
    @Test
    @Parameters({"regulation"})
    public void emptyDemoAccountRegistration(String regulation) throws IOException, AWTException {
        FortradePage fortradePage = new FortradePage(driver);
        fortradePage.unsuccessfullyRegistrationWrongData("","","","","");
        fortradePage.assertErrorMessages();
        fortradePage.assertColor("red");
        fortradePage.takeScreenshot("Demo account registration - no data " + regulation + " regulation", fortradePage.submitButton);
    }
}
