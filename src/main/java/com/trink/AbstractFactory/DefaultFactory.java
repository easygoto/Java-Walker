package com.trink.AbstractFactory;

import com.trink.AbstractFactory.model.weapon.AK47;
import com.trink.AbstractFactory.model.food.Apple;
import com.trink.AbstractFactory.model.vehicle.Car;

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
