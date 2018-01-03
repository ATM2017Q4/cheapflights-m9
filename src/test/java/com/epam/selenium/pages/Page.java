package com.epam.selenium.pages;

import com.epam.selenium.annotation.ElementFactory;
import com.epam.selenium.annotation.Element;
import com.epam.selenium.annotation.LocateBy;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;


public abstract class Page {

    protected WebDriver driver;
    protected Logger logger = Logger.getLogger(this.getClass().getName());


    public Page(WebDriver driver) {

        this.driver = driver;
        PageFactory.initElements(this.driver, this);
        initElements();

    }


    public WebDriver getDriver() {
        return driver;
    }

    public void waitForVisibilityFluently(WebElement element, int timeout, int poll) {
        new FluentWait(driver).withTimeout(timeout, TimeUnit.SECONDS).pollingEvery(poll, TimeUnit.SECONDS)
                .ignoring(NoSuchElementException.class)
                .until(ExpectedConditions.visibilityOf(element));
    }

    public void waitForClickabilityFluently(WebElement element, int timeout, int poll) {
        new FluentWait(driver).withTimeout(timeout, TimeUnit.SECONDS).pollingEvery(poll, TimeUnit.SECONDS)
                .ignoring(NoSuchElementException.class)
                .until(ExpectedConditions.elementToBeClickable(element));
    }

    public void waitForInvisibilityExplicitly(WebElement element, int timeout) {
        new WebDriverWait(driver, timeout).until(ExpectedConditions.invisibilityOf(element));
    }

    public void waitForAttributeToBe(By by, String attribute, String value, int timeout) {
        new WebDriverWait(driver, timeout).until(ExpectedConditions.attributeToBe(by, attribute, value));
    }


    public boolean waitForJSandJQueryToLoad() {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                try {
                    return ((Long) ((JavascriptExecutor) getDriver()).executeScript("return jQuery.active") == 0);
                } catch (Exception e) {
                    return true;
                }
            }
        };
        ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return ((JavascriptExecutor) getDriver()).executeScript("return document.readyState")
                        .toString().equals("complete");
            }
        };
        return wait.until(jQueryLoad) && wait.until(jsLoad);
    }

    public boolean isDisplayed(List<WebElement> element) {
        boolean result;
        try {
            result = (element.size() > 0 && element.get(0).isDisplayed());

        } catch (org.openqa.selenium.NoSuchElementException e) {
            result = false;
        }
        return result;
    }

    public String getElementText(String xpath) {
        return driver.findElement(By.xpath(xpath)).getText();
    }

    public Element updateElement(final Element element, final String... values) {
        return element.updateElement(values);
    }

    public WebElement findElement(final Element element) {
        return driver.findElement(element.getLocator());
    }

    public List<WebElement> findElements(final Element element) {
        return driver.findElements(element.getLocator());
    }
    public void click(final Element element) {
        findElement(element).click();
    }
    public void initElements() {

        final List<Field> fields = new ArrayList<Field>();
        Class currentPageObject = this.getClass();

        while (currentPageObject != Page.class) {
            fields.addAll(new ArrayList<Field>(Arrays.asList(currentPageObject.getDeclaredFields())));
            currentPageObject = currentPageObject.getSuperclass();
        }

        for (Field field : fields) {
            final LocateBy fieldAnnotation = field.getAnnotation(LocateBy.class);
            final boolean accessible = field.isAccessible();

            if (fieldAnnotation != null) {
                try {
                    field.setAccessible(true);
                    field.set(this, new Element(fieldAnnotation.attribute(), fieldAnnotation.value()));
                    field.setAccessible(accessible);
                } catch (IllegalAccessException e) {
                    logger.log(Level.SEVERE, e.toString(), e);
                }
            }
        }
    }
}
