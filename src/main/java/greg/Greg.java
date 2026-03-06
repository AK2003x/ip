package greg;

import greg.exception.GregException;
import greg.parser.Parser;
import greg.storage.Storage;
import greg.tasklist.TaskList;


/**
 * Runs the Greg chatbot program.
 * Acts as the main entry point, initializing the UI and looping for user input.
 * It delegates command parsing and execution to the greg.parser.Parser
 */
public class Greg {
    public static void main(String[] args) {
        greg.Ui ui = new greg.Ui();
        Storage storage = new Storage("data/greg.txt");
        TaskList tasks = storage.load();

        ui.showWelcome();

        while (true) {
            String input = ui.readCommand();

            if (input.equals("bye")) {
                ui.showLine();
                ui.showMessage(" Bye. Hope to see you again soon!");
                ui.showLine();
                break;
            }

            try {
                // Notice we now pass 'ui' to the parser instead of a raw String 'line'
                Parser.parseAndExecute(input, tasks, ui, storage);
                storage.save(tasks);
            } catch (GregException e) {
                ui.showLine();
                ui.showError(e.getMessage());
                ui.showLine();
            }
        }
        ui.closeScanner();
    }
}