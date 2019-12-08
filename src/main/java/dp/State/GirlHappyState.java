package dp.State;

public class GirlHappyState extends GirlState {

    @Override
    void smile() {
        System.out.println("Happy smile ...");
    }

    @Override
    void cry() {
        System.out.println("Happy cry ...");
    }

    @Override
    void say() {
        System.out.println("Happy say ...");
    }
}
