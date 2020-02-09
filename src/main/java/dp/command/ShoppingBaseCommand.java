package dp.command;

/**
 * @author trink
 */
public class ShoppingBaseCommand extends BaseCommand {

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
