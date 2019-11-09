package com.qagoose.hackathon.traditional;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;
import ru.yandex.qatools.ashot.coordinates.WebDriverCoordsProvider;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class ScreenshotComparer {
    public static void compareScreenshots(String baselineName, BaseDriver baseDriver, WebElement element, boolean initialRun) throws IOException {
        if (initialRun) {
            // If it's the initial run we need to save a new image and not do a comparison
            saveScreenshot(
                    baselineName + "_baseline",
                    takeScreenshot(baseDriver.getDriver(), element)
            );
        } else {
            // Load baseline
            Screenshot baseline = loadScreenshot(new File(ScreenshotComparer.class.getClassLoader().getResource(baselineName + "_baseline.PNG").getFile()));

            // Take screenshot
            Screenshot current = takeScreenshot(baseDriver.getDriver(), element);

            // Compare them?
            ImageDiff diff = compareScreenshots(baseline, current);

            // Save current
            saveScreenshot(baselineName + "_current", current);

            if(diff.hasDiff()) {
                saveScreenshot(baselineName + "_diff", bufferedImageToScreenshot(diff.getMarkedImage()));
                saveScreenshot(baselineName + "_diff_transparent", bufferedImageToScreenshot(diff.getTransparentMarkedImage()));
            }

            assertFalse(diff.hasDiff(), "Comparison revealed significant differences.  Please view _diff and _diff_transparent for " + baselineName);
            //diff.getTransparentMarkedImage()
        }
    }

    private static Screenshot takeScreenshot(WebDriver driver, WebElement element) {
        AShot ashot = new AShot();
        ashot.coordsProvider(new WebDriverCoordsProvider());

        return ashot.takeScreenshot(driver, element);
    }

    private static ImageDiff compareScreenshots(Screenshot baseline, Screenshot current) {
        ImageDiffer differ = new ImageDiffer();
        return differ.makeDiff(baseline, current);
    }

    private static Screenshot loadScreenshot(File file) throws IOException {
        BufferedImage image = ImageIO.read(file);
        return new Screenshot(image);
    }

    private static Screenshot bufferedImageToScreenshot(BufferedImage image) {
        return new Screenshot(image);
    }

    private static void saveScreenshot(String name, Screenshot screenshot) throws IOException {
        File file = new File(ScreenshotComparer.class.getClassLoader().getResource("").getFile() + File.separator + name + ".PNG");
        System.out.println(file.getPath());
        file.createNewFile();
        ImageIO.write(screenshot.getImage(), "PNG", file);
    }
}
