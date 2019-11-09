package com.qagoose.hackathon.traditional;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BaseDriver {
    private WebDriver driver;

    public WebDriver getDriver() {
        return driver;
    }

    public void startDriver() {
        ensureDriverLocationSet();
        driver = new ChromeDriver();
    }

    public void stopDriver() {
        if(driver != null) {
            driver.quit();
        }
    }

    private void ensureDriverLocationSet() {
        if(System.getProperty("webdriver.chrome.driver") == null) {
            System.setProperty("webdriver.chrome.driver", "T:/chromedriver/chromedriver.exe");
        }
    }
}
