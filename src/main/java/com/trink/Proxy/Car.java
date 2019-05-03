package com.trink.Proxy;

import java.util.Random;

public class Car implements Movable {

    @Override
    public void move() {
        try {
            Thread.sleep(new Random().nextInt(1500));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Car moving ...");
    }

    @Override
    public void stop() {
        try {
            Thread.sleep(new Random().nextInt(800));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Tank stopping ...");
    }
}
