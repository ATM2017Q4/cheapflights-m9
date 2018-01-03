package com.epam.selenium.annotation;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.ElementLocator;

import java.util.List;

import static com.epam.selenium.annotation.StringReplacement.replaceWithValues;

public class Element implements ElementLocator{
    private By locator;
    private Attributes elementAttribute;
    private String elementValue;
    private WebDriver driver;

    private static final String TOKENTOREPLACE = "?";

    public Element(final Attributes elementAttribute, final String elementValue) {

        if (!elementValue.contains(TOKENTOREPLACE)) {
            assignValues(elementAttribute, elementValue);
        }

        this.elementAttribute = elementAttribute;
        this.elementValue = elementValue;
    }

    public final void assignValues(final Attributes elementAttribute, final String elementValue) {

        switch (elementAttribute) {
            case ID:
                locator = By.id(elementValue);
                break;
            case XPATH:
                locator = By.xpath(elementValue);
                break;
            case LINK_TEXT:
                locator = By.linkText(elementValue);
                break;
            case CLASS_NAME:
                locator = By.className(elementValue);
                break;
            case CSS_SELECTOR:
                locator = By.cssSelector(elementValue);
                break;
            case TAG_NAME:
                locator = By.tagName(elementValue);
                break;
            case NAME:
                locator = By.name(elementValue);
                break;
            case PARTIAL_LINK_TEXT:
                locator = By.partialLinkText(elementValue);
                break;
        }
    }

    public Element updateElement(final String... values) {
        assignValues(elementAttribute, replaceWithValues(elementValue, TOKENTOREPLACE, values));
        return this;
    }

    public By getLocator() {
        return locator;
    }


    public Element updateElement(final Element element, final String... values) {
        return element.updateElement(values);
    }

    public WebElement findElement(final Element element) {
        return driver.findElement(element.getLocator());
    }

    public List<WebElement> findElements(final Element element) {
        return driver.findElements(element.getLocator());
    }
    public void click(final Element element) {
        findElement(element).click();
}

    public WebElement findElement() {
        return null;
    }

    public List<WebElement> findElements() {
        return null;
    }
}
