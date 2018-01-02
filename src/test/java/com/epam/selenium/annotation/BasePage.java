package com.epam.selenium.annotation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BasePage {
    private WebDriver driver;


    private static final Logger log = Logger.getLogger(BasePage.class.getName());

    public BasePage(final WebDriver driver) {

        this.driver = driver;
        initElements();
    }

    public final void initElements() {

        final List<Field> fields = new ArrayList<Field>();
        Class currentPageObject = this.getClass();

        while (currentPageObject != BasePage.class) {
            fields.addAll(new ArrayList<Field>(Arrays.asList(currentPageObject.getDeclaredFields())));
            currentPageObject = currentPageObject.getSuperclass();
        }

        for (Field field : fields) {
            final LocateBy fieldAnnotation = field.getAnnotation(LocateBy.class);
            final boolean accessible = field.isAccessible();

            if (fieldAnnotation != null) {
                try {
                    field.setAccessible(true);
                    field.set(this, new Element(fieldAnnotation.attributes(), fieldAnnotation.value()));
                    field.setAccessible(accessible);
                } catch (IllegalAccessException e) {
                    log.log(Level.SEVERE, e.toString(), e);
                }
            }
        }
    }

    public Element updateElement(final Element element, final String... values) {
        return element.updateElement(values);
    }

    public WebElement findElement(final Element element) {
        return driver.findElement(element.getLocator());
    }

    public void click(final Element element) {
        findElement(element).click();
    }

    public String getText(final Element element) {
        return findElement(element).getText();
    }
}
