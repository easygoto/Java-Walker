package dp.command;

/**
 * @author trink
 */
public class InterferenceBaseCommand extends BaseCommand {

    @Override
    boolean execute() {
        System.out.println("interference ...");
        return false;
    }

    @Override
    boolean undo() {
        System.out.println("cancel interference ...");
        return true;
    }
}
