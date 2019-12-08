package dp.AbstractFactory;

import dp.AbstractFactory.model.vehicle.Broom;
import dp.AbstractFactory.model.weapon.MagicStick;
import dp.AbstractFactory.model.food.MushRoom;

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
