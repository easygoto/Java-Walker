package dp.command;

/**
 * @author trink
 */
public class TourismBaseCommand extends BaseCommand {

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
