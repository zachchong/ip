package Eureka.Command;

import Eureka.Storage;
import Eureka.Task.TaskList;
import Eureka.Ui.Ui;

public class ListCommand extends Command{
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showLine();
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.getCount(); i++) {
            System.out.println(i + 1 + ". " + tasks.getTask(i).toString());
        }
        ui.showLine();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
