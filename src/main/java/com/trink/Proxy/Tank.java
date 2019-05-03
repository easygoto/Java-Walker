package com.trink.Proxy;

import java.util.Random;

public class Tank implements Movable {

    @Override
    public void move() {
        try {
            Thread.sleep(new Random().nextInt(3000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Tank moving ...");
    }

    @Override
    public void stop() {
        try {
            Thread.sleep(new Random().nextInt(3000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Tank stopping ...");
    }
}
