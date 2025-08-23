public class Event extends Task{
    private String start;
    private String end;

    Event(String start, String end, String taskName) {
        super(taskName, false);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return "[E] " + super.toString() + " (from: " + this.start + " to: " + this.end + ")";
    }
}
