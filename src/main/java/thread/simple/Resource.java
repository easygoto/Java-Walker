package thread.simple;

public class Resource {

    private int num = 20;

    public synchronized void in() {
        while (this.num > 99) {
            try {
                wait();
            } catch (Exception e) {

            }
        }
        System.out.println("生产者 生产..." + (++this.num));
        notify();
    }

    public synchronized void out() {
        while (this.num < 1) {
            try {
                wait();
            } catch (Exception ignored) {
            }
        }
        System.out.println("消费者 消费..." + (--this.num));
        notify();
    }
}
