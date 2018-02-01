package com.cheapflights.ui.page.abstractpages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementDecorator;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementLocatorFactory;

import java.util.logging.Logger;

public abstract class AbstractSearchPage {

    protected static WebDriver driver;

    protected Logger logger = Logger.getLogger(this.getClass().getName());

    public AbstractSearchPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new HtmlElementDecorator(new HtmlElementLocatorFactory(this.driver)), this);
    }

    public abstract AbstractSearchPage chooseNonStopFlights();

    public abstract AbstractSearchPage modifyDuration(int divider, int multiplier);

    public AbstractSearchPage sortByCheapest() {
        return this;
    }

    public abstract int getCheapestFlight();

    public void closeFilters() {
    }

    public static WebDriver getDriver() {
        return driver;
    }


}
