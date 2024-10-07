package Selenium_Core;

import org.openqa.selenium.WebDriver;

/**
 * Klasa DriverManager je apstrakna klasa koja se bavi time kako se kreira driver, kako se dobija driver, kako se gasi
 * driver.
 */
public abstract class DriverManager {
    /**
     * Selenium WebDriver je interfejs koji definise skup metoda. Međutim, implementaciju pružaju klase specifične za
     * pretraživač. Neke od klasa implementacije su AndroidDriver, ChromeDriver, FirefoxDriver, InternetExplorerDriver,
     * IPhoneDriver , SafariDriver itd. Glavna funkcionalnost WebDriver-a je kontrola pretraživača. Čak nam pomaže da
     * odaberemo elemente HTML stranice i izvršimo operacije na njima kao što su klik, popunjavanje polja obrasca itd.
     * Polje WebDriver driver je dostupno jedino klasama koje nasledjuju klasu DriverManager
     */
    protected WebDriver driver;
    /**
     * Metod za kreiranje vebdrajvera
     *
     * @param version - verzija browser-a
     */
    public abstract void createWebDriver(String version);

    /**
     * driverQuit metoda sluzi za gasenje drivera - Ukoliko driver nije null ugasi ga (quit-uj ga) i podesi njegovu
     * vrednost da bude null
     */
    public void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
    /**
     * Ukoliko je vrednost drivera null, pre nego sto vratis driver kreiraj ga;
     * @param version - verzija browser-a
     * @return Metod ima return type driver (objekat klase WebDriver)
     */
    public WebDriver getWebDriver(String version) {
        if (driver == null) {
            createWebDriver(version);
        }
        return driver;
    }
}
