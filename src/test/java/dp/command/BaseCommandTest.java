package dp.command;

import org.junit.Test;

public class BaseCommandTest {

    @Test
    public void test() {

        Boy     boy          = new Boy();
        BaseCommand shopping     = new ShoppingBaseCommand();
        BaseCommand tourism      = new TourismBaseCommand();
        BaseCommand interference = new InterferenceBaseCommand();

        boy.addCommand(shopping)
                .addCommand(tourism)
                .addCommand(interference);

        boy.executeCommands();
        boy.undoCommands();
    }
}
