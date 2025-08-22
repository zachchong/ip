import java.util.Scanner;

public class Eureka {

    private static String input;

    public static void main(String[] args) {
        String CHATBOT_NAME = "Eureka";
        System.out.println("_____________________________");
        System.out.println("Hello! I'm " + CHATBOT_NAME);
        System.out.println("What can I do for you?");
        System.out.println("_____________________________");

        Scanner scanner = new Scanner(System.in);
        input = scanner.nextLine();

        while (!input.equals("bye")) {
            System.out.println("_____________________________");
            System.out.println(input);
            System.out.println("_____________________________");
            input = scanner.nextLine();
        }

        System.out.println("_____________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("_____________________________");


    }
}
