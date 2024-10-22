package TestsFortrade;

import Pages.FortradePage;
import Pages.FortradeRPage;
import faker.TestData;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;

public class EducationLp extends BaseTestFortrade {
    String email = TestData.emailGenerator();
    String phoneNumber = TestData.phoneNumberGenerator();
    @BeforeMethod
    public void setUp() {
        baseSetup("Chrome", "130");
    }

    @AfterMethod
    public void tearDown() throws AWTException {
        baseTearDown();
    }

    @Test
    @Parameters({"countryCode","regulation"})
    public void demoAccountRegistration(String countryCode,String regulation) throws IOException, AWTException, InterruptedException {
        FortradePage fortradePage = new FortradePage(driver);
        fortradePage.successfullyRegistration("Testq", "Testa", email,
        countryCode, phoneNumber);
        fortradePage.assertURL("https://ready.fortrade.com/#chartticket");
        fortradePage.clickContinueBtn();
        if (regulation.equals("Asic")){
            fortradePage.clickConsentBtn();
        }
        fortradePage.clickMenuBtn();
        fortradePage.checkRegulation(regulation);
    }
    @Test
    @Parameters({"countryCode","regulation"})
    public void alreadyRegisteredAccountTest(String countryCode, String regulation) throws IOException, AWTException, InterruptedException {
        demoAccountRegistration(countryCode, regulation);
        driver.get("https://www.fortrade.com/minilps/en/education/");
        FortradePage fortradePage = new FortradePage(driver);
        fortradePage.alreadyRegisteredAccount("Testq", "Testa", email, countryCode, phoneNumber);
        fortradePage.assertPopUpForAlreadyRegisteredAccount("Already registered account - pop-up " + regulation);
    }
}
