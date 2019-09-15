package study.thread.simple;

public class Output implements Runnable {

    private Resource r;

    public Output(Resource r) {
        super();
        this.r = r;
    }

    public void run() {
        while (true) {
            r.out();
        }
    }
}
