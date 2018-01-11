package com.epam.selenium.pages.abstractpages;

import com.epam.selenium.pages.factory.SearchPageFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public abstract class AbstractHomePage extends AbstractPage{

    public AbstractHomePage(WebDriver driver){
        super(driver);
    }

    @FindBy(name = "origin")
    protected WebElement origin;

    @FindBy(name = "destination")
    protected WebElement destination;

    @FindBy(xpath = "//*[contains(text(), 'Find deals')]")
    protected WebElement submitButton;

    private static By logoXpath = By.xpath("//div[contains(@class, 'logo')]//a[@href='/']");

    public AbstractHomePage chooseOrigin(String from) {
        return this;
    }

    public AbstractHomePage chooseDestination(String to) {
        return this;
    }

    public AbstractHomePage chooseDates(String period, String startDate, String endDate){
        return this;
    }

    public AbstractHomePage increaseNumberOfAdults(int number) {
        return this;
    }

    public AbstractSearchPage submitForm(){
        submitButton.click();
        String parentWindow = getDriver().getWindowHandle();
        for (String childWindow : getDriver().getWindowHandles()) {
            if (childWindow != parentWindow) {
                getDriver().switchTo().window(childWindow);
            }
        }
        waitForVisibilityFluently(getDriver().findElement(logoXpath), 40, 5);
        return SearchPageFactory.getCorrectPage(getDriver());
    }




}
