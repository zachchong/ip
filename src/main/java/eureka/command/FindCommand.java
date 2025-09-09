package eureka.command;

import eureka.Storage;
import eureka.Task.Task;
import eureka.Task.TaskList;
import eureka.ui.Ui;

import java.util.ArrayList;

public class FindCommand extends Command {

    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Task> results = tasks.find(keyword);

        if (results.isEmpty()) {
            return "No matching tasks found for keyword: " + keyword;
        }

        ui.showLine();

        System.out.println("Here are the matching tasks in your list:");
        for (Task result : results) {
            System.out.println(result.toString());
        }

        ui.showLine();

        StringBuilder sb = new StringBuilder();
        sb.append("Here are the matching tasks in your list:\n");

        for (int i = 0; i < results.size(); i++) {
            sb.append((i + 1)).append(". ").append(results.get(i).toString()).append("\n");
        }

        return sb.toString().trim(); // remove last newline
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
