package dp.State;

public class GirlSadState extends GirlState {

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
