package com.qagoose.hackathon.traditional;

public class Hackathon {
    public Pages pages;
    public DataLoader dataLoader;

    public Hackathon(BaseDriver baseDriver) {
        pages = new Pages(baseDriver);
        dataLoader = new DataLoader();
    }
}
