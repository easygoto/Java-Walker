package dp.Command;

public class ShoppingCommand extends Command {

    @Override
    boolean execute() {
        System.out.println("shopping ...");
        return true;
    }

    @Override
    boolean undo() {
        System.out.println("cancel shopping ...");
        return true;
    }
}
