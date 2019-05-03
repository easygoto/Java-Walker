package com.trink.Proxy;

import com.trink.Proxy.Car;
import com.trink.Proxy.MovableLogProxy;
import com.trink.Proxy.MovableTimeProxy;
import com.trink.Proxy.Tank;
import org.junit.Test;

public class ProxyTest {

    @Test
    public void tank() {
        Tank tank = new Tank();
        new MovableLogProxy(
                new MovableTimeProxy(
                        tank
                )
        ).move();
        new MovableLogProxy(
                new MovableTimeProxy(
                        tank
                )
        ).stop();
    }

    @Test
    public void car() {
        Car car = new Car();
        new MovableLogProxy(
                new MovableTimeProxy(
                        car
                )
        ).move();
        new MovableLogProxy(
                new MovableTimeProxy(
                        car
                )
        ).stop();
    }
}
