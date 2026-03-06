package greg;

import greg.storage.Storage;
import greg.tasklist.TaskList;
import greg.ui.Ui;
import greg.parser.Parser;
import greg.exception.GregException;

/**
 * Acts as the main controller for the Greg chatbot.
 * Initializes the required components and starts the main application loop.
 */
public class Greg {

    private final Storage storage;
    private final TaskList tasks;
    private final Ui ui;

    /**
     * Initializes the chatbot with the specified file path for data storage.
     *
     * @param filePath The path to the file where tasks are saved and loaded.
     */
    public Greg(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = storage.load();
    }

    /**
     * Runs the main program loop, reading user commands and delegating them to the Parser.
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
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        new Greg("data/greg.txt").run();
    }
}