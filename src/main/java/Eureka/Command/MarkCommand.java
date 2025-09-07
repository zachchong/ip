package Eureka.Command;

import Eureka.Storage;
import Eureka.Task.TaskList;
import Eureka.Ui.Ui;

public class MarkCommand extends Command{

    private int index;

    public MarkCommand(int index) {
        this.index = index;
    }

    //For Testing Purpose
    public int getIndex() {
        return index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (index >= 0 && index < tasks.getCount()) {
            tasks.markTask(index);
        }

        storage.updateFile();

        ui.showLine();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(tasks.getTask(index).toString());
        ui.showLine();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
