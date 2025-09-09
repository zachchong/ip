package eureka.command;

import eureka.Storage;
import eureka.Task.TaskList;
import eureka.ui.Ui;

public class DeleteCommand extends Command {

    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * @param tasks
     * @param ui
     * @param storage
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {

        String deletedInfo = "";

        if (index >= 0 && index < tasks.getCount()) {
            deletedInfo = tasks.getTask(index).toString();
            tasks.deleteTask(index);
        } else {
            return "⚠️ Oops! Invalid task index.";
        }

        storage.updateFile();

        ui.showLine();
        System.out.println("Noted. I've removed this task:");
        System.out.println(deletedInfo);
        System.out.println("Now you have " + tasks.getCount() + " tasks in the list.");
        ui.showLine();

        return "Noted. I've removed this task:\n"
                + deletedInfo + "\n"
                + "Now you have " + tasks.getCount() + " task"
                + (tasks.getCount() > 1 ? "s" : "") + " in the list.";
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
