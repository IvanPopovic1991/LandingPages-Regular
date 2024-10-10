package TestsFortrade;

import Pages.FortradePage;
import faker.TestData;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class EducationLp extends BaseTestFortrade {
    @BeforeMethod
    public void setUp() {
        baseSetup("Chrome", "129");
    }

    @AfterMethod
    public void tearDown() {
        baseTearDown();
    }

    @Test
    public void demoAccountRegistration() {
        FortradePage fortradePage = new FortradePage(driver);
        fortradePage.successfullyRegistration("Testq", "Testa", TestData.emailGenerator(), "381", TestData.phoneNumberGenerator());
        fortradePage.assertURL("https://ready.fortrade.com/#chartticket");
    }
}
