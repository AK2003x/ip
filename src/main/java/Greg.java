import java.util.Scanner;

/**
 * Runs the Greg chatbot greeting program.
 * Prints an ASCII logo of GREG and a short greeting
 * Prints an intro message,
 * Adds tasks, lists tasks, and supports marking tasks as done.
 */
public class Greg {
    /**
     * Sends an intro message, reads user input, processes commands, and exits on "bye"
     * Input lines that are not "list", "mark", or "bye" are treated as tasks to be stored.
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
        System.out.println(" I am your personal Task Tracking Assistant!");
        System.out.println(" How may I be of service to you today?");
        System.out.println(line);

        // Reads task input
        Scanner sc = new Scanner(System.in);

        // Array to store 100 taskInputs
        String[] userTasks = new String[100];

        // Tracks whether each task is done, where done[i] corresponds to userTasks[i].
        boolean[] completed = new boolean[100];

        // Tracks the number of taskInputs so far
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

            // list Command: Prints a numbered list of stored taskInputs
            if (input.equals("list")) {
                System.out.println(line);
                for (int i = 0; i < taskCount; i++) {
                    System.out.println((i + 1) + "." + (completed[i] ? "[X] " : "[ ] ") + userTasks[i]);
                }
                System.out.println(line);
                continue;
            }

            // mark Command: Marks a designated task "completed"
            if (input.startsWith("mark ")) {
                // Extracts and converts the designated task to interact with into an integer
                int index = Integer.parseInt(input.substring(5).trim()) - 1;

                // Checks if designated task is an existing stored task
                if (index >= 0 && index < taskCount) {
                    completed[index] = true;

                    System.out.println(line);
                    System.out.println("GOOD JOB!!! I have marked this task as completed for you:");
                    System.out.println("  [X] " + userTasks[index]);
                    System.out.println("Keep it up!!!!!");
                    System.out.println(line);
                } else {
                    System.out.println(line);
                    System.out.println("Sorry, but I am going to need you to provide a valid task number, please!");
                    System.out.println(line);
                }
                continue;
            }

            // Store taskInput into array, update boolean array & increment taskInput count
            userTasks[taskCount] = input;
            completed[taskCount] = false;
            taskCount++;

            System.out.println(line);
            System.out.println("added: " + input);
            System.out.println(line);
        }
        sc.close();
    }
}
