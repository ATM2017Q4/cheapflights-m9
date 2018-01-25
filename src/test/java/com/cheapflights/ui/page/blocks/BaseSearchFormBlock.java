package com.cheapflights.ui.page.blocks;


import com.cheapflights.ui.utils.WebDriverTools;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.element.HtmlElement;
import ru.yandex.qatools.htmlelements.element.TextInput;

public abstract class BaseSearchFormBlock extends HtmlElement{

    @Name("Origin box")
    @FindBy(name = "origin")
    protected TextInput origin;

    @Name("Destination box")
    @FindBy(name = "destination")
    protected TextInput destination;

    @Name("Submit button")
    @FindBy(xpath = "//*[contains(text(), 'Find deals')]")
    protected WebElement submitButton;

    private static By logoXpath = By.xpath("//div[contains(@class, 'logo')]//a[@href='/']");

    public abstract void searchOrigin(String from);
    public abstract void searchDestination(String to);
    public abstract void searchDates(String month, String startDate, String endDate);
    public abstract void increaseNumberOfAdults(int number);
    public void submitForm(WebDriver driver){
        submitButton.click();
        String parentWindow = driver.getWindowHandle();
        for (String childWindow : driver.getWindowHandles()) {
            if (childWindow != parentWindow) {
                driver.switchTo().window(childWindow);
            }
        }
        WebDriverTools.waitForVisibilityFluently(driver, driver.findElement(logoXpath), 40, 5);
    }



}
