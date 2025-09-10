package eureka.command;

import eureka.Storage;
import eureka.Task.Deadline;
import eureka.Task.TaskList;
import eureka.ui.Ui;

import java.time.LocalDateTime;

/**
 * Represents a command that adds a {@link Deadline} task to the task list.
 * <p>
 * A deadline task includes a description and a specific due date/time.
 * When executed, the command updates the task list, saves it to storage,
 * and notifies the user via the UI.
 */
public class DeadlineCommand extends Command {

    private String taskName;
    private LocalDateTime by;

    /**
     * Constructs a DeadlineCommand with the specified task name and deadline.
     *
     * @param taskName the description of the deadline task
     * @param by       the due date and time of the task
     */
    public DeadlineCommand(String taskName, LocalDateTime by) {
        this.taskName = taskName;
        this.by = by;
    }

    /**
     * Executes the command by creating a new {@link Deadline} task,
     * adding it to the {@link TaskList}, saving it to {@link Storage},
     * and showing a confirmation message in the {@link Ui}.
     *
     * @param tasks   the task list to add the new deadline task into
     * @param ui      the user interface used to display feedback
     * @param storage the storage used to persist the updated task list
     * @return a confirmation message containing the details of the added task
     *         and the updated number of tasks
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addTask(new Deadline(by, taskName, false));
        storage.updateFile();
        ui.addTaskMessage(tasks);
        int totalTasks = tasks.getCount();
        assert tasks.getTask(totalTasks - 1) != null : "last task should exist";
        String addedTask = tasks.getTask(totalTasks - 1).toString();

        String isPlural = totalTasks > 1 ? "s" : "";
        return "Got it. I've added this task:\n"
                + addedTask + "\n"
                + "Now you have " + totalTasks + " task"
                + isPlural + " in the list.";
    }

    /**
     * Indicates whether this command will terminate the program.
     *
     * @return false, since adding a deadline task does not exit the program
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
