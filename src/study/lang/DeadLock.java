package study.lang;

class Demo implements Runnable {

    Object o1 = new Object();
    Object o2 = new Object();
    private boolean flag = false;

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

public class DeadLock {

    public static void main(String[] args) {

        Demo d = new Demo();
        new Thread(d).start();
        new Thread(d).start();
        new Thread(d).start();
        new Thread(d).start();
    }

}
