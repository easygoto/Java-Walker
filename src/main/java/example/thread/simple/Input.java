package example.thread.simple;

/**
 * @author trink
 */
public class Input implements Runnable {
    private Resource r;

    public Input(Resource r) {
        super();
        this.r = r;
    }

    @Override
    public void run() {
        while (true) {
            r.in();
        }
    }
}
