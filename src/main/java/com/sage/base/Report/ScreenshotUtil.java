package com.sage.base.Report;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;

public class ScreenshotUtil {
    /**
     * Captures a screenshot of the current browser window.
     *
     * @param driver   The WebDriver instance
     * @param filePath Full path including the file name and .png extension (e.g., "C:/screenshots/homepage.png")
     */
    public static void captureScreenshot(WebDriver driver, String filePath) {
        TakesScreenshot screenshot = (TakesScreenshot) driver;
        File srcFile = screenshot.getScreenshotAs(OutputType.FILE);
        File destFile = new File(filePath);

        try {
            FileUtils.copyFile(srcFile, destFile);
            System.out.println("Screenshot saved to: " + filePath);
        } catch (IOException e) {
            System.err.println("Failed to save screenshot: " + e.getMessage());
        }
    }
}
