package dp.Command;

import org.junit.Test;

public class CommandTest {

    @Test
    public void test() {

        Boy     boy          = new Boy();
        Command shopping     = new ShoppingCommand();
        Command tourism      = new TourismCommand();
        Command interference = new InterferenceCommand();

        boy.addCommand(shopping)
                .addCommand(tourism)
                .addCommand(interference);

        boy.executeCommands();
        boy.undoCommands();
    }
}
