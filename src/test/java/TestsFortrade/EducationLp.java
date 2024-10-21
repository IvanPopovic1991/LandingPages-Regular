package TestsFortrade;

import Pages.FortradePage;
import faker.TestData;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;

public class EducationLp extends BaseTestFortrade {
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
    public void demoAccountRegistration(String countryCode,String regulation) throws IOException, AWTException {
        FortradePage fortradePage = new FortradePage(driver);
        fortradePage.successfullyRegistration("Testq", "Testa", TestData.emailGenerator(),
        countryCode, TestData.phoneNumberGenerator());
        fortradePage.assertURL("https://ready.fortrade.com/#chartticket");
        fortradePage.clickContinueBtn();
        fortradePage.clickMenuBtn();
        fortradePage.checkRegulation(regulation);
    }
}
