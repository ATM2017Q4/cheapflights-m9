package com.cheapflights.core.runner;


import com.cheapflights.common.util.Settings;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.testng.TestNG;
import org.testng.xml.XmlSuite;

import java.util.ArrayList;
import java.util.List;

public class Runner {


    public static void main(String[] args) {
        TestNG testNG = new TestNG();
        Settings settings = new Settings();
        CmdLineParser parser = new CmdLineParser(settings);

        XmlSuite suite = new XmlSuite();

        List<String> files = new ArrayList<>();
        files.add("./src/main/resources/testng.xml");

        suite.setSuiteFiles(files);

        try {
            parser.parseArgument(args);
        } catch (CmdLineException e) {
            e.printStackTrace();
        }
        if (settings.isFirstTest()) {
            suite.getTests().get(0);


        } else if (settings.isAllTests()) {
            for (int i = 0; i < suite.getTests().size(); i++) {
                suite.addTest(suite.getTests().get(i));
            }

            List<XmlSuite> suites = new ArrayList<>();
            suites.add(suite);

            testNG.setXmlSuites(suites);
            testNG.run();

        }
    }
}
