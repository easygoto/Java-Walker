package dp.bridge;

/**
 * @author trink
 */
public class WildGift extends BaseGift {

    public WildGift(BaseGiftImpl baseGiftImpl) {
        this.baseGiftImpl = baseGiftImpl;
    }

    @Override
    void show() {
        System.out.println("WildGift ...");
        if (baseGiftImpl != null) {
            baseGiftImpl.show();
        }
    }
}
