package eureka.Task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Task {
    private String taskName;
    private boolean isDone;

    Task(String taskName, boolean isDone) {
        this.taskName = taskName;
        this.isDone = isDone;
    }

    public void setIsDone(boolean isDone) {
        this.isDone = isDone;
    }

    @Override
    public String toString() {
        if (this.isDone) {
            return "[X] " + this.taskName;
        } else {
            return "[ ] " + this.taskName;
        }
    }

    public String serialise() {
        if (this.isDone) {
            return "1" + " | " + this.taskName;
        } else {
            return "0" + " | " + this.taskName;
        }
    }

    public static Task parse(String line) {
        String[] parts = line.split("\\s*\\|\\s*"); // split according to |

        String type = parts[0];
        boolean isDone = "1".equals(parts[1]);
        String taskName = parts[2];

        switch (type) {
        case "T":
            return new Todo(taskName, isDone);
        case "D":
            return new Deadline(LocalDateTime.parse(parts[3]), taskName, isDone);
        case "E":
            return new Event(LocalDateTime.parse(parts[3]), LocalDateTime.parse(parts[4]), taskName, isDone);
        default:
            throw new IllegalArgumentException("Unknown type: " + parts[0]);
        }

    }

    public String dateTimeFormatter(LocalDateTime dateTime) {
        DateTimeFormatter custom1 = DateTimeFormatter.ofPattern("EEEE, MMMM dd yyyy HH:mm");
        return dateTime.format(custom1);
    }

    public boolean contains(String keyword) {
        return taskName.toLowerCase().contains(keyword.toLowerCase());
    }


}
