package com.qagoose.hackathon.visual;

import com.applitools.eyes.selenium.Eyes;
import org.openqa.selenium.WebDriver;

public class EyesWrapper {
    private WebDriver driver;
    private String appName;
    private Eyes eyes;

    public EyesWrapper(Eyes eyes, WebDriver driver, String appName) {
        this.driver = driver;
        this.eyes = eyes;
        this.appName = appName;
    }

    public void startEyes(String testName) {
        eyes.open(driver, appName, testName);
    }

    public void stopEyes() {
        eyes.closeAsync();
    }
}
