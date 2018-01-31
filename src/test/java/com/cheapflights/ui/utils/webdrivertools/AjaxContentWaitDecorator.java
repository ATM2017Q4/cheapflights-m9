package com.cheapflights.ui.utils.webdrivertools;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AjaxContentWaitDecorator extends WaitDecorator {

    public AjaxContentWaitDecorator(Wait wait) {
        super(wait);
    }

    @Override
    public boolean waitForExpectedCondition() {
        return waitForJSandJQueryToLoad(Wait.getDriver());
    }

    public static boolean waitForJSandJQueryToLoad(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        ExpectedCondition<Boolean> jQueryLoad = driver1 -> {
            try {
                return ((Long) ((JavascriptExecutor) driver1).executeScript("return jQuery.active") == 0);
            } catch (Exception e) {
                return true;
            }
        };
        ExpectedCondition<Boolean> jsLoad = driver12 -> ((JavascriptExecutor) driver12).executeScript("return document.readyState")
                .toString().equals("complete");
        return wait.until(jQueryLoad) && wait.until(jsLoad);
    }
}
