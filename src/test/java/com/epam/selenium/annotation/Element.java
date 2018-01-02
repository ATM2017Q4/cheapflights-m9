package com.epam.selenium.annotation;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;

import java.util.List;

import static com.epam.selenium.annotation.StringReplacement.replaceWithValues;

public class Element extends By{
    private By locator;
    private Attributes elementSearchCriteria;
    private String elementValue;

    private static final String REPLACE_TOKEN = "?";

    public Element(final Attributes elementSearchCriteria, final String elementValue) {

        if (!elementValue.contains(REPLACE_TOKEN)) {
            initElement(elementSearchCriteria, elementValue);
        }

        this.elementSearchCriteria = elementSearchCriteria;
        this.elementValue = elementValue;
    }

    public final void initElement(final Attributes elementSearchCriteria, final String elementValue) {

        switch (elementSearchCriteria) {
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
        initElement(elementSearchCriteria, replaceWithValues(elementValue, REPLACE_TOKEN, values));
        return this;
    }

    public By getLocator() {
        return locator;
    }

    @Override
    public List<WebElement> findElements(final SearchContext searchContext) {
        return null;
    }
}
