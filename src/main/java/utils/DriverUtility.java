package utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DriverUtility {

    public static void takeScreenshot(WebDriver driver, String fileName) {
        try {
            //Not to overwrite the screenshots
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
            LocalDateTime now = LocalDateTime.now();
            String dateTimeString = now.format(formatter);
            String fullFileName = fileName + "_" + dateTimeString + ".png";

            TakesScreenshot screenshot = (TakesScreenshot) driver;
            File srcFile = screenshot.getScreenshotAs(OutputType.FILE);
            Path destinationPath = Paths.get("test-output/screenshots", fullFileName);

            Files.createDirectories(destinationPath.getParent());
            Files.copy(srcFile.toPath(), destinationPath);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
