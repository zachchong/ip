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
        return "[D] " + super.toString() + " (by: " + super.dateTimeFormatter(this.due) + ")";
    }

    @Override
    public String serialise() {
        return "D | " + super.serialise() + " | " + this.due;
    }

}
