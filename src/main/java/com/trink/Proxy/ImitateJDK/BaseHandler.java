package com.trink.Proxy.ImitateJDK;

public abstract class BaseHandler implements InvocationHandler {

    protected Object target;

    public BaseHandler(Object target) {
        this.target = target;
    }

    public Object getTarget() {
        return target;
    }

    public BaseHandler setTarget(Object target) {
        this.target = target;
        return this;
    }
}
