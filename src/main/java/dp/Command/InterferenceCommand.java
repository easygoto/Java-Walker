package dp.Command;

public class InterferenceCommand extends Command {

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
