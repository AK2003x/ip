import java.util.Scanner;

/**
 * Runs the Greg chatbot greeting program.
 * Prints an ASCII logo of GREG and a short greeting
 * Prints an intro message, then supports adding and listing tasks until the user enters "bye"
 */
public class Greg {
    /**
     * Starts the program, reads user input, stores tasks, lists tasks, and exits on "bye"
     *
     * @param args Command-line arguments.
     */
    public static void main(String[] args) {
        // Display name of Chatbot
        String chatbotName = "Greg";
        String line = "--------------------------------------------";

        // Display Logo of Chatbot
        String logo =
                "  ____   ____   _____   ____  \n"
                        + " / ___| |  _ \\ | ____| / ___| \n"
                        + "| |  _  | |_) ||  _|  | |  _  \n"
                        + "| |_| | |  _ < | |___ | |_| | \n"
                        + " \\____| |_| \\_\\|_____| \\____| \n";

        // Print introduction once
        System.out.println(line);
        System.out.println(logo);
        System.out.println(line);
        System.out.println(" GREETINGS! I'm " + chatbotName + " :)))");
        System.out.println(" How can I be of service to you today my good sir!");
        System.out.println(line);

        // Reads user input
        Scanner sc = new Scanner(System.in);

        // Array to store 100 user inputs
        String[] userTasks = new String[100];

        // Tracks the number of userInputs so far
        int taskCount = 0;

        while (true) {
            String input = sc.nextLine();

            // bye Command: Exits loop
            if (input.equals("bye")) {
                System.out.println(line);
                System.out.println(" Bye. Hope to see you again soon!");
                System.out.println(line);
                break;
            }

            // list Command: Prints a numbered list of stored userInputs
            if (input.equals("list")) {
                System.out.println(line);
                for (int i = 0; i < taskCount; i++) {
                    System.out.println((i + 1) + ". " + userTasks[i]);
                }
                System.out.println(line);
                continue;
            }

            // Store userInput into array & increment userInput count
            userTasks[taskCount] = input;
            taskCount++;

            System.out.println(line);
            System.out.println("added: " + input);
            System.out.println(line);
        }
        sc.close();
    }
}
