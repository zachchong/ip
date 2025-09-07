package Eureka.Command;

import Eureka.Storage;
import Eureka.Task.TaskList;
import Eureka.Task.Todo;
import Eureka.Ui.Ui;

import java.io.FileWriter;
import java.io.IOException;

public class TodoCommand extends Command{

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
