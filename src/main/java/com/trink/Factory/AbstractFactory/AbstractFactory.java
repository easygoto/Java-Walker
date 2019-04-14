package com.trink.Factory.AbstractFactory;

import com.trink.Factory.AbstractFactory.model.food.Food;
import com.trink.Factory.AbstractFactory.model.vehicle.Vehicle;
import com.trink.Factory.AbstractFactory.model.weapon.Weapon;

public abstract class AbstractFactory {

    public abstract Vehicle createVehicle();

    public abstract Weapon createWeapon();

    public abstract Food createFood();
}
