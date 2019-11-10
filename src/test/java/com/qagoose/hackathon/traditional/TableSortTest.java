package com.qagoose.hackathon.traditional;

import com.qagoose.hackathon.traditional.builders.LogInAction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.FileNotFoundException;
import java.util.HashMap;

public class TableSortTest extends TraditionalTest {
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
    public void userShouldBeAbleToSortRecentTransactionsByAmount() throws FileNotFoundException {
        HashMap<String, RecentTransactionRow> originalRows;
        HashMap<String, RecentTransactionRow> amountOrderedRows;
        HashMap<String, Integer> expectedOrder = new HashMap<>();

        HashMap<String, RecentTransactionRow> expectedRowData = hack.dataLoader.loadRecentTransactions("recentTransactionsSortAmountData.json");


        // Store the original data away for later
        originalRows = hack.pages.dashboardPage.getRecentTransactionRows();

        expectedOrder.put("MailChimp Services", 1);
        expectedOrder.put("Ebay Marketplace", 2);
        expectedOrder.put("Shopify product", 3);
        expectedOrder.put("Templates Inc", 4);
        expectedOrder.put("Stripe Payment Processing", 5);
        expectedOrder.put("Starbucks coffee", 6);

        hack.pages.dashboardPage.sortRecentTransactionsBy(RecentTransactionsSortType.AMOUNT);
        amountOrderedRows = hack.pages.dashboardPage.getRecentTransactionRows();

        hack.pages.dashboardPage.checkTransactionRowsMatchExpected(
                expectedRowData,
                amountOrderedRows
        );

        hack.pages.dashboardPage.checkRecentTransactionsAreInOrder(
                amountOrderedRows,
                expectedOrder
        );


    }
}
