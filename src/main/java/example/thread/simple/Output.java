package example.thread.simple;

/**
 * @author trink
 */
public class Output implements Runnable {

    private Resource r;

    public Output(Resource r) {
        super();
        this.r = r;
    }

    @Override
    public void run() {
        while (true) {
            r.out();
        }
    }
}
