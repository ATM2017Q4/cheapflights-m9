package com.epam.selenium.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import java.util.logging.Level;

public class FirstFlightSearchPage extends Page {

    public FirstFlightSearchPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(name = "2")
    private WebElement twoStops;

    @FindBy(name = "1")
    private WebElement oneStop;

    @FindBy(xpath = "//div[@aria-label='Maximum flight duration']")
    private WebElement slider;

    @FindBy(xpath = "//div[contains(@id, 'legdur-content')]//div[contains(@class,'activeRange')]")
    private WebElement progress;

    @FindBy(xpath = "//div[@data-name='sort-section']")
    private WebElement sortSection;

    @FindBy(xpath = "//div[@data-name='sort-section']//div//div/span")
    private WebElement sortSectionValue;

    @FindBy(xpath = "//li[@data-title='Cheapest']")
    private WebElement cheapestFlights;

    @FindBy(css = "ul[class = 'dropdownList']")
    private WebElement sortingList;

    private By loadComplete = By.xpath("//div[@class='resultsContainer']/div[contains(@id, 'cover')]");

    public FirstFlightSearchPage chooseNonstopFlights() {
        try {
            waitForVisibilityFluently(twoStops, 100, 5);
        } catch (org.openqa.selenium.TimeoutException e) {
            logger.log(Level.SEVERE, "Driver was unable to locate the element during the specified amount of time", e);
        } catch (org.openqa.selenium.NoSuchElementException e) {
            logger.log(Level.SEVERE, "Driver was not able to find the element by the specified locator." + e);
        } finally {
            waitForVisibilityFluently(twoStops, 100, 5);
            oneStop.click();
            waitForJSandJQueryToLoad();
            twoStops.click();
            waitForJSandJQueryToLoad();

        }
        return this;
    }

    public FirstFlightSearchPage modifyDuration(int divider, int multiplier) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", slider);
        Dimension size = progress.getSize();
        int sliderWidth = size.getWidth();

        Actions builder = new Actions(driver);
        builder.click()
                .dragAndDropBy
                        (slider, -((sliderWidth / divider) * multiplier), 0)
                .build()
                .perform();
        waitForJSandJQueryToLoad();
        //new WebDriverWait(driver,10).until(ExpectedConditions.attributeToBe(load, "class", "resultsListCover tl"));
        return this;
    }

    public FirstFlightSearchPage sortByCheapest() {
        if (sortSectionValue.getText().equals("Cheapest")) {
        } else {
            sortSection.click();
            waitForVisibilityFluently(sortingList, 5, 1);
            cheapestFlights.click();
            waitForAttributeToBe(loadComplete, "class", "resultsListCover tl", 20);

        }
        return this;
    }

    public String getElementText(String xpath) {
        return driver.findElement(By.xpath(xpath)).getText();
    }
}
