package eureka.Task;

public class Todo extends Task {
    public Todo(String taskName, boolean isDone) {
        super(taskName, isDone);
    }

    @Override
    public String toString() {
        return "[T] " + super.toString();
    }

    @Override
    public String serialise() {
        return "T | " + super.serialise();
    }
}
