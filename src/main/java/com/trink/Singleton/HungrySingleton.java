package com.trink.Singleton;

import java.util.Random;

public class HungrySingleton {

    private static final HungrySingleton instance = new HungrySingleton();

    private int id;

    private HungrySingleton() {
        id = new Random().nextInt(999999);
    }

    public synchronized static HungrySingleton getInstance() {
        return instance;
    }

    public int getId() {
        return id;
    }
}
