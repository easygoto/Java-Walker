package study.thread.many2Many;

public class Out implements Runnable {

    private Base b;

    public Out(Base b) {
        super();
        this.b = b;
    }

    public void run() {
        while (true) {
            b.out();
        }
    }
}
