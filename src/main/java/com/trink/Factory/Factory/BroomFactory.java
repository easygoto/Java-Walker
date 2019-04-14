package com.trink.Factory.Factory;

public class BroomFactory implements VehicleFactory {
    @Override
    public Movable create() {
        return new Broom();
    }
}
