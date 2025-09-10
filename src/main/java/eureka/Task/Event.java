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
        String fromStr = (start != null) ? super.dateTimeFormatter(start) : "N/A";
        String toStr   = (end   != null) ? super.dateTimeFormatter(end)   : "N/A";

        return "[E] " + super.toString()
                + " (from: " + fromStr
                + " to: " + toStr + ")";
    }

    @Override
    public String serialise() {
        return "E | " + super.serialise() + " | " + this.start + " | " + this.end;
    }
}
