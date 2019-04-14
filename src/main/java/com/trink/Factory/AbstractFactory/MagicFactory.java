package com.trink.Factory.AbstractFactory;

import com.trink.Factory.AbstractFactory.model.vehicle.Broom;
import com.trink.Factory.AbstractFactory.model.weapon.MagicStick;
import com.trink.Factory.AbstractFactory.model.food.MushRoom;

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
