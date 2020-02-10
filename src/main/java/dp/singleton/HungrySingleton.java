package dp.singleton;

import java.util.Random;

/**
 * @author trink
 */
public class HungrySingleton {

    private static final HungrySingleton INSTANCE = new HungrySingleton();

    private int id;

    private HungrySingleton() {
        id = new Random().nextInt(999999);
    }

    public synchronized static HungrySingleton getInstance() {
        return INSTANCE;
    }

    public int getId() {
        return id;
    }
}
