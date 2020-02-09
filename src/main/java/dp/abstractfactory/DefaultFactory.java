package dp.abstractfactory;

import dp.abstractfactory.model.food.Apple;
import dp.abstractfactory.model.vehicle.Car;
import dp.abstractfactory.model.weapon.Ak47;

/**
 * @author trink
 */
public class DefaultFactory {

    public Car createCar() {
        return new Car();
    }

    public Ak47 createAk47() {
        return new Ak47();
    }

    public Apple createApple() {
        return new Apple();
    }
}
