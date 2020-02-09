package dp.abstractfactory;

import dp.abstractfactory.model.food.BaseFood;
import dp.abstractfactory.model.vehicle.BaseVehicle;
import dp.abstractfactory.model.weapon.BaseWeapon;

/**
 * @author trink
 */
public abstract class AbstractFactory {

    /**
     * 创建运输工具
     *
     * @return BaseVehicle
     */
    public abstract BaseVehicle createVehicle();

    /**
     * 创建武器
     *
     * @return BaseWeapon
     */
    public abstract BaseWeapon createWeapon();

    /**
     * 创建食物
     *
     * @return BaseFood
     */
    public abstract BaseFood createFood();
}
