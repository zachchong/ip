package Eureka.Command;

import Eureka.Storage;
import Eureka.Task.Event;
import Eureka.Task.TaskList;
import Eureka.Ui.Ui;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public class EventCommand extends Command{

    private String taskName;
    private LocalDateTime from;
    private LocalDateTime to;

    /**
     *
     * @param taskName
     * @param from
     * @param to
     */
    public EventCommand(String taskName, LocalDateTime from, LocalDateTime to) {
        this.taskName = taskName;
        this.from = from;
        this.to = to;
    }

    // For testing purpose
    public LocalDateTime getFrom() {
        return from;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addTask(new Event(from, to, taskName, false));
        storage.updateFile();
        ui.addTaskMessage(tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
