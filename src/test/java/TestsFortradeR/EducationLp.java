package TestsFortradeR;
import Pages.FortradeRPage;
import faker.TestData;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;


public class EducationLp extends BaseTestFortradeR {

    @BeforeMethod
    public void setUp(){
        baseSetup("Chrome", "129");
    }
    @AfterMethod
    public void tearDown() throws IOException, AWTException {
        baseTearDown("FortradeR page - Account registration");
    }
    @Test
    public void demoAccountRegistration(){
        FortradeRPage fortradeRPage = new FortradeRPage(driver);
        fortradeRPage.successfullyRegistration("Testq","Testa", TestData.emailGenerator(),"381", TestData.phoneNumberGenerator());
        fortradeRPage.assertURL("https://ready.fortrade.com/#chartticket");
    }
}
