package com.qagoose.hackathon.traditional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public class BaseTest {
    protected BaseDriver baseDriver;
    private static final String HACKATHON_URL = "https://demo.applitools.com/hackathon.html";
    public Hackathon hack;

    @BeforeEach
    public void setup() {
        baseDriver = new BaseDriver();
        baseDriver.startDriver();
        baseDriver.getDriver().get(HACKATHON_URL);
        hack = new Hackathon(baseDriver);
    }

    @AfterEach
    public void tearDown() {
        baseDriver.stopDriver();
    }
}
