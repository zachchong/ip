package eureka.command;

import eureka.Storage;
import eureka.Task.TaskList;
import eureka.ui.Ui;

public class ExitCommand extends Command {
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        ui.bye();
        return "Bye";
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
