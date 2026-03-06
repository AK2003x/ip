package greg;

import greg.storage.Storage;
import greg.tasklist.TaskList;
import greg.ui.Ui;
import greg.parser.Parser;
import greg.exception.GregException;

/**
 * Acts as the main controller for the Greg chatbot application.
 * This class initializes the user interface, storage systems, and task management
 * components, then enters the main execution loop to handle user interaction.
 */
public class Greg {

    /** The storage component responsible for reading and writing task data to the hard disk. */
    private final Storage storage;

    /** The task list component that maintains the current set of tasks in memory. */
    private final TaskList tasks;

    /** The user interface component that manages input and output for the application. */
    private final Ui ui;

    /**
     * Initialises the Greg chatbot with the specified file path for data storage.
     * During initialisation, it loads existing task data from the disk into memory.
     *
     * @param filePath The path to the text file where task data is persisted.
     */
    public Greg(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = storage.load();
    }

    /**
     * Runs the main program loop.
     * It displays a welcome message and continuously waits for user commands.
     * Commands are delegated to the Parser until the user signals an exit with "bye".
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;

        while (!isExit) {
            String fullCommand = ui.readCommand();

            if (fullCommand.equalsIgnoreCase("bye")) {
                ui.showLine();
                ui.showMessage(" GOODBYEEE!!! Hope to see you again soon!");
                ui.showLine();
                isExit = true;
            } else {
                try {
                    Parser.parseAndExecute(fullCommand, tasks, ui, storage);
                } catch (GregException e) {
                    ui.showLine();
                    ui.showError(e.getMessage());
                    ui.showLine();
                }
            }
        }
        ui.closeScanner();
    }

    /**
     * Entry point of the application.
     * Instantiates the Greg object and starts the program execution.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        new Greg("data/greg.txt").run();
    }
}