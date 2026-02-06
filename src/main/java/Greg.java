import java.util.Scanner;

/**
 * Runs the Greg chatbot program.
 * Supports adding Todo, Deadline, and Event tasks using inheritance + polymorphism.
 * Supports listing tasks and marking tasks as done.
 */
public class Greg {
    /**
     * Starts the Greg chatbot, prints a greeting and logo, then continuously reads user commands
     * until the user enters "bye".
     *
     * Supported commands:
     * list - lists all tasks currently stored.
     * mark N - marks task number N as completed.
     * todo DESCRIPTION - adds a Todo task.
     * deadline DESCRIPTION /by BY - adds a Deadline task.
     * event DESCRIPTION /from START /to END - adds an Event task.
     * any other input - treated as a Todo task.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
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

        // Store tasks as Task objects
        Task[] tasks = new Task[100];

        // Track completion
        boolean[] completed = new boolean[100];

        int taskCount = 0;

        while (true) {
            String input = sc.nextLine().trim();

            // bye command: Exits chatbot
            if (input.equals("bye")) {
                System.out.println(line);
                System.out.println(" Bye. Hope to see you again soon!");
                System.out.println(line);
                break;
            }

            // list command: Lists out Tasks
            if (input.equals("list")) {
                System.out.println(line);
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < taskCount; i++) {
                    String status = completed[i] ? "[X]" : "[ ]";
                    System.out.println((i + 1) + "." + tasks[i].toString().substring(0, 3) + status
                            + tasks[i].toString().substring(3));
                }
                System.out.println(line);
                continue;
            }

            // mark command: Mark a specified task as completed
            if (input.startsWith("mark ")) {
                int index = Integer.parseInt(input.substring(5).trim()) - 1;

                if (index >= 0 && index < taskCount) {
                    completed[index] = true;

                    System.out.println(line);
                    System.out.println("GOOD JOB!!! I have marked this task as completed for you:");
                    System.out.println("  " + tasks[index].toString().substring(0, 3) + "[X]"
                            + tasks[index].toString().substring(3));
                    System.out.println("Keep it up!!!!!");
                    System.out.println(line);
                } else {
                    System.out.println(line);
                    System.out.println("Sorry, but I am going to need you to provide a valid task number, please!");
                    System.out.println(line);
                }
                continue;
            }

            // Add toDo Task with DESCRIPTION
            if (input.startsWith("todo ")) {
                String description = input.substring(5).trim();
                tasks[taskCount] = new Todo(description);
                completed[taskCount] = false;

                System.out.println(line);
                System.out.println("Got it. I've added this task:");
                System.out.println("  [T][ ] " + description);
                taskCount++;
                System.out.println("Now you have " + taskCount + " tasks in the list.");
                System.out.println(line);
                continue;
            }

            // Add Deadline Task with DESCRIPTION and deadline BY
            if (input.startsWith("deadline ")) {
                String rest = input.substring(9).trim();
                String[] parts = rest.split(" /by ", 2);

                String description = parts[0].trim();
                String by = parts.length < 2 ? "" : parts[1].trim();

                tasks[taskCount] = new Deadline(description, by);
                completed[taskCount] = false;

                System.out.println(line);
                System.out.println("Got it. I've added this task:");
                System.out.println("  [D][ ] " + description + " (by: " + by + ")");
                taskCount++;
                System.out.println("Now you have " + taskCount + " tasks in the list.");
                System.out.println(line);
                continue;
            }

            // Add Event Task with DESCRIPTION, from: START, by: END
            if (input.startsWith("event ")) {
                String rest = input.substring(6).trim();

                String[] firstSplit = rest.split(" /from ", 2);
                String description = firstSplit[0].trim();

                String from = "";
                String to = "";

                if (firstSplit.length == 2) {
                    String[] secondSplit = firstSplit[1].split(" /to ", 2);
                    from = secondSplit[0].trim();
                    if (secondSplit.length == 2) {
                        to = secondSplit[1].trim();
                    }
                }

                tasks[taskCount] = new Event(description, from, to);
                completed[taskCount] = false;

                System.out.println(line);
                System.out.println("Got it. I've added this task:");
                System.out.println("  [E][ ] " + description + " (from: " + from + " to: " + to + ")");
                taskCount++;
                System.out.println("Now you have " + taskCount + " tasks in the list.");
                System.out.println(line);
                continue;
            }

            // If user types random text, treat it as Todo
            tasks[taskCount] = new Todo(input);
            completed[taskCount] = false;

            System.out.println(line);
            System.out.println("Got it. I've added this task:");
            System.out.println("  [T][ ] " + input);
            taskCount++;
            System.out.println("Now you have " + taskCount + " tasks in the list.");
            System.out.println(line);
        }

        sc.close();
    }
}
