package greg.ui;

import java.util.Scanner;

/**
 * Handles all interactions with the user, such as reading input
 * and displaying messages, errors, and the welcome screen.
 */
public class Ui {
    private final String LINE = "--------------------------------------------";
    private final Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Prints the welcome message and Greg logo.
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
        System.out.println(" How may I be of service to you today?");
        showLine();
    }

    /**
     * Reads the next line of input from the user.
     * @return The trimmed input string.
     */
    public String readCommand() {
        return scanner.nextLine().trim();
    }

    /**
     * Displays the decorative divider line.
     */
    public void showLine() {
        System.out.println(LINE);
    }

    /**
     * Displays an error message to the user.
     * @param message The error message to show.
     */
    public void showError(String message) {
        System.out.println(" [ERROR] " + message);
    }

    /**
     * Displays a general message to the user.
     * @param message The message to show.
     */
    public void showMessage(String message) {
        System.out.println(message);
    }

    /**
     * Closes the scanner resource.
     */
    public void closeScanner() {
        scanner.close();
    }
}