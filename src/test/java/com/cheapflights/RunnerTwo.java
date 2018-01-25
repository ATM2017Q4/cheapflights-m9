package com.cheapflights;

import com.cheapflights.common.listeners.TestsListener;
import com.cheapflights.common.util.Settings;
import com.cheapflights.ui.tests.CheapFlightsTest;
import com.cheapflights.ui.utils.FileSearchUtil;
import com.cheapflights.ui.utils.JsonUtil;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.testng.ITestNGListener;
import org.testng.TestNG;

import java.util.ArrayList;
import java.util.List;

public class RunnerTwo {
    public static void main(String[] args) {
        List<String> files = FileSearchUtil.getDirectoryFiles("./src/main/resources", ".json");
        for (int i = 0; i<files.size(); i++) {
            TestNG testNG = new TestNG();
            Settings settings = new Settings();
            CmdLineParser parser = new CmdLineParser(settings);
            try {
                parser.parseArgument(args);
            } catch (CmdLineException e) {
                e.printStackTrace();
            }
            List<Class<? extends ITestNGListener>> listener = new ArrayList<>();
            listener.add(TestsListener.class);
            testNG.setListenerClasses(listener);
            testNG.setTestClasses(new Class[]{CheapFlightsTest.class});
            if(settings.isFirstTest()){
                JsonUtil.fileName = files.get(0);
                System.out.println(JsonUtil.fileName);
                testNG.run();
                break;
            } else if (settings.isAllTests()){
                JsonUtil.fileName = files.get(i);
                testNG.run();

            }




        }

    }
}
