package com.cheapflights.ui.page.pageobjects;

import com.cheapflights.ui.page.blocks.FiltersBlock;
import com.cheapflights.ui.utils.WebDriverTools;
import com.cheapflights.ui.page.abstractpages.AbstractSearchPage;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import java.util.logging.Level;

public class FirstFlightSearchPage extends AbstractSearchPage {

    public FirstFlightSearchPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//div[@class='Common-Results-ProgressBar Hidden']")
    private WebElement progressBar;

    private FiltersBlock filtersBlock;

    private By loadComplete = By.xpath("//div[@class='resultsContainer']/div[contains(@id, 'cover')]");

    private static String cheapestFlightXpath = "(//div[@class='above-button']//a[@class='booking-link']/span[@class='price option-text'])[1]";

    public FirstFlightSearchPage chooseNonStopFlights() {
        try {
            WebDriverTools.waitForInvisibilityExplicitly(driver, progressBar, 100);
        } catch (org.openqa.selenium.TimeoutException e) {
            logger.log(Level.SEVERE, "Driver was unable to locate the element during the specified amount of time", e);
        } catch (org.openqa.selenium.NoSuchElementException e) {
            logger.log(Level.SEVERE, "Driver was not able to find the element by the specified locator." + e);
        } finally {
            filtersBlock.chooseNonStopFlights();
        }
        return this;
    }

    public FirstFlightSearchPage modifyDuration(int divider, int multiplier) {
        filtersBlock.modifyDuration(divider, multiplier);
        return this;
    }

    public FirstFlightSearchPage sortByCheapest() {
        filtersBlock.sortByCheapest();
        WebDriverTools.waitForAttributeToBe(driver, loadComplete, "class", "resultsListCover tl", 20);
        return this;
    }

    public int getCheapestFlight() {
        String[] price;
        int sum;
        String cheapestFlight = driver.findElement(By.xpath(cheapestFlightXpath)).getText();
        price = cheapestFlight.split("\\$");
        sum = Integer.parseInt(price[1]);
        return sum;
    }

}
