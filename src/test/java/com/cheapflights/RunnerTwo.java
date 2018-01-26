package com.cheapflights;

import com.cheapflights.common.listeners.TestsListener;
import com.cheapflights.common.util.Settings;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.testng.ITest;
import org.testng.ITestNGListener;
import org.testng.TestNG;

import java.util.ArrayList;
import java.util.List;

public class RunnerTwo {
    public static void main(String[] args) {
        TestNG testNG = new TestNG();
        Settings settings = new Settings();
        CmdLineParser parser = new CmdLineParser(settings);
        try {
            parser.parseArgument(args);
        } catch (CmdLineException e) {
            e.printStackTrace();
        }
        for(int i = 0; i < args.length; i++) {
            System.out.println(args[i]);
        }
        List<Class<? extends ITestNGListener>> listener = new ArrayList<>();
        listener.add(TestsListener.class);
        testNG.setListenerClasses(listener);
            if(settings.isFirstTest()){
                testNG.setTestClasses(new Class[]{FirstTestFactory.class});
            } else if (settings.isAllTests()){
                testNG.setTestClasses(new Class[]{AllTestsFactory.class});
            }
            testNG.run();

    }
    ITest test;
    test.set
}
