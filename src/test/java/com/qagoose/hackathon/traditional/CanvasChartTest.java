package com.qagoose.hackathon.traditional;

import com.qagoose.hackathon.traditional.builders.LogInAction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import java.io.IOException;

public class CanvasChartTest extends TraditionalTest {

    @BeforeEach
    public void getToCompareExpensesPage() throws IOException, InterruptedException {
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
    public void checkUserSeesCorrectCompareExpensesDataIncludingNextYear() throws IOException, InterruptedException {
        // Here's where I nope out trying to figure out a non-visual way of interacting with a canvas.
        // Let's compare some SCREENSHOTS!!!
        // This is mostly demonstrating that you can do visual testing without a $500 a month tool.  It might not be
        // great, but you can do it

        // Wait for a bit, I don't want to create a retry system for the screenshot comparer, though it could be done.
        // Very lazy today
        Thread.sleep(5000);

        ScreenshotComparer.compareScreenshots(
                "two_year_expenses",
                baseDriver,
                baseDriver.getDriver().findElement(By.id("canvas")), // Sssshhhh don't look at it
                false
        );

        hack.pages.compareExpensesPage.clickShowDataForNextYear();

        // Additional lazy wait
        Thread.sleep(5000);

        ScreenshotComparer.compareScreenshots(
                "three_year_expenses",
                baseDriver, baseDriver.getDriver().findElement(By.id("canvas")),
                false
        );
    }
}
