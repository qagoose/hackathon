package com.qagoose.hackathon.traditional;

import com.qagoose.hackathon.traditional.pages.CompareExpensesPage;
import com.qagoose.hackathon.traditional.pages.DashboardPage;
import com.qagoose.hackathon.traditional.pages.LoginPage;

public class Pages {
    private BaseDriver baseDriver;
    public LoginPage loginPage;
    public DashboardPage dashboardPage;
    public CompareExpensesPage compareExpensesPage;

    public Pages(BaseDriver baseDriver) {
            this.baseDriver = baseDriver;
            loginPage = new LoginPage(baseDriver);
            dashboardPage = new DashboardPage(baseDriver);
            compareExpensesPage = new CompareExpensesPage(baseDriver);
    }
}