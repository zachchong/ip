import java.util.Scanner;

public class Eureka {

    private static String input;
    private static String[] taskList = new String[100];
    private static int taskListCount = 0;

    public static void main(String[] args) {
        String CHATBOT_NAME = "Eureka";
        System.out.println("_____________________________");
        System.out.println("Hello! I'm " + CHATBOT_NAME);
        System.out.println("What can I do for you?");
        System.out.println("_____________________________");

        Scanner scanner = new Scanner(System.in);
        input = scanner.nextLine();

        while (true) {
            switch (input) {
                case "bye":
                    System.out.println("_____________________________");
                    System.out.println("Bye. Hope to see you again soon!");
                    System.out.println("_____________________________");
                    return;
                case "list":
                    System.out.println("_____________________________");
                    for (int i = 0; i < taskListCount; i++) {
                        System.out.println(i + 1 + ". " + taskList[i]);
                    }
                    System.out.println("_____________________________");
                    input = scanner.nextLine();
                    break;
                default:
                    taskList[taskListCount] = input;
                    taskListCount++;
                    System.out.println("_____________________________");
                    System.out.println("added: " + input);
                    System.out.println("_____________________________");
                    input = scanner.nextLine();
            }
        }

    }
}
