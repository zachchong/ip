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
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addTask(new Todo(taskName, false));
        storage.updateFile();
        ui.addTaskMessage(tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
