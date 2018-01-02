package com.epam.selenium;

import com.epam.selenium.pages.EmptyHomePage;
import com.epam.selenium.pages.FirstFlightSearchPage;
import com.epam.selenium.pages.PrefilledHomePage;
import com.epam.selenium.pages.SecondFligthtSearchPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.util.concurrent.TimeUnit;

public class CheapFlightsTest {

    private WebDriver driver;

    private final String URL = "https://cheapflights.com/";
    private final String SEARCHPAGEURL = "https://global.cheapflights.com/flight-search/MOW-TYO/";

    private final String DOLLARSIGN = "$";
    private String sumPattern = "[\\$\\r\\n]*(([0-5][0-4][0-9])|([0-4][0-9][0-9]))[.\\r\\n]*";
    private String currencySymbol = "//div[@class='quicklink cheapest clearfix selected-filter']//span[@class='symbol']";
    private String sum = "//div[@class='quicklink cheapest clearfix selected-filter']//span[@class='value']";
    private String cheapestFlight = "(//div[@class='above-button']//a[@class='booking-link']/span[@class='price option-text'])[1]";

    @BeforeClass
    public void launchBrowser() {
        System.setProperty("webdriver.gecko.driver", "./src/main/resources/geckodriver");
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @BeforeClass(dependsOnMethods = "launchBrowser", description = "Add implicit wait and maximize window")
    public void openUrl() {
        driver.get(URL);

    }

    @Test(description = "Fill in form on the empty Home Page")
    public void fillInForm() {
        WebElement origin = driver.findElement(By.name("origin"));
        if (origin.getAttribute("value").equals("")) {
            EmptyHomePage hp1 = new EmptyHomePage(driver);
            hp1.chooseOrigin("Moscow")
                    .chooseDestination("Tokyo")
                    .chooseStartDate("October, 2018", "7")
                    .chooseEndDate("20")
                    .increaseNumberOfAdults(2)
                    .submitForm();
        } else {
            PrefilledHomePage hp2 = new PrefilledHomePage(driver);
            hp2.chooseOrigin("Moscow")
                    .chooseDestination("Tokyo")
                    .chooseDates("October 2018", "7", "20")
                    .increaseNumberOfAdults(2)
                    .submitForm();

        }

    }

    @Test(description = "Filter results", dependsOnMethods = "fillInForm")
    public void filterResults() throws InterruptedException {
        FirstFlightSearchPage sp1 = new FirstFlightSearchPage(driver);
        SecondFligthtSearchPage sp2 = new SecondFligthtSearchPage(driver);
        for (String winHandle : driver.getWindowHandles()) {
            driver.switchTo().window(winHandle);
        }
       // Thread.sleep(10000);

        new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//div[contains(@class, 'logo')]//a[@href='/']"))));
        if (driver.getCurrentUrl().contains(SEARCHPAGEURL)) {

            sp1.chooseNonstopFlights()
                    .modifyDuration(4, 3)
                    .sortByCheapest();
            System.out.println(sp1.getElementText(cheapestFlight));
            Assert.assertTrue(sp1.getElementText(cheapestFlight).matches(sumPattern));
        } else {
            sp2.chooseNonStopFligths()
                    .modifyDuration(4, 3)
                    .closeFilters();
            Assert.assertTrue(sp2.getElementText(currencySymbol).equals(DOLLARSIGN));
            Assert.assertTrue(sp2.getElementText(sum).matches(sumPattern));
        }
    }


}
