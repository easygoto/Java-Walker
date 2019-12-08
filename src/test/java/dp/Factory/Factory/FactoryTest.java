package dp.Factory.Factory;

import org.junit.Test;

public class FactoryTest {

    @Test
    public void factory() {

        VehicleFactory factory = new CarFactory();
        Movable        movable = factory.create();
        movable.run();
    }
}
