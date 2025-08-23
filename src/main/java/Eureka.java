import java.util.Scanner;

public class Eureka {

    private static String input;
    private static Task[] taskList = new Task[100];
    private static int taskListCount = 0;

    public static void main(String[] args) {
        String CHATBOT_NAME = "Eureka";
        System.out.println("_____________________________");
        System.out.println("Hello! I'm " + CHATBOT_NAME);
        System.out.println("What can I do for you?");
        System.out.println("_____________________________");

        Scanner scanner = new Scanner(System.in);

        while (true) {
            input = scanner.nextLine();
            switch (input) {
                case "bye":
                    System.out.println("_____________________________");
                    System.out.println("Bye. Hope to see you again soon!");
                    System.out.println("_____________________________");
                    return;
                case "list":
                    System.out.println("_____________________________");
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < taskListCount; i++) {
                        System.out.println(i + 1 + ". " + taskList[i].toString());
                    }
                    System.out.println("_____________________________");

                    break;
                default:
                    if (input.startsWith("mark")) {
                        String[] parts = input.split(" ");
                        if (parts.length > 1) {
                            System.out.println(parts[1]);
                            int index = Integer.parseInt(parts[1]) - 1;
                            if (index >= 0 && index < taskListCount) {
                                taskList[index].setIsDone(true);
                            }
                        }
                        System.out.println("_____________________________");
                        System.out.println("Nice! I've marked this task as done:");
                        System.out.println(taskList[Integer.parseInt(parts[1]) - 1].toString());
                        System.out.println("_____________________________");
                    } else if (input.startsWith("unmark")) {
                        String[] parts = input.split(" ");
                        if (parts.length > 1) {
                            int index = Integer.parseInt(parts[1]) - 1;
                            if (index >= 0 && index < taskListCount) {
                                taskList[index].setIsDone(false);
                            }
                        }
                        System.out.println("_____________________________");
                        System.out.println("OK, I've marked this task as not done yet:");
                        System.out.println(taskList[Integer.parseInt(parts[1]) - 1].toString());
                        System.out.println("_____________________________");
                    } else {
                        taskList[taskListCount] = new Task(input, false);
                        taskListCount++;
                        System.out.println("_____________________________");
                        System.out.println("added: " + input);
                        System.out.println("_____________________________");
                    }

            }
        }

    }
}
