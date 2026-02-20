package greg;

import java.util.Scanner;

import greg.exception.GregException;
import greg.task.Deadline;
import greg.task.Event;
import greg.task.Task;
import greg.task.Todo;

/**
 * Runs the greg.Greg chatbot program.
 * Supports adding greg.task.Todo, greg.task.Deadline, and greg.task.Event tasks using inheritance + polymorphism.
 * Supports listing tasks and marking tasks as done.
 */
public class Greg {
    /**
     * Starts the greg.Greg chatbot, prints a greeting and logo, then continuously reads user commands
     * until the user enters "bye".
     *
     * Supported commands:
     * list - lists all tasks currently stored.
     * mark N - marks task number N as completed.
     * todo DESCRIPTION - adds a greg.task.Todo task.
     * deadline DESCRIPTION /by BY - adds a greg.task.Deadline task.
     * event DESCRIPTION /from START /to END - adds an greg.task.Event task.
     * * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        String chatbotName = "greg.Greg";
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
        System.out.println(" I am your personal greg.task.Task Tracking Assistant!");
        System.out.println(" How may I be of service to you today?");
        System.out.println(line);

        Scanner sc = new Scanner(System.in);

        // Store tasks as greg.task.Task objects
        Task[] tasks = new Task[100];

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

            try {
                // list command: Lists out Tasks
                if (input.equals("list")) {
                    System.out.println(line);
                    if (taskCount == 0) {
                        System.out.println("Your list is currently empty!");
                    } else {
                        System.out.println("Here are the tasks in your list:");
                        for (int i = 0; i < taskCount; i++) {
                            System.out.println((i + 1) + ". " + tasks[i]);
                        }
                    }
                    System.out.println(line);

                    // mark command: Mark a specified task as completed
                } else if (input.startsWith("mark")) {
                    if (input.length() <= 5) {
                        throw new GregException("I need a task number to mark! Try 'mark 1'.");
                    }

                    try {
                        int index = Integer.parseInt(input.substring(5).trim()) - 1;

                        if (index >= 0 && index < taskCount) {
                            tasks[index].setDone(true);

                            System.out.println(line);
                            System.out.println("GOOD JOB!!! I have marked this task as completed for you:");
                            System.out.println("  " + tasks[index]);
                            System.out.println("Keep it up!!!!!");
                            System.out.println(line);
                        } else {
                            throw new GregException("I can't mark task #" + (index + 1) + ". You only have " + taskCount + " tasks!");
                        }
                    } catch (NumberFormatException e) {
                        throw new GregException("That's not a valid number! Please use 'mark [number]'.");
                    }

                    // Add toDo greg.task.Task with DESCRIPTION
                } else if (input.startsWith("todo")) {
                    if (input.length() <= 5) {
                        throw new GregException("The description of a todo cannot be empty. What are we planning?");
                    }
                    String description = input.substring(5).trim();
                    tasks[taskCount] = new Todo(description);
                    taskCount++;
                    printTaskAddedConfirmation(tasks[taskCount - 1], taskCount, line);

                    // Add greg.task.Deadline greg.task.Task with DESCRIPTION and deadline BY
                } else if (input.startsWith("deadline")) {
                    if (input.length() <= 9) {
                        throw new GregException("A deadline needs a description and time. Try: deadline fix bug /by tonight");
                    }
                    String rest = input.substring(9).trim();
                    if (!rest.contains(" /by ")) {
                        throw new GregException("I need a '/by' to know when the deadline is!");
                    }
                    String[] parts = rest.split(" /by ", 2);

                    tasks[taskCount] = new Deadline(parts[0].trim(), parts[1].trim());
                    taskCount++;
                    printTaskAddedConfirmation(tasks[taskCount - 1], taskCount, line);

                    // Add greg.task.Event greg.task.Task with DESCRIPTION, from: START, by: END
                } else if (input.startsWith("event")) {
                    if (input.length() <= 6) {
                        throw new GregException("An event needs a name, /from, and /to. Try: event party /from 6pm /to 10pm");
                    }
                    String rest = input.substring(6).trim();
                    if (!rest.contains(" /from ") || !rest.contains(" /to ")) {
                        throw new GregException("Events must follow the format: event [name] /from [start] /to [end]");
                    }

                    String[] firstSplit = rest.split(" /from ", 2);
                    String description = firstSplit[0].trim();
                    String[] secondSplit = firstSplit[1].split(" /to ", 2);

                    tasks[taskCount] = new Event(description, secondSplit[0].trim(), secondSplit[1].trim());
                    taskCount++;
                    printTaskAddedConfirmation(tasks[taskCount - 1], taskCount, line);

                } else {
                    // Replaces the default greg.task.Todo behavior with an Exception for unknown commands
                    throw new GregException("I'm sorry, but I don't know what '" + input + "' means :-( Try using todo, deadline, or event!");
                }

            } catch (GregException e) {
                /**
                 * Catches chatbot-specific errors
                 */
                System.out.println(line);
                System.out.println(" [ERROR]  " + e.getMessage());
                System.out.println(line);
            }
        }

        sc.close();
    }

    /**
     * Prints a confirmation message after a task has been successfully added to the list.
     * * @param task The task object that was just added.
     * @param total The total number of tasks currently in the list.
     * @param line The decorative line string for formatting.
     */
    private static void printTaskAddedConfirmation(Task task, int total, String line) {
        System.out.println(line);
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + total + " tasks in the list.");
        System.out.println(line);
    }
}