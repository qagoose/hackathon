package com.qagoose.hackathon.visual;

import com.qagoose.hackathon.traditional.TraditionalTest;
import com.qagoose.hackathon.traditional.builders.LogInAction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

public class DynamicContentTest extends VisualTest {
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
    @DisplayName("Dashboard should show two ads")
    public void pageShouldHaveTwoAds(TestInfo testInfo) {
        hack.eyesHelper.startEyes(testInfo.getDisplayName());
        hack.eyes.checkWindow("two_ads");
        hack.eyesHelper.stopEyes();
    }
}
