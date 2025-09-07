package Eureka;

import Eureka.Task.Task;

import java.io.*;
import java.util.ArrayList;

public class Storage {

    private String filePath;
    private ArrayList<Task> taskList = new ArrayList<>();

    Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     *
     * @return
     */
    public ArrayList<Task> load() {
        File f = new File(filePath);
        if (!f.exists()) {
            try {
                File parentDir = f.getParentFile();
                if (parentDir != null && !parentDir.exists()) {
                    parentDir.mkdirs(); // create "data/" folder if missing
                }

                if (f.createNewFile()) {
                    System.out.println("Created new history file: " + f.getAbsolutePath());
                } else {
                    System.out.println("Failed to create history file.");
                }
            } catch (IOException e) {
                System.out.println("Error creating file: " + e.getMessage());
            }
            return taskList;
        }

        try (BufferedReader r = new BufferedReader(new FileReader(f))) {
            String line;
            while ((line = r.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;
                try {
                    taskList.add(Task.parse(line));
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return taskList;
    }

    public void updateFile() {
        try (FileWriter writer = new FileWriter(filePath)) {
            for (Task task : taskList) {
                writer.write(task.serialise() + System.lineSeparator());
            }
        } catch (IOException e) {
            System.out.println("Error updating file: " + e.getMessage());
        }
    }
}
