package com.cheapflights.ui.page.blocks;

import com.cheapflights.ui.page.abstractpages.AbstractSearchPage;
import com.cheapflights.ui.utils.webdrivertools.AjaxContentWaitDecorator;
import com.cheapflights.ui.utils.webdrivertools.Wait;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.element.CheckBox;
import ru.yandex.qatools.htmlelements.element.HtmlElement;


@Name("Filters container")
@FindBy(xpath = "//div[@class='filterListContainer']")
public class FiltersBlock extends HtmlElement {

    @Name("One stop checkbox")
    @FindBy(name = "2")
    private CheckBox twoStops;

    @Name("Two stops checkbox")
    @FindBy(name = "1")
    private CheckBox oneStop;

    @Name("Max flight duration sider")
    @FindBy(xpath = "//div[@aria-label='Maximum flight duration']")
    private WebElement slider;

    @Name("Page load progress bar")
    @FindBy(xpath = "//div[contains(@id, 'legdur-content')]//div[contains(@class,'activeRange')]")
    private WebElement progress;

    @Name("Sort section dropdown")
    @FindBy(xpath = "//div[@data-name='sort-section']")
    private WebElement sortSection;

    @Name("Sort section dropdown values")
    @FindBy(xpath = "//div[@data-name='sort-section']//div//div/span")
    private WebElement sortSectionValue;

    private SortDropDownBlock sortDropDownBlock;

    public void chooseNonStopFlights() {
        new AjaxContentWaitDecorator(new Wait(AbstractSearchPage.getDriver())).setUpWait();
        oneStop.click();
        new AjaxContentWaitDecorator(new Wait(AbstractSearchPage.getDriver())).setUpWait();
        twoStops.click();
        new AjaxContentWaitDecorator(new Wait(AbstractSearchPage.getDriver())).setUpWait();

    }

    public void modifyDuration(int divider, int multiplier) {
        //Here Javascript is used as a workaround as moveToElement doesn't scroll the element into view in ForeFox and MoveTargetOutOfBoundsException is thrown
        //https://github.com/mozilla/geckodriver/issues/776
        ((JavascriptExecutor) AbstractSearchPage.getDriver()).executeScript("arguments[0].scrollIntoView(true);", slider);
        Dimension size = progress.getSize();
        int sliderWidth = size.getWidth();

        Actions builder = new Actions(AbstractSearchPage.getDriver());
        builder.moveToElement(slider).click()
                .dragAndDropBy
                        (slider, -((sliderWidth / divider) * multiplier), 0)
                .build()
                .perform();
        new AjaxContentWaitDecorator(new Wait(AbstractSearchPage.getDriver())).setUpWait();

    }

    public void sortByCheapest() {
        if (!(sortSectionValue.getText().equals("Cheapest"))) {
            sortSection.click();
            sortDropDownBlock.sortByCheapest();
        }

    }


}
