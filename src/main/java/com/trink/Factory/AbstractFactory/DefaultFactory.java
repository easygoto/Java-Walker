package com.trink.Factory.AbstractFactory;

import com.trink.Factory.AbstractFactory.model.weapon.AK47;
import com.trink.Factory.AbstractFactory.model.food.Apple;
import com.trink.Factory.AbstractFactory.model.vehicle.Car;

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
