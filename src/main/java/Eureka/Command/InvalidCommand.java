package Eureka.Command;

import Eureka.Storage;
import Eureka.Task.TaskList;
import Eureka.Ui.Ui;

public class InvalidCommand extends Command{
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        System.out.println("Invalid Command");
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
