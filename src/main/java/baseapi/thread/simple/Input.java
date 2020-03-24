package baseapi.thread.simple;

public class Input implements Runnable {
    private Resource r;

    public Input(Resource r) {
        super();
        this.r = r;
    }

    public void run() {
        while (true) {
            r.in();
        }
    }
}
