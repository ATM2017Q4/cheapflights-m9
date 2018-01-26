package com.cheapflights.ui.page.blocks;

import com.cheapflights.ui.page.abstractpages.AbstractHomePage;
import com.cheapflights.ui.utils.WebDriverTools;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.annotations.Name;

import java.util.List;

public class EmptySearchFormBlock extends BaseSearchFormBlock {

    @Name("Search options drop down")
    @FindBy(css = "div[class='lookup']")
    private WebElement options;


    @FindBy(css = "svg[class='icon -departureDate']")
    private WebElement departureField;

    @FindBy(xpath = "//a[@class='next-month']")
    private WebElement nextButton;

    @FindBy(xpath = "//th[@class='-monthname']/span")
    private WebElement monthName;

    @FindBy(css = "svg[class='icon -returnDate']")
    private WebElement arrivalField;

    @FindBy(xpath = "//span[@class='cfui -field -spinner -numAdults']//a[@class='spin-up']")
    private WebElement adultsPlus;

    private final By departureDates = By.xpath("//div/table[1]//td");
    private final By returnDates = By.xpath("//div/table[1]//td");

    @Override
    public void searchOrigin(String from) {
        origin.click();
        origin.sendKeys(from);
        WebDriverTools.waitForVisibilityFluently(AbstractHomePage.getDriver(), options, 2, 1);
        origin.sendKeys(Keys.ARROW_DOWN);
        origin.sendKeys(Keys.ENTER);
    }

    @Override
    public void searchDestination(String to) {
        destination.click();

        destination.sendKeys(to);
        WebDriverTools.waitForVisibilityFluently(AbstractHomePage.getDriver(), options, 2, 1);
        destination.sendKeys(Keys.ARROW_DOWN);
        destination.sendKeys(Keys.ENTER);

    }

    @Override
    public void searchDates(String month, String startDate, String endDate) {
        departureField.click();
        while (!(monthName.getText().contains(month.toUpperCase()))) {
            nextButton.click();

        }
        List<WebElement> departure = AbstractHomePage.getDriver().findElements(departureDates);

        for (WebElement cell : departure) {
            if (cell.getText().equals(startDate)) {
                cell.click();
                break;
            }
        }

        arrivalField.click();

        List<WebElement> arrival = AbstractHomePage.getDriver().findElements(returnDates);
        for (WebElement cell : arrival) {
            if (cell.getText().equals(endDate)) {
                cell.click();
                break;
            }
        }

    }

    @Override
    public void increaseNumberOfAdults(int number) {
        for (int i = 1; i < number; i++) {
            adultsPlus.click();
        }

    }
}
