package listeners;

import org.testng.ITestListener;
import org.testng.ITestResult;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Date;

public class ScreenshotListener implements ITestListener {
    /**
     * onTestFailure metoda prati test koji je pokrenut, i ako padne poziva metodu takeScreenshot koja pravi
     * screenshot celog ekrana.
     * @param result <code>ITestResult</code> containing information about the run test
     */
    @Override
    public void onTestFailure(ITestResult result){
        try {
            takeScreenshot(result);
        } catch (AWTException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * takeScreenshot metoda pravi screenshot celog ekrana (ako folder ne postoji, objekat directory File
     * klase kreira novi folder (directory.mkdirs();)).
     *
     * takeScreenshot metoda koristi javinu Robot klasu koja uzima dimenzije ekrana i kreira screenshot celog ekrana
     * i smesta ga u screenFullImage objekat klase BufferedImage. Kreira ime fajla (String screenshotName) i kreira
     * folder - (failedScreenshots) ukoliko ne postoji (directory.mkdirs();). Kreira lokaciju fajla gde ce biti smesten i
     * nakon toga upisuje screenshot u PNG formatu na lokaciji koju smo mu odredili (String location).
     * @param result
     */
    public void takeScreenshot(ITestResult result) throws AWTException, IOException {

        // Using java.awt.Robot and java.awt.Dimension for full screen capture
        Robot robot = new Robot();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Rectangle screenRect = new Rectangle(screenSize);
        BufferedImage screenFullImage = robot.createScreenCapture(screenRect);

        // Saving the full screen image
        String screenshotName = "Failed - " + result.getName();
        File directory = new File("src/failedScreenshots");
        if (!directory.exists()) {
            directory.mkdirs();  // Create the directory if it doesn't exist
        }
        String location = "src/failedScreenshots/" + screenshotName + ".png";
        ImageIO.write(screenFullImage, "PNG", new File(location));
    }
}
