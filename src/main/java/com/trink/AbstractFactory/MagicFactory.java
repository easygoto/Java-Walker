package com.trink.AbstractFactory;

import com.trink.AbstractFactory.model.vehicle.Broom;
import com.trink.AbstractFactory.model.weapon.MagicStick;
import com.trink.AbstractFactory.model.food.MushRoom;

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
