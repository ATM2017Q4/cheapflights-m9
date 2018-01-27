package com.cheapflights.ui.page.pageobjects;

import com.cheapflights.ui.page.abstractpages.AbstractHomePage;
import com.cheapflights.ui.page.blocks.PrefilledSearchFormBlock;
import org.openqa.selenium.WebDriver;


public class PrefilledHomePage extends AbstractHomePage {

    public PrefilledHomePage(WebDriver driver) {
        super(driver);
    }

    private PrefilledSearchFormBlock searchForm;


    public PrefilledHomePage chooseOrigin(String from) {
        searchForm.searchOrigin(from);
        return this;
    }

    public PrefilledHomePage chooseDestination(String to) {
        searchForm.searchDestination(to);
        return this;
    }

    public PrefilledHomePage chooseDates(String period, String startDate, String endDate) {
        searchForm.searchDates(period, startDate, endDate);
        return this;
    }

    public PrefilledHomePage increaseNumberOfAdults(int number) {
        searchForm.increaseNumberOfAdults(number);
        return this;
    }


}


