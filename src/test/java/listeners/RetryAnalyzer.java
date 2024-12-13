package listeners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {
    private int count = 0;
    private int maxCount = 1;

    /**
     * Ova metoda prati test koji je pokrenut, i ako je pao (nije uspesan) ponavlja ga onoliko puta koliko maxCount
     * ima vrednost (tj. povecava count vrednost sve dok ne bude imao istu vrednost kao i maxCount), u suprotnom
     * stavlja vrednost testa na uspesan i ne ponavlja ga. (Ova klasa i retry methoda se koriste u Transform klasi
     * koju kasnije koristimo u .xml fajlu)
     * @param result The result of the test method that just ran.
     * @return
     */
    @Override
    public boolean retry(ITestResult result) {
        if (!result.isSuccess()) {
            if (count < maxCount) {
                count++;
                return true;
            } else {
                return false;
            }
        } else {
            result.setStatus(1);
        }
        return false;
    }
}
