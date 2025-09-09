package eureka.command;

import eureka.Storage;
import eureka.Task.Event;
import eureka.Task.TaskList;
import eureka.ui.Ui;

import java.time.LocalDateTime;

public class EventCommand extends Command {

    private String taskName;
    private LocalDateTime from;
    private LocalDateTime to;

    /**
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
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addTask(new Event(from, to, taskName, false));
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
