package com.cheapflights;


import com.cheapflights.utils.RandomUtil;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;

import org.testng.TestNG;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

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
            suite.addIncludedGroup("first");
        }
        else if (settings.isRandomTest()){
            if (suite.getIncludedGroups().equals("all")){
               suite.addTest(suite.getTests().get(RandomUtil.generateRandom(0, suite.getTests().size())));
            }
        }
        else if(settings.isAllTests()){
            suite.addIncludedGroup("all");
        }

        List<XmlSuite> suites = new ArrayList<>();
        suites.add(suite);

        testNG.setXmlSuites(suites);
        testNG.run();

    }

}
