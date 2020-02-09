package dp.command;

import java.util.ArrayList;
import java.util.List;

/**
 * @author trink
 */
public class Boy {

    private String name;

    private List<BaseCommand> baseCommands         = new ArrayList<>();
    private List<BaseCommand> executedBaseCommands = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boy addCommand(BaseCommand baseCommand) {
        baseCommands.add(baseCommand);
        return this;
    }

    public Boy addCommand(List<BaseCommand> baseCommands) {
        this.baseCommands.addAll(baseCommands);
        return this;
    }

    public void executeCommands() {
        for (BaseCommand baseCommand : baseCommands) {
            if (baseCommand.execute()) {
                executedBaseCommands.add(0, baseCommand);
            }
        }
    }

    public void undoCommands() {
        for (BaseCommand baseCommand : executedBaseCommands) {
            baseCommand.undo();
        }
    }
}
