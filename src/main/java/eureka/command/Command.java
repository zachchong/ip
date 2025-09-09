package eureka.command;

import eureka.Storage;
import eureka.Task.TaskList;
import eureka.ui.Ui;

public abstract class Command {

    public abstract String execute(TaskList tasks, Ui ui, Storage storage);

    public abstract boolean isExit();
}
