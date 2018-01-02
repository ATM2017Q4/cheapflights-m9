package com.epam.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class SecondFligthtSearchPage extends Page {
    public SecondFligthtSearchPage(WebDriver driver){
        super(driver);
    }

    @FindBy(xpath = "//label[contains(text(), 'Cheapest')]")
    private WebElement cheapestFlight;

    @FindBy(css = "a[data-index='3']")
    private WebElement stopsFilter;

    @FindBy(css = "label[for='filters-stops-multi']")
    private WebElement multiStops;

    @FindBy(css = "label[for='filters-stops-one']")
    private WebElement oneStop;

    @FindBy(css = "a[data-index='2']")
    private WebElement durationFilter;

    @FindBy(css = "div[id='duration-slider-outbound'] div[class='handle']")
    private WebElement slider;

    @FindBy(css = "div[id='duration-slider-outbound'] div[class='slider-bar']")
    private WebElement progress;

    @FindBy(css = "span[class='handle-top-items']>i[class='icon-arrow-down']")
    private WebElement closeButton;

    @FindBy(id = "update-indicator")
    private WebElement updateIndicator;

    public SecondFligthtSearchPage chooseNonStopFligths(){
        try {
            waitForVisibilityFluently(cheapestFlight, 300, 10);
        }catch(org.openqa.selenium.NoSuchElementException e){
            waitForVisibilityFluently(cheapestFlight, 100, 10);
        }

        stopsFilter.click();
        waitForClickabilityFluently(oneStop, 10, 1);
        oneStop.click();
        multiStops.click();
        return this;
    }

    public SecondFligthtSearchPage modifyDuration(int divider, int multiplier){
        durationFilter.click();
        Dimension size = progress.getSize();
        int sliderWidth = size.getWidth();
        Actions builder = new Actions(driver);
        builder
                .dragAndDropBy
                        (slider, -((sliderWidth / divider) * multiplier), 0)
                .build()
                .perform();
        return this;

    }

    public SecondFligthtSearchPage closeFilters(){
        closeButton.click();
        waitForInvisibilityExplicitly(updateIndicator, 10);
        return this;
    }

    public String getElementText(String xpath){
        return driver.findElement(By.xpath(xpath)).getText();
    }
}
