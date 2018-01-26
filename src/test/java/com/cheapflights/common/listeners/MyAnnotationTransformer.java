package com.cheapflights.common.listeners;

import org.testng.IAnnotationTransformer;
import org.testng.annotations.ITestAnnotation;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class MyAnnotationTransformer implements IAnnotationTransformer {
    private String name;

    public MyAnnotationTransformer(String name) {
        this.name = name;
    }

    @Override
    public void transform(ITestAnnotation iTestAnnotation, Class aClass, Constructor constructor, Method method) {
        if ("chooseTheCheapestFlight".equals(method.getName())) {
            iTestAnnotation.setDataProvider(name);

        }
    }


}
