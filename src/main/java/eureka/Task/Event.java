package eureka.Task;

import java.time.LocalDateTime;

public class Event extends Task {
    private LocalDateTime start;
    private LocalDateTime end;

    public Event(LocalDateTime start, LocalDateTime end, String taskName, boolean isDone) {
        super(taskName, isDone);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return "[E] " + super.toString() + " (from: " + super.dateTimeFormatter(this.start) + " to: " + super.dateTimeFormatter(this.end) + ")";
    }

    @Override
    public String serialise() {
        return "E | " + super.serialise() + " | " + this.start + " | " + this.end;
    }
}
