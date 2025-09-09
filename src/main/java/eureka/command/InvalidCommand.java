package eureka.command;

import eureka.Storage;
import eureka.Task.TaskList;
import eureka.ui.Ui;

public class InvalidCommand extends Command {
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        System.out.println("Invalid Command");
        return "Invalid Command";
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
