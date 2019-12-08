package dp.Bridge;

public class WarmGift extends Gift {

    public WarmGift(GiftImpl giftImpl) {
        this.giftImpl = giftImpl;
    }

    @Override
    void show() {
        System.out.println("WarmGift ...");
        if (giftImpl != null) {
            giftImpl.show();
        }
    }
}
