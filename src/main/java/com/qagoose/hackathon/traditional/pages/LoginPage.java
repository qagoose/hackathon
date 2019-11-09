package com.qagoose.hackathon.traditional.pages;

import com.qagoose.hackathon.traditional.BaseDriver;
import com.qagoose.hackathon.traditional.BasePage;
import com.qagoose.hackathon.traditional.builders.LogInAction;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginPage extends BasePage {

    public LoginPage(BaseDriver baseDriver) {
        super(baseDriver);
    }

    public void logIn(LogInAction actions) {
        if (actions.getUsername() != null) {
            fillUsernameField(actions.getUsername());
        }

        if (actions.getPassword() != null) {
            fillPasswordField(actions.getPassword());
        }

        clickLogInButton();

        if (!actions.getErrorMessages().isEmpty()) {
            for (String errorMessage : actions.getErrorMessages()) {
                assertTrue(
                        isErrorMessageVisible(errorMessage),
                        String.format("Error message '%s' was not visible", errorMessage)
                );
            }
        }

        if (actions.getAssertSuccess() != null && actions.getAssertSuccess()) {
            assertTrue(hasDashboardLoaded(), "Dashboard did not load after login");
        }
    }

    private void fillUsernameField(String value) {
        fillTextField(By.id("username"), value);
    }

    private void fillPasswordField(String value) {
        fillTextField(By.id("password"), value);
    }

    private void clickLogInButton() {
        clickElement(By.id("log-in"));
    }

    private boolean hasDashboardLoaded() {
        return isElementDisplayed(By.xpath("//h6[contains(text(), 'Financial Overview')]"));
    }

    private boolean isErrorMessageVisible(String errorMessage) {
        boolean errorVisible = false;
        WebElement errorElement = getElement(By.cssSelector(".alert.alert-warning"));

        if (errorElement.getText().equals(errorMessage) && isElementDisplayed(errorElement)) {
            errorVisible = true;
        }

        return errorVisible;
    }
}
