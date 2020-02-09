package dp.bridge;

/**
 * @author trink
 */
public class WarmGift extends BaseGift {

    public WarmGift(BaseGiftImpl baseGiftImpl) {
        this.baseGiftImpl = baseGiftImpl;
    }

    @Override
    void show() {
        System.out.println("WarmGift ...");
        if (baseGiftImpl != null) {
            baseGiftImpl.show();
        }
    }
}
