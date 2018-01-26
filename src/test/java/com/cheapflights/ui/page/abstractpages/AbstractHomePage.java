package com.cheapflights.ui.page.abstractpages;

import com.cheapflights.ui.page.factory.SearchPageFactory;
import com.cheapflights.ui.utils.WebDriverTools;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementDecorator;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementLocatorFactory;

public abstract class AbstractHomePage {


    protected static WebDriver driver;

    public AbstractHomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new HtmlElementDecorator(new HtmlElementLocatorFactory(this.driver)), this);
    }

    @FindBy(name = "origin")
    protected WebElement origin;

    @FindBy(name = "destination")
    protected WebElement destination;

    @FindBy(xpath = "//*[contains(text(), 'Find deals')]")
    protected WebElement submitButton;

    private static By logoXpath = By.xpath("//div[contains(@class, 'logo')]//a[@href='/']");

    public abstract AbstractHomePage chooseOrigin(String from);

    public abstract AbstractHomePage chooseDestination(String to);

    public abstract AbstractHomePage chooseDates(String period, String startDate, String endDate);

    public abstract AbstractHomePage increaseNumberOfAdults(int number);

    public AbstractSearchPage submitForm() {
        submitButton.click();
        String parentWindow = driver.getWindowHandle();
        for (String childWindow : driver.getWindowHandles()) {
            if (childWindow != parentWindow) {
                driver.switchTo().window(childWindow);
            }
        }
        WebDriverTools.waitForVisibilityFluently(driver, driver.findElement(logoXpath), 40, 5);
        return SearchPageFactory.getCorrectPage(driver);
    }

    public static WebDriver getDriver() {
        return driver;
    }


}
