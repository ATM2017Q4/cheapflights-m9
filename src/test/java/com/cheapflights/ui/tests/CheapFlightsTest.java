package com.cheapflights.ui.tests;

import com.cheapflights.ui.entities.TravelInfo;
import com.cheapflights.ui.page.abstractpages.AbstractHomePage;
import com.cheapflights.ui.page.factory.HomePageFactory;
import com.cheapflights.ui.page.factory.SearchPageFactory;
import com.cheapflights.ui.utils.FileSearchUtil;
import com.cheapflights.ui.utils.JsonUtil;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.List;
import java.util.concurrent.TimeUnit;


public class CheapFlightsTest {

    private WebDriver driver;
    private AbstractHomePage homePage;
    private String url = "https://cheapflights.com/";


    @BeforeClass(alwaysRun = true)
    public void launchBrowser() {
        System.setProperty("webdriver.gecko.driver", "./src/main/resources/geckodriver");
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();

    }

    @BeforeClass(dependsOnMethods = "launchBrowser", description = "Add implicit wait and maximize window", alwaysRun = true)
    public void openUrl() {
        driver.get(url);

    }


    @Test(description = "Fill in form and get the cheapest flight", dataProvider = "dp")
    public void chooseTheCheapestFlight(TravelInfo travelInfo) {
        HomePageFactory pageFactory = new HomePageFactory(driver);

        homePage = pageFactory.getCorrectPage(driver);
        homePage.chooseOrigin(travelInfo.getOrigin())
                .chooseDestination(travelInfo.getDestination())
                .chooseDates(travelInfo.getDepartureDates().getMonth(), Integer.toString(travelInfo.getDepartureDates().getDay()), Integer.toString(travelInfo.getReturnDates().getDay()))
                .increaseNumberOfAdults(travelInfo.getNumberOfAdults())
                .submitForm()
                .chooseNonStopFlights()
                .modifyDuration(4, 3)
                .sortByCheapest()
                .closeFilters();
        Assert.assertTrue(SearchPageFactory.getCorrectPage(driver).getCheapestFlight() <= travelInfo.getAcceptablePrice());

    }

    @AfterClass(description = "Close browser", alwaysRun = true)
    public void tearDown() {
        driver.quit();
    }

    @DataProvider(name = "dp")
    public static Object[][] getData() {
        List<String> files = FileSearchUtil.getDirectoryFiles("./src/main/resources", ".json");
        Object[][] object = null;
        for (int i = 0; i < files.size(); i++) {
            object =  new Object[][]{
                    new Object[]{JsonUtil.readJson(files.get(i), TravelInfo.class)}
            };
        }
        System.out.println(object.length);
        return object;
    }
    ITes
}
