package dp.AbstractFactory;

import dp.AbstractFactory.model.weapon.AK47;
import dp.AbstractFactory.model.food.Apple;
import dp.AbstractFactory.model.vehicle.Car;

public class DefaultFactory {

    public Car createCar() {
        return new Car();
    }

    public AK47 createAK47() {
        return new AK47();
    }

    public Apple createApple() {
        return new Apple();
    }
}
