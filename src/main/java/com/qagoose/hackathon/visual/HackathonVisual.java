package com.qagoose.hackathon.visual;

import com.applitools.eyes.selenium.Eyes;
import com.qagoose.hackathon.traditional.BaseDriver;
import com.qagoose.hackathon.traditional.Hackathon;

public class HackathonVisual extends Hackathon {
    public Eyes eyes;
    public EyesWrapper eyesHelper;

    public HackathonVisual(BaseDriver baseDriver, String appName) {
        super(baseDriver);
        eyes = new Eyes();

        if(System.getenv("APPLITOOLS_API_KEY") != null) {
            eyes.setApiKey(System.getenv("APPLITOOLS_API_KEY"));
        } else if(System.getProperty("APPLITOOLS_API_KEY") != null) {
            eyes.setApiKey(System.getProperty("APPLITOOLS_API_KEY"));
        }

        eyesHelper = new EyesWrapper(eyes, baseDriver.getDriver(), appName);
    }
}
