package com.epam.selenium.pages.abstractpages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AbstractSearchPage extends AbstractPage{
    public AbstractSearchPage(WebDriver driver) {
        super(driver);
    }

    public AbstractSearchPage chooseNonStopFlights() {return this;}

    public AbstractSearchPage modifyDuration(int divider, int multiplier) { return this;}

    public AbstractSearchPage sortByCheapest() { return this;}

    public String getElementText(String xpath) {
        return driver.findElement(By.xpath(xpath)).getText();
    }

    public AbstractSearchPage closeFilters(){
        return this;
    }

}
