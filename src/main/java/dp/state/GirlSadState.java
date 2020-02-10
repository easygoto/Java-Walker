package dp.state;

/**
 * @author trink
 */
public class GirlSadState extends BaseGirlState {

    @Override
    void smile() {
        System.out.println("Sad smile ...");
    }

    @Override
    void cry() {
        System.out.println("Sad cry ...");
    }

    @Override
    void say() {
        System.out.println("Sad say ...");
    }
}
