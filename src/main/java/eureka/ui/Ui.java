package eureka.ui;

import eureka.Task.TaskList;

import java.util.Scanner;

public class Ui {

    private static String input;

    public Ui() {
    }

    ;

    public void showLoadingError() {
        System.out.println("There is an error loading the tasks history.");
    }

    public void showLine() {
        System.out.println("_____________________________");
    }

    public void showWelcome() {
        String CHATBOT_NAME = "eureka";
        System.out.println("_____________________________");
        System.out.println("Hello! I'm " + CHATBOT_NAME);
        System.out.println("What can I do for you?");
        System.out.println("_____________________________");
    }

    public void showError(String error) {
        System.out.println(error);
    }

    public String readCommand() {
        Scanner scanner = new Scanner(System.in);
        input = scanner.nextLine();
        return input;
    }

    public void bye() {
        System.out.println("_____________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("_____________________________");
    }

    public void addTaskMessage(TaskList taskList) {
        System.out.println("_____________________________");
        System.out.println("Got it. I've added this task:");
        System.out.println(taskList.getTask(taskList.getCount() - 1).toString());
        System.out.println("Now you have " + taskList.getCount() + " tasks in the list.");
        System.out.println("_____________________________");
    }
}
