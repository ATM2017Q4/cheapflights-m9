package com.cheapflights.ui.utils.webdrivertools;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ExplicitWaitDecorator extends WaitDecorator {

    private WebElement webElement;
    private int timeout;
    private By by;
    private String attribute;
    private String value;

    public ExplicitWaitDecorator(Wait wait, WebElement webElement, int timeout) {
        super(wait);
        this.webElement = webElement;
        this.timeout = timeout;
    }

    public ExplicitWaitDecorator(Wait wait, By by, String attribute, String value, int timeout) {
        super(wait);
        this.by = by;
        this.attribute = attribute;
        this.value = value;
        this.timeout = timeout;
    }

    @Override
    public boolean waitForExpectedCondition() {
        if (by != null) {
            return waitForAttributeToBe(by, attribute, value, timeout);
        } else {
            return waitForInvisibility(webElement, timeout);
        }
    }

    public static boolean waitForInvisibility(WebElement element, int timeout) {
        return new WebDriverWait(Wait.getDriver(), timeout).until(ExpectedConditions.invisibilityOf(element));
    }

    public static boolean waitForAttributeToBe(By by, String attribute, String value, int timeout) {
        return new WebDriverWait(Wait.getDriver(), timeout).until(ExpectedConditions.attributeToBe(by, attribute, value));
    }
}
