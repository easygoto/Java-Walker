package com.trink.AbstractFactory;

import com.trink.AbstractFactory.model.food.Food;
import com.trink.AbstractFactory.model.vehicle.Vehicle;
import com.trink.AbstractFactory.model.weapon.Weapon;

public abstract class AbstractFactory {

    public abstract Vehicle createVehicle();

    public abstract Weapon createWeapon();

    public abstract Food createFood();
}
