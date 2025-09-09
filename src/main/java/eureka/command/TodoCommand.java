package eureka.command;

import eureka.Storage;
import eureka.Task.TaskList;
import eureka.Task.Todo;
import eureka.ui.Ui;

public class TodoCommand extends Command {

    private String taskName;

    public TodoCommand(String taskName) {
        this.taskName = taskName;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addTask(new Todo(taskName, false));
        storage.updateFile();
        ui.addTaskMessage(tasks);

        int totalTasks = tasks.getCount();
        String addedTask = tasks.getTask(totalTasks - 1).toString();

        return "Got it. I've added this task:\n"
                + addedTask + "\n"
                + "Now you have " + totalTasks + " task"
                + (totalTasks > 1 ? "s" : "") + " in the list.";
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
