package com.trink.Proxy.ImitateJDK;

import java.lang.reflect.Method;

public interface InvocationHandler {

    void invoke(Object object, Method method);
}
