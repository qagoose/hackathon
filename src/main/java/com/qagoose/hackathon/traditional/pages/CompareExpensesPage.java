package com.qagoose.hackathon.traditional.pages;

import com.qagoose.hackathon.traditional.BaseDriver;
import com.qagoose.hackathon.traditional.BasePage;
import org.openqa.selenium.By;

public class CompareExpensesPage extends BasePage {

    public CompareExpensesPage(BaseDriver baseDriver) {
        super(baseDriver);
    }

    public void clickShowDataForNextYear() {
        clickElement(By.id("addDataset"));
    }
}
