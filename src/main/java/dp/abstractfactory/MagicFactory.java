package dp.abstractfactory;

import dp.abstractfactory.model.vehicle.Broom;
import dp.abstractfactory.model.weapon.MagicStick;
import dp.abstractfactory.model.food.MushRoom;

/**
 * @author trink
 */
public class MagicFactory {

    public Broom createBroom() {
        return new Broom();
    }

    public MagicStick createMagicStick() {
        return new MagicStick();
    }

    public MushRoom createMushRoom() {
        return new MushRoom();
    }
}
