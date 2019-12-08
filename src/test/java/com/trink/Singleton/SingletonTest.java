package com.trink.Singleton;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class SingletonTest {

    @Test
    public void lazy() throws InterruptedException {
        int           times = 10;
        List<Integer> list  = new ArrayList<>();
        while ((times--) > 0) {
            Thread thread = new Thread(() -> {
                list.add(LazySingleton.getInstance().getId());
            });
            thread.start();
            thread.join();
        }
        list.forEach(System.out::println);
    }

    @Test
    public void hungry() throws InterruptedException {
        int           times = 10;
        List<Integer> list  = new ArrayList<>();
        while ((times--) > 0) {
            Thread thread = new Thread(() -> {
                list.add(HungrySingleton.getInstance().getId());
            });
            thread.start();
            thread.join();
        }
        list.forEach(System.out::println);
    }
}
