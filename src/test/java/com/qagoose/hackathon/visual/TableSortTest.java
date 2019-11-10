package com.qagoose.hackathon.visual;

import com.qagoose.hackathon.traditional.RecentTransactionsSortType;
import com.qagoose.hackathon.traditional.builders.LogInAction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

import java.io.FileNotFoundException;

public class TableSortTest extends VisualTest {
    @BeforeEach
    public void logIn() {
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
    @DisplayName("User should be able to sort recent transactions by amount")
    public void userShouldBeAbleToSortRecentTransactionsByAmount(TestInfo testInfo) throws FileNotFoundException {
        // Pre sort screenshot
        hack.eyesHelper.startEyes(testInfo.getDisplayName());
        hack.eyes.checkWindow("sort_recent_transactions_before_amount");

        // Sort
        hack.pages.dashboardPage.sortRecentTransactionsBy(RecentTransactionsSortType.AMOUNT);

        // Post sort screenshot
        hack.eyes.checkWindow("sort_recent_transactions_after_amount");
        hack.eyesHelper.stopEyes();
    }
}
