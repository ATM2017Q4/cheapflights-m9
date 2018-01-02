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
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class CheapFlightsTest {

    private WebDriver driver;

    @BeforeClass
    public void launchBrowser() {
        System.setProperty("webdriver.gecko.driver", "./src/main/resources/geckodriver");
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Parameters({"url"})
    @BeforeClass(dependsOnMethods = "launchBrowser", description = "Add implicit wait and maximize window")
    public void openUrl(String url) {
        driver.get(url);

    }

    @Parameters({"origin", "destination", "period", "startDate", "endDate", "numberOfAdults"})
    @Test(description = "Fill in form on the empty Home Page")
    public void fillInForm(String origin, String destination, String period, String startDate, String endDate, int numberOfAdults) {
        WebElement originField = driver.findElement(By.name("origin"));
        if (originField.getAttribute("value").equals("")) {
            EmptyHomePage hp1 = new EmptyHomePage(driver);
            hp1.chooseOrigin(origin)
                    .chooseDestination(destination)
                    .chooseStartDate(period, startDate)
                    .chooseEndDate(endDate)
                    .increaseNumberOfAdults(numberOfAdults)
                    .submitForm();
        } else {
            PrefilledHomePage hp2 = new PrefilledHomePage(driver);
            hp2.chooseOrigin(origin)
                    .chooseDestination(destination)
                    .chooseDates(period, startDate, endDate)
                    .increaseNumberOfAdults(numberOfAdults)
                    .submitForm();

        }

    }
    @Parameters({"searchPageUrl", "dollarSign", "sumPattern", "currencySymbolXpath", "sumXpath", "cheapestFlightXpath"})
    @Test(description = "Filter results", dependsOnMethods = "fillInForm")
    public void filterResults(String searchPageUrl, String dollarSign, String sumPattern, String currencySymbolXpath, String sumXpath, String cheapestFlightXpath) {
        FirstFlightSearchPage sp1 = new FirstFlightSearchPage(driver);
        SecondFligthtSearchPage sp2 = new SecondFligthtSearchPage(driver);
        for (String winHandle : driver.getWindowHandles()) {
            driver.switchTo().window(winHandle);
        }

        new WebDriverWait(driver, 20).until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[contains(@class, 'logo')]//a[@href='/']"))));
        if (driver.getCurrentUrl().contains(searchPageUrl)) {

            sp1.chooseNonstopFlights()
                    .modifyDuration(4, 3)
                    .sortByCheapest();
            Assert.assertTrue(sp1.getElementText(cheapestFlightXpath).matches(sumPattern));
        } else {
            sp2.chooseNonStopFligths()
                    .modifyDuration(4, 3)
                    .closeFilters();
            Assert.assertTrue(sp2.getElementText(currencySymbolXpath).equals(dollarSign));
            Assert.assertTrue(sp2.getElementText(sumXpath).matches(sumPattern));
        }
    }

    @AfterClass(description = "Close browser")
    public void tearDown() {
        driver.quit();
    }


}
