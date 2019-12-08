package dp.Command;

import java.util.ArrayList;
import java.util.List;

public class Boy {

    private String name;

    private List<Command> commands = new ArrayList<>();
    private List<Command> executedCommands = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boy addCommand(Command command) {
        commands.add(command);
        return this;
    }

    public Boy addCommand(List<Command> commands) {
        this.commands.addAll(commands);
        return this;
    }

    public void executeCommands() {
        for (Command command : commands) {
            if (command.execute())
                executedCommands.add(0, command);
        }
    }

    public void undoCommands() {
        for (Command command : executedCommands) {
            command.undo();
        }
    }
}
