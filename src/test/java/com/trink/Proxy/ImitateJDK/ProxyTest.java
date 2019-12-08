package com.trink.Proxy.ImitateJDK;

import org.junit.Test;

public class ProxyTest {

    @Test
    public void test() {
//        Proxy.newProxyInstance(Comparable.class);
//        Proxy.newProxyInstance(com.trink.Filter.Filter.Filter.class, new TimeHandler());
    }

    @Test
    public void time() {
        try {
            Movable movable = (Movable) Proxy.newProxyInstance(Movable.class, new TimeHandler(new Tank()));
            movable.move();
            movable.stop();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void log() {
        try {
            Movable movable = (Movable) Proxy.newProxyInstance(Movable.class, new LogHandler(new Tank()));
            movable.move();
            movable.stop();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
