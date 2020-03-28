package example.thread.many2many;

/**
 * @author trink
 */
public class Out implements Runnable {

    private Base b;

    public Out(Base b) {
        super();
        this.b = b;
    }

    @Override
    public void run() {
        while (true) {
            b.out();
        }
    }
}
