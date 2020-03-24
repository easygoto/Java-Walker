package baseapi.thread.many2Many;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Base {

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
