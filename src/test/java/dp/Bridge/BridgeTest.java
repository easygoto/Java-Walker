package dp.Bridge;

import org.junit.Test;

public class BridgeTest {

    @Test
    public void warmFlower() {
        Gift gift = new WarmGift(new Flower());
        gift.show();
    }

    @Test
    public void warmRing() {
        Gift gift = new WarmGift(new Ring());
        gift.show();
    }

    @Test
    public void wildFlower() {
        Gift gift = new WildGift(new Flower());
        gift.show();
    }
}
