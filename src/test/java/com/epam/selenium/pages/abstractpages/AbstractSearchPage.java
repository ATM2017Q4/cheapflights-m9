package com.epam.selenium.pages.abstractpages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import java.util.logging.Logger;

public abstract class AbstractSearchPage {

    protected static WebDriver driver;

    protected Logger logger = Logger.getLogger(this.getClass().getName());

    public AbstractSearchPage(WebDriver driver) {

        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    private static String cheapestFlightXpath;

    public AbstractSearchPage chooseNonStopFlights() {
        return this;
    }

    public AbstractSearchPage modifyDuration(int divider, int multiplier) {
        return this;
    }

    public AbstractSearchPage sortByCheapest() {
        return this;
    }

    public String getCheapestFlight() {
        return driver.findElement(By.xpath(cheapestFlightXpath)).getText();
    }

    public void setCheapestFlight(String cheapestFlightXpath) {
        this.cheapestFlightXpath = cheapestFlightXpath;
    }

    public String getCheapestFlightXpath() {
        return cheapestFlightXpath;
    }

    public void closeFilters() {

    }


}
