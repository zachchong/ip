package Eureka.Command;

import Eureka.Storage;
import Eureka.Task.TaskList;
import Eureka.Ui.Ui;

public abstract class Command {

    public abstract void execute(TaskList tasks, Ui ui, Storage storage);

    public abstract boolean isExit();
}
