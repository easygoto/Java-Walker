package dp.factory;

import org.junit.Test;

public class BeanFactoryTest {

    @Test
    public void factory() {

        Movable movable = (Movable) Bean.get("VehicleType");
        movable.run();
    }
}
