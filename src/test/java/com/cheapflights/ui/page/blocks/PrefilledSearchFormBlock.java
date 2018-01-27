package com.cheapflights.ui.page.blocks;

import com.cheapflights.ui.page.abstractpages.AbstractHomePage;
import com.cheapflights.ui.utils.WebDriverTools;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.element.Button;

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
    @FindBy(xpath = "//div[@class='contentContainer']")
    private WebElement datePicker;

    @Name("Close button for travelers block")
    @FindBy(xpath = "//div[@class=\"cabinTravelerWrapper\"]//div[@class='close']")
    private WebElement closeButton;

    @Name("Number of adult travellers")
    @FindBy(xpath = "//a[@aria-label='Select number of travelers and cabin class']")
    private WebElement travellersNumber;

    @Name("Increase in number of adults")
    @FindBy(xpath = "(//div[contains(@id, 'adults')]//button[@title=\"Increment\"])[2]")
    private Button adultsPlus;

    private DatePickerBlock datePickerBlock;

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
        datePickerBlock.searchDates(month, startDate, endDate);
    }

    @Override
    public void increaseNumberOfAdults(int number) {
        travellersNumber.click();
        for (int i = 1; i < number; i++) {
            adultsPlus.click();
        }
        closeButton.click();
    }

}
