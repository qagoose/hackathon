package com.qagoose.hackathon.visual;

import com.qagoose.hackathon.traditional.builders.LogInAction;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

public class LoginTest extends VisualTest {
    @Test
    @DisplayName("Login form should match design")
    public void loginFormShouldMatchDesign(TestInfo testInfo) {
        hack.eyesHelper.startEyes(testInfo.getDisplayName());
        hack.eyes.checkWindow("login_page");
        hack.eyesHelper.stopEyes();
    }

    @Test
    @DisplayName("User should see error if login form left blank")
    public void userShouldSeeErrorIfFormLeftBlank(TestInfo testInfo) {
        hack.eyesHelper.startEyes(testInfo.getDisplayName());

        hack.pages.loginPage.logIn(
                LogInAction
                        .builder()
                        .build()
        );

        hack.eyes.checkWindow("login_page_blank_error");

        hack.eyesHelper.stopEyes();
    }

    @Test
    @DisplayName("User should see error if password left blank")
    public void userShouldSeeErrorIfPasswordLeftBlank(TestInfo testInfo) {
        hack.eyesHelper.startEyes(testInfo.getDisplayName());

        hack.pages.loginPage.logIn(
                LogInAction
                        .builder()
                        .withUsername("Will")
                        .build()
        );

        hack.eyes.checkWindow("login_page_password_blank_error");
        hack.eyesHelper.stopEyes();
    }

    @Test
    @DisplayName("User should see error if username left blank")
    public void userShouldSeeErrorIfUsernameLeftBlank(TestInfo testInfo) {
        hack.eyesHelper.startEyes(testInfo.getDisplayName());
        hack.pages.loginPage.logIn(
                LogInAction
                        .builder()
                        .withPassword("Password1SuperSecureOK?")
                        .build()
        );

        hack.eyes.checkWindow("login_page_username_blank_error");
        hack.eyesHelper.stopEyes();
    }

    @Test
    @DisplayName("User should be able to log in with valid credentials")
    public void userShouldBeAbleToLogInWithValidCredentials(TestInfo testInfo) {
        hack.eyesHelper.startEyes(testInfo.getDisplayName());
        hack.pages.loginPage.logIn(
                LogInAction
                        .builder()
                        .withUsername("Will")
                        .withPassword("Password1SuperSecureOK?")
                        .build()
        );
        hack.eyes.checkWindow("dashboard_after_login");
        hack.eyesHelper.stopEyes();
    }
}
