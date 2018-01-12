package com.epam.selenium.pages.tests;

import com.epam.selenium.pages.abstractpages.AbstractHomePage;
import com.epam.selenium.pages.factory.HomePageFactory;
import com.epam.selenium.pages.factory.SearchPageFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.AfterClass;

import java.util.concurrent.TimeUnit;

public class CheapFlightsTest {

    private WebDriver driver;
    private AbstractHomePage homePage;


    @BeforeClass
    public void launchBrowser() {
        System.setProperty("webdriver.gecko.driver", "./src/main/resources/geckodriver");
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        driver.manage().window().maximize();

    }

    @Parameters({"url"})
    @BeforeClass(dependsOnMethods = "launchBrowser", description = "Add implicit wait and maximize window")
    public void openUrl(String url) {
        driver.get(url);

    }

    @Parameters({"origin", "destination", "period",
            "startDate", "endDate", "numberOfAdults",
            "sliderDivider", "sliderMultiplier", "sumPattern"})
    @Test(description = "Fill in form and get the cheapest flight")
    public void chooseTheCheapestFlight(String origin, String destination, String period,
                                        String startDate, String endDate, int numberOfAdults,
                                        int sliderDivider, int sliderMultiplier, String sumPattern) {
        HomePageFactory pageFactory = new HomePageFactory(driver);
        new SearchPageFactory();
        homePage = pageFactory.getCorrectPage(driver);
        homePage.chooseOrigin(origin)
                .chooseDestination(destination)
                .chooseDates(period, startDate, endDate)
                .increaseNumberOfAdults(numberOfAdults)
                .submitForm()
                .chooseNonStopFlights()
                .modifyDuration(sliderDivider, sliderMultiplier)
                .sortByCheapest()
                .closeFilters();

        Assert.assertTrue(SearchPageFactory.getCorrectPage(driver).getCheapestFlight().matches(sumPattern));

    }


    @AfterClass(description = "Close browser")
    public void tearDown() {
        driver.quit();
    }


}
