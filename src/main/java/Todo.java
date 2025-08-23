public class Todo extends Task {
    Todo(String taskName) {
        super(taskName, false);
    }

    @Override
    public String toString() {
        return "[T] " + super.toString();
    }
}
