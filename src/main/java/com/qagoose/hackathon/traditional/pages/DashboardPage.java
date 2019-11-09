package com.qagoose.hackathon.traditional.pages;

import com.qagoose.hackathon.traditional.BaseDriver;
import com.qagoose.hackathon.traditional.BasePage;
import com.qagoose.hackathon.traditional.RecentTransactionRow;
import com.qagoose.hackathon.traditional.RecentTransactionsSortType;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DashboardPage extends BasePage {
    public DashboardPage(BaseDriver baseDriver) {
        super(baseDriver);
    }

    public void sortRecentTransactionsBy(RecentTransactionsSortType sortType) {
        By sortBy;

        switch (sortType) {
            case AMOUNT:
                sortBy = By.id("amount");
                break;

            default:
                throw new RuntimeException(String.format("Unable to sort by '%s'", sortType.name()));
        }

        clickElement(sortBy);

        // Hard to know what we should be waiting for here.  As this page is instant, let's just wait a second
        // Yes I know this is bad
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public HashMap<String, RecentTransactionRow> getRecentTransactionRows() {
        HashMap<String, RecentTransactionRow> transactionRows = new HashMap();
        ArrayList<WebElement> htmlRows = (ArrayList<WebElement>) getElements(By.cssSelector("#transactionsTable tbody tr"));
        int position = 1;


        for (WebElement htmlRow : htmlRows) {
            ArrayList<String> columnTexts = getTransactionRowColumnText(htmlRow);

            RecentTransactionRow row = new RecentTransactionRow(
                    columnTexts.get(0),
                    columnTexts.get(1),
                    columnTexts.get(2),
                    columnTexts.get(3),
                    columnTexts.get(4),
                    position
            );

            transactionRows.put(columnTexts.get(2), row);

            position += 1;
        }

        return transactionRows;
    }

    private ArrayList<String> getTransactionRowColumnText(WebElement row) {
        ArrayList<String> columnTexts = new ArrayList<>();
        ArrayList<WebElement> columns = (ArrayList<WebElement>) getElements(By.tagName("td"), row);

        for (WebElement column : columns) {
            columnTexts.add(column.getText());
        }

        return columnTexts;
    }

    public void checkRecentTransactionsAreInOrder(HashMap<String, RecentTransactionRow> currentRows, HashMap<String, Integer> expectedOrder) {
        for (Map.Entry<String, Integer> entry : expectedOrder.entrySet()) {
            assertEquals(currentRows.get(entry.getKey()).getPosition(), (int) entry.getValue(), String.format(
                    "Expected '%s' to be at position '%d', but it was not.",
                    entry.getKey(),
                    entry.getValue()
            ));
        }
    }

    public void checkTransactionRowsMatchExpected(HashMap<String, RecentTransactionRow> expectedRows, HashMap<String, RecentTransactionRow> actualRows) {
        for(Map.Entry<String, RecentTransactionRow> entry : expectedRows.entrySet()) {
            String key = entry.getKey();
            RecentTransactionRow expectedRow = entry.getValue();

            RecentTransactionRow actualRow = actualRows.get(key);

            if(actualRow == null) {
                throw new RuntimeException(String.format("Could not find actual row for '%s'!", key));
            }

            System.out.println("Expected row: " + expectedRow.toString());
            System.out.println("Actual row: " + actualRow.toString());

            assertTrue(expectedRow.equals(actualRow), "Rows did not match!");
        }
    }

    public void clickCompareExpenses() {
        clickElement(By.id("showExpensesChart"));
    }

    public void checkAdOneIsPresent() {
        checkAdIsPresent(By.cssSelector("#flashSale img"));
    }

    public void checkAdTwoIsPresent() {
        checkAdIsPresent(By.cssSelector("#flashSale2 img"));
    }

    private void checkAdIsPresent(By by) {
        WebElement image = waitForElementDisplayed(by);
        assertTrue(hasImageLoaded(image));
    }
}
