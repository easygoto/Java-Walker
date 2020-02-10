package dp.proxy.simple;

/**
 * @author trink
 */
public class MovableTimeProxy implements Movable {

    private Movable movable;

    private long startTime;

    MovableTimeProxy(Movable movable) {
        super();
        this.movable = movable;
    }

    private void before() {
        startTime = System.currentTimeMillis();
    }

    private void after() {
        long stopTime = System.currentTimeMillis();
        System.out.println("Run Time: " + (stopTime - startTime) + " ms");
    }

    @Override
    public void move() {
        before();
        movable.move();
        after();
    }

    @Override
    public void stop() {
        before();
        movable.stop();
        after();
    }
}
