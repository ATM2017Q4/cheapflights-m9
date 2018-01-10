package com.epam.selenium.pages.desktop;

import com.epam.selenium.pages.abstractpages.AbstractHomePage;

public interface HomePage {
    AbstractHomePage chooseOrigin(String from);
    AbstractHomePage chooseDestination(String to);
    AbstractHomePage increaseNumberOfAdults(int number);

}
