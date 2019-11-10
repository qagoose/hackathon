package com.qagoose.hackathon.visual;
import com.applitools.eyes.BatchInfo;
import com.qagoose.hackathon.traditional.BaseDriver;
import com.qagoose.hackathon.traditional.Hackathon;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

public class VisualTest {
    private static BatchInfo batch;
    public static final String APP_NAME = "Hackathon";
    private static final String HACKATHON_URL = "https://demo.applitools.com/hackathonV2.html";
    protected BaseDriver baseDriver;
    public HackathonVisual hack;

    @BeforeAll
    public static void setBatch() {
        batch = new BatchInfo("Hackathon");
        batch.setId(System.getProperty("EYES_BATCH_ID"));
    }

    @BeforeEach
    public void visualTestSetup() {
        baseDriver = new BaseDriver();
        baseDriver.startDriver();
        baseDriver.getDriver().get(HACKATHON_URL);
        hack = new HackathonVisual(baseDriver, APP_NAME);
        hack.eyes.setBatch(batch);
        hack.eyes.setForceFullPageScreenshot(true);
    }

    @AfterEach
    public void tearDown() {
        baseDriver.stopDriver();
        hack.eyes.abortIfNotClosed();
    }
}

