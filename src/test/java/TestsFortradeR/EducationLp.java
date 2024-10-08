package TestsFortradeR;
import Pages.FortradeRPage;
import com.github.javafaker.Faker;
import faker.TestData;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class EducationLp extends BaseTestFortradeR {

    @BeforeMethod
    public void setUp(){
        baseSetup("Chrome", "129");
    }
    @AfterMethod
    public void tearDown(){
        baseTearDown();
    }
    @Test
    public void demoAccountRegistration(){
        FortradeRPage fortradeRPage = new FortradeRPage(driver);
        fortradeRPage.successfullyRegistration("Testq","Testa", TestData.emailGenerator(),"381", TestData.phoneNumberGenerator());
    }

}
