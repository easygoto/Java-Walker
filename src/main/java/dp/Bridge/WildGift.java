package dp.Bridge;

public class WildGift extends Gift {

    public WildGift(GiftImpl giftImpl) {
        this.giftImpl = giftImpl;
    }

    @Override
    void show() {
        System.out.println("WildGift ...");
        if (giftImpl != null) {
            giftImpl.show();
        }
    }
}
