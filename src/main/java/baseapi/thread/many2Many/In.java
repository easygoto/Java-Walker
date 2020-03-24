package baseapi.thread.many2Many;

public class In implements Runnable {

    private Base b;

    public In(Base b) {
        super();
        this.b = b;
    }

    public void run() {
        while (true) {
            b.in();
        }
    }
}
