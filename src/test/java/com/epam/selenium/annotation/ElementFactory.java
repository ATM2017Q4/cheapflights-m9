package com.epam.selenium.annotation;

import org.openqa.selenium.WebDriver;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ElementFactory {
    private WebDriver driver;


    private static final Logger log = Logger.getLogger(ElementFactory.class.getName());

    public ElementFactory(final WebDriver driver) {

        this.driver = driver;
        initElements();
    }

    public  void initElements() {

        final List<Field> fields = new ArrayList<Field>();
        Class currentPageObject = this.getClass();

        while (currentPageObject != ElementFactory.class) {
            fields.addAll(new ArrayList<Field>(Arrays.asList(currentPageObject.getDeclaredFields())));
            currentPageObject = currentPageObject.getSuperclass();
        }

        for (Field field : fields) {
            final LocateBy fieldAnnotation = field.getAnnotation(LocateBy.class);
            final boolean accessible = field.isAccessible();

            if (fieldAnnotation != null) {
                try {
                    field.setAccessible(true);
                    field.set(this, new Element(fieldAnnotation.attribute(), fieldAnnotation.value()));
                    field.setAccessible(accessible);
                } catch (IllegalAccessException e) {
                    log.log(Level.SEVERE, e.toString(), e);
                }
            }
        }
    }

//    public Element updateElement(final Element element, final String... values) {
//        return element.updateElement(values);
//    }
//
//    public WebElement findElement(final Element element) {
//        return driver.findElement(element.getLocator());
//    }
//
//    public List<WebElement> findElements(final Element element) {
//        return driver.findElements(element.getLocator());
//    }
//    public void click(final Element element) {
//        findElement(element).click();
//}


}
