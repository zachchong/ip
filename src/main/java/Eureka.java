import javafx.css.Match;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.time.LocalDate;

public class Eureka {

    private static String input;
    private static ArrayList<Task> taskList = new ArrayList<>();
    private static int taskListCount = 0;
    private static final String HISTORY_FILE = "data/record.txt";

    private static void updateFile() {
        try (FileWriter writer = new FileWriter(HISTORY_FILE)) {
            for (Task task : taskList) {
                writer.write(task.serialise() + System.lineSeparator());
            }
        } catch (IOException e) {
            System.out.println("Error updating file: " + e.getMessage());
        }
    }

    public static void loadTasks() {
        File f = new File(HISTORY_FILE);
        if (!f.exists()) {
            try {
                File parentDir = f.getParentFile();
                if (parentDir != null && !parentDir.exists()) {
                    parentDir.mkdirs(); // create "data/" folder if missing
                }

                if (f.createNewFile()) {
                    System.out.println("Created new history file: " + f.getAbsolutePath());
                } else {
                    System.out.println("Failed to create history file.");
                }
            } catch (IOException e) {
                System.out.println("Error creating file: " + e.getMessage());
            }
            return; // nothing to load yet
        };

        try (BufferedReader r = new BufferedReader(new FileReader(f))) {
            String line;
            while ((line = r.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;
                try {
                    taskList.add(Task.parse(line));
                    taskListCount++;
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    public static void main(String[] args) {

        loadTasks();

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

                        updateFile();

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

                        updateFile();

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

                            updateFile();
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

                            taskList.add(new Todo(taskName, false));
                            taskListCount++;

                            // Save into file
                            try (FileWriter writer = new FileWriter(HISTORY_FILE, true)) {
                                //writer.write(taskType + " | " + "0" + " | " + taskName + System.lineSeparator());
                                writer.write(taskList.get(taskListCount -1).serialise() + System.lineSeparator());
                            } catch (IOException e) {
                                System.out.println("Error writing to file: " + e.getMessage());
                            }

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
                            LocalDate by = LocalDate.parse(m.group("by"));

                            taskList.add(new Deadline(by, taskName, false));
                            taskListCount++;

                            // Save into file
                            try (FileWriter writer = new FileWriter(HISTORY_FILE, true)) {
                                //writer.write(taskType + " | " + "0" + " | " + taskName + " | " + by + System.lineSeparator());
                                writer.write(taskList.get(taskListCount -1).serialise() + System.lineSeparator());
                            } catch (IOException e) {
                                System.out.println("Error writing to file: " + e.getMessage());
                            }

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

                            taskList.add(new Event(from, to, taskName, false));
                            taskListCount++;

                            // Save into file
                            try (FileWriter writer = new FileWriter(HISTORY_FILE, true)) {
                                //writer.write(taskType + " | " + "0" + " | " + taskName + " | from " + from + " | to " + to + System.lineSeparator());
                                writer.write(taskList.get(taskListCount -1).serialise() + System.lineSeparator());
                            } catch (IOException e) {
                                System.out.println("Error writing to file: " + e.getMessage());
                            }
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
