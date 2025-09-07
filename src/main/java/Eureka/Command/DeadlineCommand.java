package Eureka.Command;

import Eureka.Storage;
import Eureka.Task.Deadline;
import Eureka.Task.TaskList;
import Eureka.Ui.Ui;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public class DeadlineCommand extends Command{

    private String taskName;
    private LocalDateTime by;

    public DeadlineCommand(String taskName, LocalDateTime by) {
        this.taskName = taskName;
        this.by = by;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addTask(new Deadline(by, taskName, false));
        storage.updateFile();
        ui.addTaskMessage(tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
