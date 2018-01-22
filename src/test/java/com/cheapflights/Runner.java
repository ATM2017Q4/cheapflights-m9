package com.cheapflights;

import com.cheapflights.tests.CheapFlightsTest;
import com.cheapflights.utils.FileSearchUtil;
import com.cheapflights.utils.JsonUtil;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.testng.TestNG;

public class Runner {
    protected static TestNG testNG = new TestNG();
    public static void main(String[] args) {
        Settings settings = new Settings();
        CmdLineParser parser = new CmdLineParser(settings);

        try {
            parser.parseArgument(args);

        } catch (CmdLineException e) {
            e.printStackTrace();
        }
        if (settings.isFirstTest()){
            FileSearchUtil.testFirst("./src/main/resources", ".json");
        }
        if (settings.isRandomTest()){
            FileSearchUtil.testAll("./src/main/resources", ".json");
        }

        if(settings.isAllTests()){

        }

        Class[] classes = new Class[]{
                CheapFlightsTest.class
        };
        testNG.setTestClasses(classes);
        testNG.run();

    }

}
