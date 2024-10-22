package TestsFortradeR;
import Pages.FortradeRPage;
import faker.TestData;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;


public class EducationLp extends BaseTestFortradeR {
    String email = TestData.emailGenerator();
    String phoneNumber = TestData.phoneNumberGenerator();

    @BeforeMethod
    public void setUp(){
        baseSetup("Chrome", "130");
    }
    @AfterMethod
    public void tearDown(){
        baseTearDown();
    }
    @Test
    public void demoAccountRegistration(){
        FortradeRPage fortradeRPage = new FortradeRPage(driver);
        fortradeRPage.successfullyRegistration("Testq","Testa", email,"381", phoneNumber);
        fortradeRPage.assertURL("https://ready.fortrade.com/#chartticket");
    }
    @Test
    public void alreadyRegisteredAccountTest() throws IOException, AWTException {
        demoAccountRegistration();
        driver.get("https://www.fortrader.com/minilps/en/education/");
        FortradeRPage fortradeRPage = new FortradeRPage(driver);
        fortradeRPage.alreadyRegisteredAccount("Testq", "Testa", email, "381", phoneNumber);
        fortradeRPage.assertPopUpForAlreadyRegisteredAccount("Already registered account - FortradeR - pop-up");
    }
}
