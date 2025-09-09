package eureka;

import eureka.command.Command;
import eureka.Task.TaskList;
import eureka.ui.Ui;

public class Eureka {


    private TaskList tasks;
    private Storage storage;
    private Ui ui;

    public Eureka(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (Exception e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (Exception e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public String getResponse(String input) {
        return "Duke heard: " + input;
    }

    public TaskList getTasks() {
        return tasks;
    }

    public Storage getStorage() {
        return storage;
    }

    public Ui getUi() {
        return ui;
    }


    public static void main(String[] args) {
        new Eureka("data/record.txt").run();
    }
}
