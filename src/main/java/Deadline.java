public class Deadline extends Task{
    private String due;

    Deadline(String due, String taskName, boolean isDone) {
        super(taskName, isDone);
        this.due = due;
    }

    @Override
    public String toString() {
        return "[D] " + super.toString() + " (by: " + this.due +")";
    }

    @Override
    public String serialise() {
        return "D | " + super.serialise() + " | " + this.due;
    }
}
