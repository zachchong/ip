package Eureka.Task;

import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> taskList;

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public Task getTask(int i) {
        return taskList.get(i);
    }

    public void markTask(int i) {
        taskList.get(i).setIsDone(true);
    }

    public void unmarkTask(int i) {
        taskList.get(i).setIsDone(false);
    }

    public void deleteTask(int i) {
        taskList.remove(i);
    }

    public void addTask(Task task) {
        taskList.add(task);
    }

    public int getCount() {
        return taskList.size();
    }
}
