package eureka.command;

import eureka.Storage;
import eureka.Task.TaskList;
import eureka.ui.Ui;

public class MarkCommand extends Command {

    private int index;

    public MarkCommand(int index) {
        this.index = index;
    }

    //For Testing Purpose
    public int getIndex() {
        return index;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        if (index < 0 || index >= tasks.getCount()) {
            return "⚠️ Oops! Invalid task index.";
        }

        if (index >= 0 && index < tasks.getCount()) {
            tasks.markTask(index);
        }

        storage.updateFile();

        ui.showLine();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(tasks.getTask(index).toString());
        ui.showLine();

        return "Nice! I've marked this task as done:\n"
                + tasks.getTask(index).toString();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
