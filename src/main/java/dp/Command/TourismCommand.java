package dp.Command;

public class TourismCommand extends Command {

    @Override
    boolean execute() {
        System.out.println("tourism ...");
        return true;
    }

    @Override
    boolean undo() {
        System.out.println("cancel tourism ...");
        return true;
    }
}
