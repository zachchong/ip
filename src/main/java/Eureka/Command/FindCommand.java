package Eureka.Command;

import Eureka.Storage;
import Eureka.Task.Task;
import Eureka.Task.TaskList;
import Eureka.Ui.Ui;

import java.util.ArrayList;

public class FindCommand extends Command{

    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Task> results = tasks.find(keyword);
        ui.showLine();
        System.out.println("Here are the matching tasks in your list:");
        for (Task result : results) {
            System.out.println(result.toString());
        }
        ui.showLine();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
