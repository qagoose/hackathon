package com.qagoose.hackathon.traditional;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class BasePage {
    protected BaseDriver baseDriver;
    private WebDriver driver;
    private WebDriverWait wait;

    public BasePage(BaseDriver baseDriver) {
        this.baseDriver = baseDriver;
        this.driver = baseDriver.getDriver();
        wait = new WebDriverWait(driver, 10);
    }

    protected WebElement getElement(By by) {
        waitForElementDisplayed(by);
        return driver.findElement(by);
    }

    protected WebElement getElement(By by, WebElement context) {
        return context.findElement(by);
    }

    protected List<WebElement> getElements(By by) {
        return driver.findElements(by);
    }

    protected List<WebElement> getElements(By by, WebElement context) {
        return context.findElements(by);
    }

    protected void fillTextField(By by, String value) {
        getElement(by).sendKeys(value);
    }

    protected WebElement waitForElementDisplayed(By by) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    protected void clickElement(By by) {
        wait.until(ExpectedConditions.elementToBeClickable(by));
        getElement(by).click();
    }

    protected boolean isElementDisplayed(By by, int timeOutInSeconds) {
        boolean elementDisplayed = false;
        WebDriverWait customWait = new WebDriverWait(driver, timeOutInSeconds);

        try {
            customWait.until(ExpectedConditions.visibilityOfElementLocated(by));
            elementDisplayed = true;
        } catch (TimeoutException e) {
            // Ignore, we just want to return false if we timed out
        }

        return elementDisplayed;
    }

    protected boolean isElementDisplayed(By by) {
        return isElementDisplayed(by, 2);
    }

    protected boolean isElementDisplayed(WebElement element, int timeOutInSeconds) {
        boolean elementDisplayed = false;
        WebDriverWait customWait = new WebDriverWait(driver, timeOutInSeconds);

        try {
            customWait.until(ExpectedConditions.visibilityOf(element));
            elementDisplayed = true;
        } catch (TimeoutException e) {
            // Ignore, we just want to return false if we timed out
        }

        return elementDisplayed;
    }

    protected boolean isElementDisplayed(WebElement element) {
        return isElementDisplayed(element, 2);
    }

    protected boolean hasImageLoaded(WebElement image) {

        Object result = ((JavascriptExecutor) driver).executeScript(
                "return arguments[0].complete && " +
                        "typeof arguments[0].naturalWidth != \"undefined\" && " +
                        "arguments[0].naturalWidth > 0", image);

        boolean loaded = false;
        if (result instanceof Boolean) {
            loaded = (Boolean) result;
        }

        return loaded;
    }
}
