package com.epam.selenium;


import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;


public class CheapFlightsTestInitial {
    private WebDriver driver;

    @FindBy(name = "origin")
    private WebElement origin;

    @FindBy(name = "destination")
    private WebElement destination;

    @FindBy(xpath = "//a[@class='next-month']")
    private WebElement nextButton;

//    @FindBy(xpath = "//svg[@aria-label='Next month']")
//    private WebElement nextButton;


    @FindBy(xpath = "//th[@class='-monthname']/span")
    private WebElement monthName;

    @FindBy(xpath = "//div[@class='monthDisplay']")
    private WebElement monthName2;

    @FindBy(css = "svg[class='icon -departureDate']")
    private WebElement departureField;

//    @FindBy(css = "aria-label=\"Departure date input\"")
//    private WebElement departureField;


    @FindBy(css = "svg[class='icon -returnDate']")
    private WebElement arrivalField;

    @FindBy(xpath = "//span[@class='cfui -field -spinner -numAdults']//a[@class='spin-up']")
    private WebElement adultsPlus;

    //    @FindBy(xpath = "//li[@data-name='0']")
//    private WebElement nonStop;
    @FindBy(name = "0")
    private WebElement nonStop;

//    @FindBy(xpath = "//li[@data-name='1']")
//    private WebElement oneStop;

    @FindBy(name = "1")
    private WebElement oneStop;

//    @FindBy(xpath = "//li[@data-name='2']")
//    private WebElement twoStops;

    @FindBy(name = "2")
    private WebElement twoStops;

    @FindBy(xpath = "//div[@data-name=\"sort-section\"]")
    private WebElement sortSection;

    @FindBy(xpath = "//div[@data-name=\"sort-section\"]//div//div/span")
    private WebElement sortSectionValue;

    @FindBy(xpath = "//li[@data-title=\"Cheapest\"]")
    private WebElement cheapestFlights;

    @FindBy(xpath = "//div[@id='searchResultsList']/div/div[1]")
    private WebElement cheapestFlight;

    private final String grandParent = "../..//div[@class='monthDisplay']";
    private final By departureDates = By.xpath("//div/table[1]//td");
    private final By returnDates = By.xpath("//div/table[1]//td");

    private final By dates = By.xpath("(//div[@class='weeks'])[3]//div[@class='day']");

    @FindBy(xpath = "//div[contains(text(), 'October 2018')]")
    private WebElement elem;
    String pattern = "Result number 1: \\$(([0-5][0-4][0-9])|([0-4][0-9][0-9])).*";
    private static String URL = "https://cheapflights.com/";

    @BeforeClass
    public void launchBrowser() {
        System.setProperty("webdriver.gecko.driver", "./src/main/resources/geckodriver");
        driver = new FirefoxDriver();
        PageFactory.initElements(driver, this);

    }

    @BeforeClass(dependsOnMethods = "launchBrowser", description = "Add implicit wait and maximize window")
    public void addImplicitWait() {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Test
    public void setValues() throws InterruptedException {
        driver.get(URL);
        if (!(origin.getAttribute("value").equals(("")))) {
            Thread.sleep(4000);
            origin.click();
            origin.clear();
            origin.sendKeys("Moscow");
            Thread.sleep(2000);
            origin.sendKeys(Keys.ENTER);
            destination.click();
            destination.sendKeys("Tokyo");
            Thread.sleep(2000);
            destination.sendKeys(Keys.ENTER);

            //departureField.click();
            driver.findElement(By.xpath("//div[@aria-label=\"Departure date input\"]")).click();

            new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[@class=\"contentContainer\"]"))));

            while (!(monthName2.getText().equals("October 2018"))) {

                driver.findElement(By.cssSelector("div[aria-label='Next month']")).click();
                if (isDisplayed(elem)) {
                    break;
                }

            }
            List<WebElement> DRdates = driver.findElements(dates);
            for (WebElement date : DRdates) {
                if (date.getText().equals("7")) {
                    Actions chooser = new Actions(driver);
                    chooser.click(date)
                            .moveToElement(driver.findElement(By.xpath("(//div[@class=\"weeks\"])[3]//div[contains(text(), '20')]")))
                            .click()
                            .build().perform();


                    break;
                }
            }

            driver.findElement(By.xpath("//a[@aria-label=\"Select number of travelers and cabin class\"]")).click();
            driver.findElement(By.xpath("(//div[contains(@id, 'adults')]//button[@title=\"Increment\"])[2]")).click();
            driver.findElement(By.xpath("//div[@class=\"cabinTravelerWrapper\"]//div[@class='close']")).click();
            driver.findElement(By.xpath("(//button[@type=\"submit\"]//span[contains(text(), 'Find deals')])[1]")).click();
        } else {


            origin.click();
            origin.sendKeys("Moscow");
            Thread.sleep(2000);
            origin.sendKeys(Keys.ARROW_DOWN);
            origin.sendKeys(Keys.ENTER);
            destination.click();

            destination.sendKeys("Tokyo");
            Thread.sleep(2000);
            destination.sendKeys(Keys.ARROW_DOWN);
            destination.sendKeys(Keys.ENTER);

            departureField.click();
            while (!(monthName.getText().toUpperCase().equalsIgnoreCase("October, 2018"))) {
                nextButton.click();

            }

            List<WebElement> departure = driver.findElements(departureDates);

            for (WebElement cell : departure) {
                if (cell.getText().equals("7")) {
                    cell.click();
                    break;
                }
            }

            arrivalField.click();

            List<WebElement> arrival = driver.findElements(returnDates);
            for (WebElement cell : arrival) {
                if (cell.getText().equals("20")) {
                    cell.click();
                    break;
                }
            }
            adultsPlus.click();
            driver.findElement(By.xpath("//button[@class='-sunspear']")).click();
        }

////        driver.get("https://global.cheapflights.com/flight-search/MOW-TYO/2018-10-07/2018-10-20/2adults/?sort=price_a");
        for (String winHandle : driver.getWindowHandles()) {
            driver.switchTo().window(winHandle);
        }
        Thread.sleep(4000);
        if (driver.getCurrentUrl().contains("https://global.cheapflights.com/flight-search/MOW-TYO/2018-10-07/2018-10-20/2adults")) {
            try {

                waitForVisibilityFluently(twoStops);
                oneStop.click();
                Thread.sleep(3000);
                twoStops.click();
            } catch (org.openqa.selenium.TimeoutException e) {

            } catch (org.openqa.selenium.NoSuchElementException e) {

            } finally {
                oneStop.click();
                Thread.sleep(3000);
                twoStops.click();
            }
//        //Assert.assertTrue(nonStop.isSelected());
//
//
            WebElement slider = driver.findElement(By.xpath("//div[@aria-label='Maximum flight duration']"));
            WebElement progress = driver.findElement(By.xpath("//div[contains(@id, 'legdur-content')]//div[contains(@class,'activeRange')]"));
//        int width = progress.getSize().getWidth();
//        Actions move = new Actions(driver);
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", slider);
            Dimension size = progress.getSize();
            int sliderWidth = size.getWidth();

            int xCoord = slider.getLocation().getX();

            Actions builder = new Actions(driver);
            builder.click()
                    .dragAndDropBy
                            (slider, -((sliderWidth / 4) * 3), 0)
                    .build()
                    .perform();
            Thread.sleep(5000);

            if (sortSectionValue.getText().equals("Cheapest")) {
                Assert.assertTrue(cheapestFlight.getAttribute("aria-label").matches(pattern));
            } else {
                sortSection.click();
                Thread.sleep(5000);
                cheapestFlights.click();
                Thread.sleep(5000);
                Assert.assertTrue(cheapestFlight.getAttribute("aria-label").matches(pattern));
            }
        } else {
            try {
                new FluentWait(driver).withTimeout(300, TimeUnit.SECONDS).pollingEvery(30, TimeUnit.SECONDS)
                        .ignoring(NoSuchElementException.class)
                        .until(presenceOfElementLocated(By.xpath("//label[contains(text(), 'Cheapest')]")));
            }catch(org.openqa.selenium.NoSuchElementException e){
                new FluentWait(driver).withTimeout(300, TimeUnit.SECONDS).pollingEvery(30, TimeUnit.SECONDS)
                        .ignoring(NoSuchElementException.class)
                        .until(presenceOfElementLocated(By.xpath("//label[contains(text(), 'Cheapest')]")));
                }

            driver.findElement(By.cssSelector("a[data-index=\"3\"]")).click();

            new FluentWait(driver).withTimeout(10, TimeUnit.SECONDS).pollingEvery(1, TimeUnit.SECONDS)
                    .ignoring(NoSuchElementException.class)
                    .until(ExpectedConditions.elementToBeClickable(driver.findElement(By.cssSelector("label[for=\"filters-stops-one\"]"))));
            driver.findElement(By.cssSelector("label[for=\"filters-stops-one\"]")).click();
            driver.findElement(By.cssSelector("label[for=\"filters-stops-multi\"]")).click();



            driver.findElement(By.cssSelector("a[data-index=\"2\"]")).click();

            WebElement slider = driver.findElement(By.cssSelector("div[id=\"duration-slider-outbound\"] div[class=\"handle\"]"));
            WebElement  progress = driver.findElement(By.cssSelector("div[id=\"duration-slider-outbound\"] div[class=\"slider-bar\"]"));
            Dimension size = progress.getSize();
            int sliderWidth = size.getWidth();
//
//        int xCoord = slider.getLocation().getX();

            Actions builder = new Actions(driver);
            builder
                    .dragAndDropBy
                            (slider, -((sliderWidth / 4) * 3), 0)
                    .build()
                    .perform();
            Thread.sleep(5000);
            driver.findElement(By.cssSelector("span[class=\"handle-top-items\"]>i[class=\"icon-arrow-down\"]")).click();
            new WebDriverWait(driver, 10).until(ExpectedConditions.invisibilityOfElementLocated(By.id("update-indicator")));

            Assert.assertTrue(driver.findElement(By.cssSelector("div[class=\"quicklink cheapest clearfix selected-filter\"] span[class=\"symbol\"]")).getText().equals("$"));
            Assert.assertTrue(driver.findElement(By.cssSelector("div[class=\"quicklink cheapest clearfix selected-filter\"] span[class=\"value\"]")).getText().matches("(([0-5][0-4][0-9])|([0-4][0-9][0-9]))"));
        }
    }


    private void waitForVisibilityFluently(WebElement element) {
        new FluentWait(driver).withTimeout(200, TimeUnit.SECONDS).pollingEvery(30, TimeUnit.SECONDS)
                .ignoring(NoSuchElementException.class)
                .until(ExpectedConditions.visibilityOf(element));
    }


    private boolean isDisplayed(WebElement element) {
        try {
            return element.isDisplayed();

        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }


    }
}
