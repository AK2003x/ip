package greg;

import java.util.Scanner;
import java.util.ArrayList;
import java.nio.file.Paths;

import greg.exception.GregException;
import greg.parser.Parser;
import greg.task.Task;
import greg.storage.Storage;


/**
 * Runs the Greg chatbot program.
 * Acts as the main entry point, initializing the UI and looping for user input.
 * It delegates command parsing and execution to the greg.parser.Parser
 */
public class Greg {
    /**
     * Starts the Greg chatbot, prints a greeting and logo, then continuously reads user commands
     * until the user enters "bye".
     *
     * Processes input by delegating to greg.parser.Parser
     *
     */
    public static void main(String[] args) {
        // Storage
        Storage storage = new Storage("data/greg.txt");
        ArrayList<Task> tasks = storage.load();

        String chatbotName = "Greg";
        String line = "--------------------------------------------";

        String logo =
                "  ____   ____   _____   ____  \n"
                        + " / ___| |  _ \\ | ____| / ___| \n"
                        + "| |  _  | |_) ||  _|  | |  _  \n"
                        + "| |_| | |  _ < | |___ | |_| | \n"
                        + " \\____| |_| \\_\\|_____| \\____| \n";

        System.out.println(line);
        System.out.println(logo);
        System.out.println(line);
        System.out.println(" GREETINGS! I'm " + chatbotName + " :)))");
        System.out.println(" I am your personal Task Tracking Assistant!");
        System.out.println(" How may I be of service to you today?");
        System.out.println(line);

        Scanner sc = new Scanner(System.in);

        while (true) {
            String input = sc.nextLine().trim();

            // bye command: Exits chatbot
            if (input.equals("bye")) {
                System.out.println(line);
                System.out.println(" Bye. Hope to see you again soon!");
                System.out.println(line);
                break;
            }

            try {
                /**
                 * The Parser interprets the command string and handles the task logic.
                 * Because taskCount is a primitive, we re-assign it to capture updates.
                 */
                Parser.parseAndExecute(input, tasks, line);
                // Save tasks into Storage
                storage.save(tasks);
            } catch (GregException e) {
                /**
                 * Catches chatbot-specific errors and displays them as formatted for the user.
                 */
                System.out.println(line);
                System.out.println(" [ERROR]  " + e.getMessage());
                System.out.println(line);
            }
        }

        sc.close();
    }
}