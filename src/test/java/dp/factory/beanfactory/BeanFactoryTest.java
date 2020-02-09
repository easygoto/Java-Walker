package dp.factory.beanfactory;

import dp.factory.Bean;
import dp.factory.Movable;
import org.junit.Test;

public class BeanFactoryTest {

    @Test
    public void factory() {

        Movable movable = (Movable) Bean.get("VehicleType");
        movable.run();
    }
}
