package com.epam.selenium.pages.factory;

import com.epam.selenium.pages.abstractpages.AbstractHomePage;
import com.epam.selenium.pages.desktop.EmptyHomePage;
import com.epam.selenium.pages.desktop.PrefilledHomePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePageFactory {

    private WebDriver driver;

    private static String originFieldAttribute = "value";
    private static String originFieldValue = "";

    @FindBy(name = "origin")
    protected static WebElement origin;

   public HomePageFactory(WebDriver driver){
       this.driver = driver;
       PageFactory.initElements(driver, this);
   }
    public static AbstractHomePage getCorrectPage(WebDriver driver){
        AbstractHomePage page;
        if (origin.getAttribute(originFieldAttribute).equals(originFieldValue)) {
            page = new EmptyHomePage(driver);
        }else {
            page = new PrefilledHomePage(driver);
        }
        return page;

    }

}
