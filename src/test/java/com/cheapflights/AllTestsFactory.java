package com.cheapflights;

import com.cheapflights.ui.tests.CheapFlightsTest;
import com.cheapflights.ui.utils.FileSearchUtil;
import org.testng.annotations.Factory;

public class AllTestsFactory {
    @Factory
    public Object[] createInstance() {
        Object[] result = new Object[FileSearchUtil.getDirectoryFiles("./src/main/resources", ".json").size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = new CheapFlightsTest();
        }
        return result;
    }


}
