package example.thread.many2many;

/**
 * @author trink
 */
public class In implements Runnable {

    private Base b;

    public In(Base b) {
        super();
        this.b = b;
    }

    @Override
    public void run() {
        while (true) {
            b.in();
        }
    }
}
