package com.qagoose.hackathon.traditional;

import com.qagoose.hackathon.traditional.builders.LogInAction;
import org.junit.jupiter.api.Test;

public class LoginTest extends TraditionalTest {
    @Test
    public void loginFormShouldMatchDesign() {
        // Not going to do this one.  To hook up a bunch of checks for elements being on the page is going to take
        // a while, and I'd rather not.  I understand the point you're making :)
    }

    @Test
    public void userShouldSeeErrorIfFormLeftBlank() {
        hack.pages.loginPage.logIn(
                LogInAction
                        .builder()
                        .assertErrorPresent("Both Username and Password must be present")
                        .build()
        );
    }

    @Test
    public void userShouldSeeErrorIsPasswordLeftBlank() {
        hack.pages.loginPage.logIn(
                LogInAction
                .builder()
                .withUsername("Will")
                .assertErrorPresent("Password must be present")
                .build()
        );
    }

    @Test
    public void userShouldSeeErrorIfUsernameLeftBlank() {
        hack.pages.loginPage.logIn(
                LogInAction
                        .builder()
                        .withPassword("Password1SuperSecureOK?")
                        .assertErrorPresent("Username must be present")
                        .build()
        );
    }

    @Test
    public void userShouldBeAbleToLogInWithValidCredentials() {
        hack.pages.loginPage.logIn(
                LogInAction
                        .builder()
                        .withUsername("Will")
                        .withPassword("Password1SuperSecureOK?")
                        .assertDashboardLoads()
                        .build()
        );
    }
}
