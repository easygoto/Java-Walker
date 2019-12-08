package dp.Proxy.Simple;

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
