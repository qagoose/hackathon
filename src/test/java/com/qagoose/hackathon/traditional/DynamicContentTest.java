package com.qagoose.hackathon.traditional;

import com.qagoose.hackathon.traditional.builders.LogInAction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DynamicContentTest extends TraditionalTest {
    @BeforeEach
    public void logIn() {
        // Need to go to a different login page
        baseDriver.getDriver().get("https://demo.applitools.com/hackathonV2.html?showAd=true");

        hack.pages.loginPage.logIn(
                LogInAction
                .builder()
                .withUsername("Will")
                .withPassword("Password1")
                .assertDashboardLoads()
                .build()
        );
    }

    @Test
    public void pageShouldHaveTwoAds() {
        hack.pages.dashboardPage.checkAdOneIsPresent();
        hack.pages.dashboardPage.checkAdTwoIsPresent();
    }
}
