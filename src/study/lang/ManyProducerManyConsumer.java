package study.lang;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ManyProducerManyConsumer {

    public static void main(String[] args) {

        Base b    = new Base();
        In   in1  = new In(b);
        In   in2  = new In(b);
        Out  out1 = new Out(b);
        Out  out2 = new Out(b);
        new Thread(in1).start();
        new Thread(in1).start();
        new Thread(in2).start();
        new Thread(in2).start();
        new Thread(out1).start();
        new Thread(out1).start();
        new Thread(out2).start();
        new Thread(out2).start();
    }
}

class Base {

    private int       num  = 100;
    private Lock      lock = new ReentrantLock();
    private Condition in   = lock.newCondition();
    private Condition out  = lock.newCondition();

    public void in() {

        lock.lock();
        try {
            while (this.num > 999) {
                try {
                    in.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(Thread.currentThread().getName() + "生产者 生产..." + (++this.num));
            out.signal();
        } finally {
            lock.unlock();
        }
    }

    public void out() {

        lock.lock();

        try {
            while (this.num < 1) {
                try {
                    out.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(Thread.currentThread().getName() + "消费者 消费..." + (--this.num));
            in.signal();
        } finally {
            lock.unlock();
        }
    }
}

class In implements Runnable {

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

class Out implements Runnable {

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