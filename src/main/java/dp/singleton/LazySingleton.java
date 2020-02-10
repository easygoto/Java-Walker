package dp.singleton;

import java.util.Random;

/**
 * @author trink
 */
public class LazySingleton {

    private static volatile LazySingleton instance = null;

    private int id;

    private LazySingleton() {
        id = new Random().nextInt(999999);
    }

    public static synchronized LazySingleton getInstance() {
        if (instance == null) {
            instance = new LazySingleton();
        }
        return instance;
    }

    public int getId() {
        return id;
    }
}
