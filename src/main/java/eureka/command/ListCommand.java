package eureka.command;

import eureka.Storage;
import eureka.Task.TaskList;
import eureka.ui.Ui;

public class ListCommand extends Command {
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

    @Override
    public boolean isExit() {
        return false;
    }
}
