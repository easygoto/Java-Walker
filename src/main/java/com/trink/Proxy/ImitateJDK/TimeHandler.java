package com.trink.Proxy.ImitateJDK;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TimeHandler implements InvocationHandler {

    @Override
    public void invoke(Object object, Method method) {
        long startTime = System.currentTimeMillis();
        try {
            method.invoke(object, new Object[]{});
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        long stopTime = System.currentTimeMillis();
        System.out.println("Run Time: " + (stopTime - startTime) + " ms");
    }
}
