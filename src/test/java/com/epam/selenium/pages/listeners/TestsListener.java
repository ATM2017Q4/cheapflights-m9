package com.epam.selenium.pages.listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestsListener implements ITestListener{

    public void onTestStart(ITestResult iTestResult) {

    }

    public void onTestSuccess(ITestResult iTestResult) {
        System.out.println(getTestMethodName(iTestResult) + " passed.");
    }

    public void onTestFailure(ITestResult iTestResult) {
        System.out.println("The execution of the test " + getTestMethodName(iTestResult) + " failed.");
    }

    public void onTestSkipped(ITestResult iTestResult) {

    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

    public void onStart(ITestContext iTestContext) {

    }

    public void onFinish(ITestContext iTestContext) {

    }
    private static String getTestMethodName(ITestResult result) {

        return result.getMethod().getConstructorOrMethod().getName();

    }

}
