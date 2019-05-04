package com.trink.Proxy.Simple;

public class MovableLogProxy implements Movable {

    private Movable movable;

    MovableLogProxy(Movable movable) {
        super();
        this.movable = movable;
    }

    private void before() {
        System.out.println("Movable log start ...");
    }

    private void after() {
        System.out.println("Movable log end ...");
    }

    @Override
    public void move() {
        before();
        movable.move();
        after();
    }

    @Override
    public void stop() {
        before();
        movable.stop();
        after();
    }
}
