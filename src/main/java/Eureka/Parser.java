package Eureka;

import Eureka.Command.*;
import java.time.LocalDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {

    /**
     *
     * @param fullCommand
     * @return
     */
    public static Command parse(String fullCommand) {
        switch (fullCommand) {
            case "bye":
                return new ExitCommand();
            case "list":
                return new ListCommand();
            default:
                if (fullCommand.startsWith("mark")) {
                    String[] parts = fullCommand.split(" ");
                    if (parts.length > 1) {
                        int index = Integer.parseInt(parts[1]) - 1;
                        return new MarkCommand(index);
                    }
                } else if (fullCommand.startsWith("unmark")) {
                    String[] parts = fullCommand.split(" ");
                    if (parts.length > 1) {
                        int index = Integer.parseInt(parts[1]) - 1;
                        return new UnmarkCommand(index);
                    }
                } else if (fullCommand.startsWith("delete")) {
                    String[] parts = fullCommand.split(" ");
                    if (parts.length > 1) {
                        int index = Integer.parseInt(parts[1]) - 1;
                        return new DeleteCommand(index);
                    }
                } else {
                    if (fullCommand.startsWith("todo")) {
                        Pattern p = Pattern.compile("^\\s*(?<cmd>\\w+)\\s+(?<taskName>.+)\\s*$");
                        Matcher m = p.matcher(fullCommand);
                        if (!m.matches()) {
                            System.out.println("_____________________________");
                            System.out.println("Invalid todo format. Task Name cannot be empty. Eg: todo homework");
                            System.out.println("_____________________________");
                            break;
                        }
                        String taskName = m.group("taskName");
                        return new TodoCommand(taskName);

                    } else if (fullCommand.startsWith("deadline")) {
                        Pattern p = Pattern.compile("^\\s*(?<cmd>\\w+)\\s+(?<taskName>.+?)\\s+/by\\s+(?<by>.+)\\s*$");
                        Matcher m = p.matcher(fullCommand);
                        if (!m.matches()) {
                            System.out.println("_____________________________");
                            System.out.println("Invalid deadline format. Eg: deadline homework /by \"yyyy-MM-dd HH:mm\"");
                            System.out.println("_____________________________");
                            break;
                        }
                        String taskName = m.group("taskName");

                        try {
                            LocalDateTime by = DateTimeConverter.dateTimeConverter(m.group("by"));
                            return new DeadlineCommand(taskName, by);

                        } catch (IllegalArgumentException e) {
                            System.out.println("_____________________________");
                            System.out.println(e.getMessage());
                            System.out.println("_____________________________");
                            break;
                        }


                    } else if (fullCommand.startsWith("event")) {
                        Pattern p = Pattern.compile("^\\s*(?<cmd>\\w+)\\s+(?<taskName>.+?)\\s+/from\\s+(?<from>.+?)\\s+/to\\s+(?<to>.+)\\s*$");
                        Matcher m = p.matcher(fullCommand);
                        if (!m.matches()) {
                            System.out.println("_____________________________");
                            System.out.println("Invalid event format. Eg: event orientation /from \"yyyy-MM-dd HH:mm\" /to \"yyyy-MM-dd HH:mm\"");
                            System.out.println("_____________________________");
                            break;
                        }
                        String taskName = m.group("taskName");

                        try {
                            LocalDateTime from = DateTimeConverter.dateTimeConverter(m.group("from"));
                            LocalDateTime to = DateTimeConverter.dateTimeConverter(m.group("to"));

                            return new EventCommand(taskName, from, to);

                        } catch (IllegalArgumentException e) {
                            System.out.println("_____________________________");
                            System.out.println(e.getMessage());
                            System.out.println("_____________________________");
                            break;
                        }
                    }
                }

        }
        return new InvalidCommand();
    }
}
