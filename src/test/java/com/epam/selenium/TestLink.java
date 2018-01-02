package com.epam.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

public class TestLink {
    private WebDriver driver;
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
    public void openLink() throws InterruptedException {
        driver.get("https://global.cheapflights.com/search/flights-to-tokyo/from-moscow/departing-2018-10-07/returning-2018-10-20?adults=2&children=0&type=search");

        new FluentWait(driver).withTimeout(200, TimeUnit.SECONDS).pollingEvery(30, TimeUnit.SECONDS)
                .ignoring(NoSuchElementException.class)
                .until(visibilityOf(driver.findElement(By.xpath("//label[contains(text(), 'Cheapest')]"))));
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
