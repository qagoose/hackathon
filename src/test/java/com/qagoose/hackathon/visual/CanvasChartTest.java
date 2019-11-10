package com.qagoose.hackathon.visual;

import com.qagoose.hackathon.traditional.builders.LogInAction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

public class CanvasChartTest extends VisualTest {

    @BeforeEach
    public void getToCompareExpensesPage() {
        hack.pages.loginPage.logIn(
                LogInAction
                        .builder()
                        .withUsername("Will")
                        .withPassword("Password1")
                        .assertDashboardLoads()
                        .build()
        );

        hack.pages.dashboardPage.clickCompareExpenses();
    }

    @Test
    @DisplayName("User should see correct compare expenses data, including next year")
    public void checkUserSeesCorrectCompareExpensesDataIncludingNextYear(TestInfo testInfo) {
       hack.eyesHelper.startEyes(testInfo.getDisplayName());

       // Two years
        hack.eyes.checkWindow("compare_expenses_two_year");

        hack.pages.compareExpensesPage.clickShowDataForNextYear();

        // Three years
        hack.eyes.checkWindow("compare_expenses_three_year");

        hack.eyesHelper.stopEyes();
    }
}
