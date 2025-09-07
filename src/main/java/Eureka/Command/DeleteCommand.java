package Eureka.Command;

import Eureka.Storage;
import Eureka.Task.TaskList;
import Eureka.Ui.Ui;

public class DeleteCommand extends Command{

    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {

        String deletedInfo = "";

        if (index >= 0 && index < tasks.getCount()) {
            deletedInfo = tasks.getTask(index).toString();
            tasks.deleteTask(index);
        }

        storage.updateFile();

        ui.showLine();
        System.out.println("Noted. I've removed this task:");
        System.out.println(deletedInfo);
        System.out.println("Now you have " + tasks.getCount() + " tasks in the list.");
        ui.showLine();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
