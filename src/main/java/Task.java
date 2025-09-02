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
                return new Deadline(parts[3], taskName, isDone);
            case "E":
                return new Event(parts[3], parts[4], taskName, isDone);
            default:
                throw new IllegalArgumentException("Unknown type: " + parts[0]);
        }

    }


}
