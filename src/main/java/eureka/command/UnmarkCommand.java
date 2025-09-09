package eureka.command;

import eureka.Storage;
import eureka.Task.TaskList;
import eureka.ui.Ui;

public class UnmarkCommand extends Command {

    private int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {

        if (index < 0 || index >= tasks.getCount()) {
            return "⚠️ Oops! Invalid task index.";
        }

        if (index >= 0 && index < tasks.getCount()) {
            tasks.unmarkTask(index);
        }

        storage.updateFile();

        ui.showLine();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(tasks.getTask(index).toString());
        ui.showLine();

        return "OK, I've marked this task as not done yet:\n"
                + tasks.getTask(index).toString();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
