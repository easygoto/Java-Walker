package com.trink.Factory.BeanFactory;

public class Train implements Movable {
    @Override
    public void run() {
        System.out.println("Train run ...");
    }
}
