package dp.AbstractFactory;

import dp.AbstractFactory.model.food.Food;
import dp.AbstractFactory.model.vehicle.Vehicle;
import dp.AbstractFactory.model.weapon.Weapon;

public abstract class AbstractFactory {

    public abstract Vehicle createVehicle();

    public abstract Weapon createWeapon();

    public abstract Food createFood();
}
