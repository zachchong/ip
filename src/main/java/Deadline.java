public class Deadline extends Task{
    private String due;

    Deadline(String due, String taskName) {
        super(taskName, false);
        this.due = due;
    }

    @Override
    public String toString() {
        return "[D] " + super.toString() + " (by: " + this.due +")";
    }
}
