package com.epam.selenium.pages.factory;

import com.epam.selenium.pages.abstractpages.AbstractSearchPage;
import com.epam.selenium.pages.desktop.FirstFlightSearchPage;
import com.epam.selenium.pages.desktop.SecondFlightSearchPage;
import org.openqa.selenium.WebDriver;


public class SearchPageFactory {

    static String searchPageUrl = "https://global.cheapflights.com/flight-search/MOW-TYO";


    public static AbstractSearchPage getCorrectPage(WebDriver driver){

        AbstractSearchPage page;
        if (driver.getCurrentUrl().contains(searchPageUrl))  {
            page = new FirstFlightSearchPage(driver);
        }else {
            page = new SecondFlightSearchPage(driver);
        }
        return page;

    }
}
