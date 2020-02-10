package dp.state;

import org.junit.Test;

public class StateTest {

    @Test
    public void test() {

        Girl girl = new Girl();
        girl.setGirlState(new GirlHappyState());
        girl.say();
        girl.cry();
        girl.smile();

        girl.setGirlState(new GirlSadState());
        girl.say();
        girl.cry();
        girl.smile();
    }
}
