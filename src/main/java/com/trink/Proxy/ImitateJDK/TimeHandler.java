package com.trink.Proxy.ImitateJDK;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TimeHandler extends BaseHandler {

    public TimeHandler(Object target) {
        super(target);
    }

    @Override
    public void invoke(Object object, Method method) {
        long startTime = System.currentTimeMillis();
        try {
            method.invoke(this.target);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        long stopTime = System.currentTimeMillis();
        System.out.println("Run Time: " + (stopTime - startTime) + " ms");
    }
}
