package eureka.command;

import eureka.Storage;
import eureka.Task.TaskList;
import eureka.ui.Ui;

/**
 * Represents a command that lists all tasks in the {@link TaskList}.
 * <p>
 * When executed, this command prints the tasks to the console via {@link Ui}
 * and also returns a formatted string for GUI display. If the list is empty,
 * it notifies the user accordingly.
 */
public class ListCommand extends Command {

    /**
     * Executes the list command by displaying all tasks in the {@link TaskList}.
     * Also formats the tasks into a string for GUI display.
     *
     * @param tasks   the task list whose tasks are to be listed
     * @param ui      the user interface used to display tasks
     * @param storage the storage (not used in this command)
     * @return a formatted string containing all tasks,
     *         or a message indicating the list is empty
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showLine();
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.getCount(); i++) {
            System.out.println(i + 1 + ". " + tasks.getTask(i).toString());
        }
        ui.showLine();

        if (tasks.getCount() == 0) {
            return "Your task list is empty!";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("Here are the tasks in your list:\n");

        for (int i = 0; i < tasks.getCount(); i++) {
            sb.append(i + 1).append(". ")
                    .append(tasks.getTask(i).toString())
                    .append("\n");
        }

        return sb.toString().trim(); // remove trailing newline
    }

    /**
     * Indicates whether this command will terminate the program.
     *
     * @return false, since listing tasks does not exit the application
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
