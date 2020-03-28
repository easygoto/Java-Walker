package example.thread;

/**
 * @author trink
 */
public class DeadLock implements Runnable {

    private final Object  o1   = new Object();
    private final Object  o2   = new Object();
    private       boolean flag = false;

    @Override
    public void run() {
        while (true) {
            if (flag) {
                synchronized (o1) {
                    System.out.println(Thread.currentThread().getName() + "...if o1...");
                    synchronized (o2) {
                        System.out.println(Thread.currentThread().getName() + "...if o2...");
                        flag = false;
                    }
                }
            } else {
                synchronized (o2) {
                    System.out.println(Thread.currentThread().getName() + "...else o2...");
                    synchronized (o1) {
                        System.out.println(Thread.currentThread().getName() + "...else o1...");
                        flag = true;
                    }
                }
            }
        }
    }
}
