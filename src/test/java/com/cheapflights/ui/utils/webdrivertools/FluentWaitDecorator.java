package com.cheapflights.ui.utils.webdrivertools;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import java.util.concurrent.TimeUnit;

public class FluentWaitDecorator extends WaitDecorator {

    private WebElement webElement;
    private int timeOut;
    private int poll;

    public FluentWaitDecorator(Wait wait, WebElement webElement, int timeout, int poll) {
        super(wait);
        this.webElement = webElement;
        this.timeOut = timeout;

    }

    @Override
    public boolean waitForExpectedCondition() {
        return waitForVisibilityFluently(webElement, timeOut, poll);
    }

    public static boolean waitForVisibilityFluently(WebElement element, int timeout, int poll) {
        Object object = new FluentWait(Wait.getDriver()).withTimeout(timeout, TimeUnit.SECONDS).pollingEvery(poll, TimeUnit.SECONDS)
                .ignoring(org.openqa.selenium.NoSuchElementException.class)
                .until(ExpectedConditions.visibilityOf(element));
        return object != null ? true : false;
    }
}
