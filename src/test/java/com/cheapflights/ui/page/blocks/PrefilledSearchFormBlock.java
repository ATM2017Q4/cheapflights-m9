package com.cheapflights.ui.page.blocks;

import com.cheapflights.ui.page.abstractpages.AbstractHomePage;
import com.cheapflights.ui.utils.WebDriverTools;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.annotations.Name;

import java.util.List;
@Name("Search Form")
@FindBy(xpath = "//div[@class=\"searchFormWrapper \"]")

public class PrefilledSearchFormBlock extends BaseSearchFormBlock{

    @Name("Origin typeahead dropdown")
    @FindBy(xpath = "//div[contains(@id, 'origin-smartbox-dropdown')]")
    private WebElement originOptions;

    @Name("Destination typeahead dropdown")
    @FindBy(xpath = "//div[contains(@id, 'destination-smartbox-dropdown')]")
    private WebElement destinationOptions;

    @Name("Departure date field")
    @FindBy(xpath = "//div[@aria-label='Departure date input']")
    private WebElement departureDateField;

    @Name("Date picker")
    @FindBy(className = "contentContainer")
    private WebElement datePicker;

    @Name("Name of the month")
    @FindBy(xpath = "(//div[@class=\"col col-month col-month-m\"])[2]//div[@class='monthDisplay']")
    private WebElement monthName;

    @Name("Arrow to switch months")
    @FindBy(css = "div[aria-label='Next month']")
    private WebElement nextArrow;

    @Name("Close button for travelers block")
    @FindBy(xpath = "//div[@class=\"cabinTravelerWrapper\"]//div[@class='close']")
    private WebElement closeButton;

    @Name("Number of adult travellers")
    @FindBy(xpath = "//a[@aria-label='Select number of travelers and cabin class']")
    private WebElement travellersNumber;

    @Name("Increase in number of adults")
    @FindBy(xpath = "(//div[contains(@id, 'adults')]//button[@title=\"Increment\"])[2]")
    private WebElement adultsPlus;

    @Name("Column with month name")
    @FindBy(xpath = "(//div[@class=\"col col-month col-month-m\"])[2]")
    private WebElement monthColumn;

    @Name("")
    @FindBy(xpath = "(//div[@class='weeks'])[3]//div[@class='day']" )
    private List<WebElement> dates;
    //private By dates = By.xpath("(//div[@class='weeks'])[3]//div[@class='day']");

    @Override
    public void searchOrigin(String from) {
        origin.click();
        origin.clear();
        origin.sendKeys(from);
        WebDriverTools.waitForVisibilityFluently(AbstractHomePage.getDriver(), originOptions, 2, 1);
        origin.sendKeys(Keys.ENTER);
    }

    @Override
    public void searchDestination(String to) {
        destination.click();
        destination.sendKeys(to);
        WebDriverTools.waitForVisibilityFluently(AbstractHomePage.getDriver(), destinationOptions, 2, 1);
        destination.sendKeys(Keys.ENTER);
    }

    @Override
    public void searchDates(String month, String startDate, String endDate) {
        departureDateField.click();
        WebDriverTools.waitForVisibilityFluently(AbstractHomePage.getDriver(), datePicker, 10, 1);

        while (!(isVisible(monthColumn, monthName, month))) {
            nextArrow.click();
        }

        By endDateLocator = By.xpath("(//div[@class='weeks'])[3]//div[contains(text(), '" + endDate + "')]");
        List<WebElement> duration = dates;
        for (WebElement day : duration) {
            if (day.getText().equals(startDate)) {
                Actions chooser = new Actions(AbstractHomePage.getDriver());
                chooser.click(day)
                        .sendKeys(Keys.TAB)
                        .moveToElement(AbstractHomePage.getDriver().findElement(endDateLocator))
                        .click(AbstractHomePage.getDriver().findElement(endDateLocator))
                        .build().perform();
                break;
            }
        }
    }

    @Override
    public void increaseNumberOfAdults(int number) {

        travellersNumber.click();
        for (int i = 1; i < number; i++) {

            adultsPlus.click();
        }
        closeButton.click();


    }

    public boolean isVisible(WebElement monthColumn, WebElement monthName, String text) {
        boolean result;
        try {
            result = (monthColumn.getAttribute("aria-hidden").equals("false")) && monthName.getText().contains(text);

        } catch (org.openqa.selenium.NoSuchElementException e) {
            result = false;
        }
        return result;


    }
}
