package com.epam.selenium.pages.abstractpages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public abstract class AbstractSearchPage extends AbstractPage{
    public AbstractSearchPage(WebDriver driver) {
        super(driver);
    }

    private static String cheapestFlightXpath;

    public AbstractSearchPage chooseNonStopFlights() {return this;}

    public AbstractSearchPage modifyDuration(int divider, int multiplier) { return this;}

    public AbstractSearchPage sortByCheapest() { return this;}

    public String getCheapestFlight() {
        return getDriver().findElement(By.xpath(cheapestFlightXpath)).getText();
    }

    public void setCheapestFlight(String cheapestFlightXpath){
        this.cheapestFlightXpath = cheapestFlightXpath;
    }
    public String getCheapestFlightXpath(){
        return cheapestFlightXpath;
    }

    public void closeFilters(){

    }



}
