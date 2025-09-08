package eureka.command;

import eureka.Storage;
import eureka.Task.Deadline;
import eureka.Task.TaskList;
import eureka.ui.Ui;

import java.time.LocalDateTime;

public class DeadlineCommand extends Command {

    private String taskName;
    private LocalDateTime by;

    public DeadlineCommand(String taskName, LocalDateTime by) {
        this.taskName = taskName;
        this.by = by;
    }

    /**
     * @param tasks
     * @param ui
     * @param storage
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addTask(new Deadline(by, taskName, false));
        storage.updateFile();
        ui.addTaskMessage(tasks);
    }

    /**
     * @return
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
