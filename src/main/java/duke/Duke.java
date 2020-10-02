package duke;
import duke.oop.Storage;
import duke.oop.TaskList;
import duke.oop.Ui;

public class Duke {
    public static final String FILE_PATH = "duke.txt";
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructor of duke
     * @param filepath setting the filepath
     */
    public Duke(String filepath) {
        ui = new Ui();
        storage = new Storage(filepath);
        tasks = new TaskList();
    }

    /**
     * Runs the main function
     */
    public void run(){
        Storage.readFromFile(tasks);
        Ui.printWelcomeMessage();
        ui.dealWithInput(tasks);
        Storage.writeToFile(tasks);
    }

    /**
     * Prints out hello message and initialize duke
     * @param args input arguments
     */
    public static void main(String[] args) {

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        new Duke(FILE_PATH).run();
    }
}