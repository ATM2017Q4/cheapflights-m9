package com.cheapflights.core.runner;

import com.cheapflights.TestsFactory;
import com.cheapflights.common.listeners.MyAnnotationTransformer;
import com.cheapflights.common.listeners.TestsListener;
import com.cheapflights.common.util.Settings;
import com.cheapflights.ui.tests.CheapFlightsTest;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.testng.ITestNGListener;
import org.testng.TestNG;

import java.util.ArrayList;
import java.util.List;


public class Runner {

    public static void main(String[] args) {

        Settings settings = new Settings();
        CmdLineParser parser = new CmdLineParser(settings);
        try {
            parser.parseArgument(args);
        } catch (CmdLineException e) {
            e.printStackTrace();
        }

        TestNG testNG = new TestNG();
        List<Class<? extends ITestNGListener>> listener = new ArrayList<>();
        listener.add(TestsListener.class);
        testNG.setListenerClasses(listener);
        if (settings.isFirstTest()) {
            testNG.setTestClasses(new Class[]{CheapFlightsTest.class});
            testNG.setAnnotationTransformer(new MyAnnotationTransformer("dp1"));
            testNG.run();
        } else if (settings.isAllTests()) {
            //testNG.setAnnotationTransformer(new MyAnnotationTransformer("dp2"));
            testNG.setTestClasses(new Class[]{TestsFactory.class});
            testNG.run();
        } else if (settings.isRandomTest()) {
            testNG.setTestClasses(new Class[]{CheapFlightsTest.class});
            testNG.setAnnotationTransformer(new MyAnnotationTransformer("dp3"));
            testNG.run();

        }
    }
}




