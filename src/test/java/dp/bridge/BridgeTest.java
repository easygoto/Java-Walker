package dp.bridge;

import org.junit.Test;

public class BridgeTest {

    @Test
    public void warmFlower() {
        new WarmGift(new Flower()).show();
    }

    @Test
    public void warmRing() {
        new WarmGift(new Ring()).show();
    }

    @Test
    public void wildFlower() {
        new WildGift(new Flower()).show();
    }
}
