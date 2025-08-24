import javafx.css.Match;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Eureka {

    private static String input;
    private static ArrayList<Task> taskList = new ArrayList<>();
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
                        System.out.println(i + 1 + ". " + taskList.get(i).toString());
                    }
                    System.out.println("_____________________________");
                    break;
                default:
                    if (input.startsWith("mark")) {
                        String[] parts = input.split(" ");
                        if (parts.length > 1) {
                            int index = Integer.parseInt(parts[1]) - 1;
                            if (index >= 0 && index < taskListCount) {
                                taskList.get(index).setIsDone(true);
                            }
                        }
                        System.out.println("_____________________________");
                        System.out.println("Nice! I've marked this task as done:");
                        System.out.println(taskList.get(Integer.parseInt(parts[1]) - 1).toString());
                        System.out.println("_____________________________");
                    } else if (input.startsWith("unmark")) {
                        String[] parts = input.split(" ");
                        if (parts.length > 1) {
                            int index = Integer.parseInt(parts[1]) - 1;
                            if (index >= 0 && index < taskListCount) {
                                taskList.get(index).setIsDone(false);
                            }
                        }
                        System.out.println("_____________________________");
                        System.out.println("OK, I've marked this task as not done yet:");
                        System.out.println(taskList.get(Integer.parseInt(parts[1]) - 1).toString());
                        System.out.println("_____________________________");
                    } else if (input.startsWith("delete")) {
                        String[] parts = input.split(" ");
                        String deletedInfo = "";
                        if (parts.length > 1) {
                            int index = Integer.parseInt(parts[1]) - 1;
                            if (index >= 0 && index < taskListCount) {
                                deletedInfo = taskList.get(index).toString();
                                taskList.remove(index);
                                taskListCount--;
                            }
                        }
                        System.out.println("_____________________________");
                        System.out.println("Noted. I've removed this task:");
                        System.out.println(deletedInfo);
                        System.out.println("Now you have " + taskListCount + " tasks in the list.");
                        System.out.println("_____________________________");
                    } else {
                        if (input.startsWith("todo")) {
                            Pattern p = Pattern.compile("^\\s*(?<cmd>\\w+)\\s+(?<taskName>.+)\\s*$");
                            Matcher m = p.matcher(input);
                            if (!m.matches()) {
                                System.out.println("_____________________________");
                                System.out.println("Invalid todo format. Task Name cannot be empty. Eg: todo homework");
                                System.out.println("_____________________________");
                                break;
                            }
                            String taskName = m.group("taskName");

                            taskList.add(new Todo(taskName));
                            taskListCount++;

                        } else if (input.startsWith("deadline")) {
                            Pattern p = Pattern.compile("^\\s*(?<cmd>\\w+)\\s+(?<taskName>.+?)\\s+/by\\s+(?<by>.+)\\s*$");
                            Matcher m = p.matcher(input);
                            if (!m.matches()) {
                                System.out.println("_____________________________");
                                System.out.println("Invalid deadline format. Eg: deadline homework /by today");
                                System.out.println("_____________________________");
                                break;
                            }
                            String taskName = m.group("taskName");
                            String by = m.group("by");

                            taskList.add(new Deadline(by, taskName));
                            taskListCount++;

                        } else if (input.startsWith("event")) {
                            Pattern p = Pattern.compile("^\\s*(?<cmd>\\w+)\\s+(?<taskName>.+?)\\s+/from\\s+(?<from>.+?)\\s+/to\\s+(?<to>.+)\\s*$");
                            Matcher m = p.matcher(input);
                            if (!m.matches()) {
                                System.out.println("_____________________________");
                                System.out.println("Invalid event format. Eg: event orientation /from today /to tomorrow");
                                System.out.println("_____________________________");
                                break;
                            }
                            String taskName = m.group("taskName");
                            String from = m.group("from");
                            String to = m.group("to");

                            taskList.add(new Event(from, to, taskName));
                            taskListCount++;
                        }
                        System.out.println("_____________________________");
                        System.out.println("Got it. I've added this task:");
                        System.out.println(taskList.get(taskListCount - 1).toString());
                        System.out.println("Now you have " + taskListCount + " tasks in the list.");
                        System.out.println("_____________________________");
                    }

            }
        }
    }
}
