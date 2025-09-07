package Eureka.Command;

import Eureka.Storage;
import Eureka.Task.TaskList;
import Eureka.Ui.Ui;

public class UnmarkCommand extends Command{

    private int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (index >= 0 && index < tasks.getCount()) {
            tasks.unmarkTask(index);
        }

        storage.updateFile();

        ui.showLine();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(tasks.getTask(index).toString());
        ui.showLine();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
