package com.cheapflights.ui.tests;

import com.cheapflights.ui.entities.TravelInfo;
import com.cheapflights.ui.page.abstractpages.AbstractHomePage;
import com.cheapflights.ui.page.factory.HomePageFactory;
import com.cheapflights.ui.page.factory.SearchPageFactory;
import com.cheapflights.ui.utils.FileSearchUtil;
import com.cheapflights.ui.utils.JsonUtil;
import com.cheapflights.ui.utils.RandomUtil;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.List;
import java.util.concurrent.TimeUnit;


public class CheapFlightsTest {

    private WebDriver driver;
    private AbstractHomePage homePage;
    private final String url = "https://cheapflights.com/";
    private static String folderPath = "./src/main/resources/travel-info-files";
    private static String extension = ".json";
    TravelInfo travelInfo;

    @Factory(dataProvider = "dp2")
    public CheapFlightsTest(TravelInfo travelInfo){
        this.travelInfo=travelInfo;
    }
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

    @Test(description = "Fill in form and get the cheapest flight")
    public void chooseTheCheapestFlight() {
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

    @DataProvider(name = "dp2")
    public static Object[][] getDataTwo() {
        return new Object[][]{
                    new Object[]{JsonUtil.readJson(FileSearchUtil.getDirectoryFiles(folderPath, extension).get(0), TravelInfo.class)},
                    new Object[]{JsonUtil.readJson(FileSearchUtil.getDirectoryFiles(folderPath, extension).get(1), TravelInfo.class)}
            };

    }

    @DataProvider(name = "dp3")
    public static Object[][] getDataThree() {
        List<String> files = FileSearchUtil.getDirectoryFiles(folderPath, extension);
        Object[][] object = new Object[][]{
                new Object[]{JsonUtil.readJson(files.get(RandomUtil.generateRandom(0, files.size())), TravelInfo.class)}
        };
        return object;
    }

    @DataProvider(name = "dp1")
    public static Object[][] getDataOne() {
        List<String> files = FileSearchUtil.getDirectoryFiles(folderPath, extension);
        Object[][] object = new Object[][]{
                new Object[]{JsonUtil.readJson(files.get(0), TravelInfo.class)}
        };

        return object;
    }


}
