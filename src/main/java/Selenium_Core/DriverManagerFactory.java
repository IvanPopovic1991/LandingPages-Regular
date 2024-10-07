package Selenium_Core;

/**
 * DriverManagerFactory instancira konkretne driver-e (tj browser-e).Bavi se apstraktnom metodom createWebDriver.
 * Sadrzi static metodu getDriverManager tipa DRIVERMANAGER - samim tim ima return type driverManager. Ova metoda sadrzi
 * i parametar browser tipa String - odnosi se na vrstu/tip driver-a ( tj. da li je Chrome, Firefox ili dr.)
 * Return type klase DriverManagerFactory bice DriverManager tj njene sve metode - createWebDriver, quitDriver, getWebDriver
 */
public class DriverManagerFactory {
    public static DriverManager getDriverManager(String browser) {
        /**
         * obzirom da vraca nazad (return browser DriverManager) kreira se objekat driverManager klase DriverManager
         */
        DriverManager driverManager;
        switch (browser) {
            case "Chrome": {
                driverManager = new ChromeDriverManager();
                // objekat driverManager ce biti instanca klase ChromeDriverManager
            }
            break;
            case "Firefox": {
                driverManager = new FirefoxDriverManager();
                // objekat driverManager ce biti instanca klase FirefoxDriverManager
            }
            break;
            case "Edge" :{
                driverManager = new EdgeDriverManager();
            }
            break;
            case "Opera" : {
                driverManager = new OperaDriverManager();
            }break;
            default: {
                driverManager = new ChromeDriverManager();
            }
            break;
        }
        return driverManager;
    }
}
