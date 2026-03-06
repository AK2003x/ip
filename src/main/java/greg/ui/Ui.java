package greg.ui;

import java.util.Scanner;

/**
 * Handles all interactions with the user for the Greg chatbot.
 * This class is responsible for reading input and displaying formatted messages,
 * errors, and the initial welcome screen.
 */
public class Ui {
    /** The decorative divider line used to separate output blocks. */
    private final String LINE = "--------------------------------------------";

    /** The scanner used to read user input from the standard input stream. */
    private final Scanner scanner;

    /**
     * Constructs a Ui object and initializes the system input scanner.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Prints the welcome message and the Greg ASCII art logo to the console.
     */
    public void showWelcome() {
        String logo =
                "  ____   ____   _____   ____  \n"
                        + " / ___| |  _ \\ | ____| / ___| \n"
                        + "| |  _  | |_) ||  _|  | |  _  \n"
                        + "| |_| | |  _ < | |___ | |_| | \n"
                        + " \\____| |_| \\_\\|_____| \\____| \n";

        showLine();
        System.out.println(logo);
        showLine();
        System.out.println(" GREETINGS! I'm Greg :)))");
        System.out.println(" I am your personal Task Tracking Assistant!");
        showLine();
        showHelp();
        System.out.println(" How may I be of service to you today?");
        showLine();

    }

    /**
     * Reads the next line of input from the user.
     *
     * @return The trimmed input string.
     */
    public String readCommand() {
        return scanner.nextLine().trim();
    }

    /**
     * Displays the decorative divider line to the console.
     */
    public void showLine() {
        System.out.println(LINE);
    }

    /**
     * Displays a formatted error message to the user.
     *
     * @param message The specific error message to be displayed.
     */
    public void showError(String message) {
        System.out.println(" [ERROR] " + message);
    }

    /**
     * Displays a general message to the user.
     *
     * @param message The message string to be displayed.
     */
    public void showMessage(String message) {
        System.out.println(message);
    }

    /**
     * Displays a succinct help menu listing all available commands.
     */
    public void showHelp() {
        System.out.println(" QUICK GUIDE - Commands:");
        System.out.println("  - list: View all tasks");
        System.out.println("  - todo [desc]: Add a task");
        System.out.println("  - deadline [desc] /by [time]: Add a deadline");
        System.out.println("  - event [desc] /from [start] /to [end]: Add an event");
        System.out.println("  - mark [index]: Complete a task");
        System.out.println("  - delete [index]: Remove a task");
        System.out.println("  - find [keyword]: Search tasks");
        System.out.println("  - bye: Exit Greg");
        showLine();
    }

    /**
     * Closes the scanner resource to prevent memory leaks.
     */
    public void closeScanner() {
        scanner.close();
    }
}