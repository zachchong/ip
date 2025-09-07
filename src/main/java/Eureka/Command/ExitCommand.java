package Eureka.Command;

import Eureka.Storage;
import Eureka.Task.TaskList;
import Eureka.Ui.Ui;

public class ExitCommand extends Command{
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.bye();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
