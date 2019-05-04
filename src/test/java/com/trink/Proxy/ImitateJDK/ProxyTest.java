package com.trink.Proxy.ImitateJDK;

import org.junit.Test;

public class ProxyTest {

    @Test
    public void test() throws Exception {
        Proxy.newProxyInstance(Movable.class);
//        Proxy.newProxyInstance(Comparable.class);
//        Proxy.newProxyInstance(com.trink.Filter.Filter.Filter.class);
    }
}
