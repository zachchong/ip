package eureka.Task;

import java.time.LocalDateTime;

public class Deadline extends Task {
    private LocalDateTime due;

    public Deadline(LocalDateTime due, String taskName, boolean isDone) {
        super(taskName, isDone);
        this.due = due;
    }

    @Override
    public String toString() {
        String dueStr = (due != null) ? dateTimeFormatter(due) : "N/A";
        return "[D] " + super.toString() + " (by: " + dueStr + ")";
    }


    @Override
    public String serialise() {
        return "D | " + super.serialise() + " | " + this.due;
    }

}
