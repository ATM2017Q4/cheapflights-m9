package com.cheapflights.ui.utils.webdrivertools;

import org.openqa.selenium.WebDriver;

public class Wait implements IWait {

    private static WebDriver driver;

    public Wait(WebDriver driver) {
        this.driver = driver;
    }

    @Override
    public boolean waitForExpectedCondition(){
      return true;
    }

    public static WebDriver getDriver() {
        return driver;
    }
}
