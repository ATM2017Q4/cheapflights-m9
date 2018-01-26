package com.cheapflights;

import com.cheapflights.ui.tests.CheapFlightsTest;
import org.testng.annotations.Factory;

public class FirstTestFactory {
    @Factory
    public Object[] createInstance() {
        Object[] result = new Object[1];
        result[0] = new CheapFlightsTest();
        return result;
    }
}
