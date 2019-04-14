package com.trink.Factory.AbstractFactory;

import org.junit.Test;

public class AbstractFactoryTest {

    @Test
    public void abstractFactory() {
        DefaultFactory defaultFactory = new DefaultFactory();
        defaultFactory.createCar().run();
        defaultFactory.createAK47().shoot();
        defaultFactory.createApple().printName();

        MagicFactory magicFactory = new MagicFactory();
        magicFactory.createBroom().run();
        magicFactory.createMagicStick().shoot();
        magicFactory.createMushRoom().printName();
    }
}
