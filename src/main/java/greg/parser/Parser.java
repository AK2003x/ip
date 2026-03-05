package greg.parser;

import java.util.ArrayList;
import greg.exception.GregException;
import greg.task.Deadline;
import greg.task.Event;
import greg.task.Task;
import greg.task.Todo;

/**
 * Handles the interpretation and execution of user commands for the Greg chatbot.
 * Uses ArrayList for dynamic task management.
 */
public class Parser {

    /**
     * Parses the user input and executes the corresponding logic.
     *
     * @param input The raw input string from the user.
     * @param tasks The ArrayList containing the tasks.
     * @param line  The decorative line for formatting output.
     * @throws GregException If input is invalid or formatted incorrectly.
     */
    public static void parseAndExecute(String input, ArrayList<Task> tasks, String line) throws GregException {
        if (input.equals("list")) {
            handleList(tasks, line);
        } else if (input.startsWith("mark")) {
            handleMark(input, tasks, line);
        } else if (input.startsWith("todo")) {
            handleTodo(input, tasks, line);
        } else if (input.startsWith("deadline")) {
            handleDeadline(input, tasks, line);
        } else if (input.startsWith("event")) {
            handleEvent(input, tasks, line);
        } else if (input.startsWith("delete")) {
            handleDelete(input, tasks, line);
        } else {
            throw new GregException("I'm sorry, but I don't know what '" + input + "' means :-( Try using todo, deadline, or event!");
        }
    }

    private static void handleList(ArrayList<Task> tasks, String line) {
        System.out.println(line);
        if (tasks.isEmpty()) {
            System.out.println("Your list is currently empty!");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + ". " + tasks.get(i));
            }
        }
        System.out.println(line);
    }

    private static void handleMark(String input, ArrayList<Task> tasks, String line) throws GregException {
        if (input.length() <= 5) {
            throw new GregException("I need a task number to mark! Try 'mark 1'.");
        }
        try {
            int index = Integer.parseInt(input.substring(5).trim()) - 1;

            if (index >= 0 && index < tasks.size()) {
                tasks.get(index).setDone(true);
                System.out.println(line);
                System.out.println("GOOD JOB!!! I have marked this task as completed for you:");
                System.out.println("  " + tasks.get(index));
                System.out.println(line);
            } else {
                throw new GregException("I can't mark task #" + (index + 1) + ". You only have " + tasks.size() + " tasks!");
            }
        } catch (NumberFormatException e) {
            throw new GregException("That's not a valid number! Please use 'mark [number]'.");
        }
    }

    private static void handleTodo(String input, ArrayList<Task> tasks, String line) throws GregException {
        if (input.length() <= 5) {
            throw new GregException("The description of a todo cannot be empty.");
        }
        String description = input.substring(5).trim();
        Task newTodo = new Todo(description);
        tasks.add(newTodo);
        printTaskAddedConfirmation(newTodo, tasks.size(), line);
    }

    private static void handleDeadline(String input, ArrayList<Task> tasks, String line) throws GregException {
        if (input.length() <= 9) {
            throw new GregException("A deadline needs a description and time.");
        }
        String rest = input.substring(9).trim();
        if (!rest.contains(" /by ")) {
            throw new GregException("I need a '/by' to know when the deadline is!");
        }
        String[] parts = rest.split(" /by ", 2);
        Task newDeadline = new Deadline(parts[0].trim(), parts[1].trim());
        tasks.add(newDeadline);
        printTaskAddedConfirmation(newDeadline, tasks.size(), line);
    }

    private static void handleDelete(String input, ArrayList<Task> tasks, String line) throws GregException {
        if (input.length() <= 7) {
            throw new GregException("I need a task number to delete! Try 'delete 1'.");
        }

        try {
            // Parse the number and convert to 0-based index
            int index = Integer.parseInt(input.substring(7).trim()) - 1;

            // Check if index is within the current list size
            if (index >= 0 && index < tasks.size()) {
                Task removedTask = tasks.remove(index); // Removes and returns the task

                System.out.println(line);
                System.out.println(" Gotcha. I've removed this task:");
                System.out.println("   " + removedTask);
                System.out.println(" Congrats!!! Now you only have " + tasks.size() + " tasks in the list.");
                System.out.println(line);
            } else {
                throw new GregException("I can't delete task #" + (index + 1) + ". You only have " + tasks.size() + " tasks!");
            }
        } catch (NumberFormatException e) {
            throw new GregException("That's not a valid number! Please use 'delete [number]'.");
        }
    }

    private static void handleEvent(String input, ArrayList<Task> tasks, String line) throws GregException {
        if (input.length() <= 6) {
            throw new GregException("An event needs a name, /from, and /to.");
        }
        String rest = input.substring(6).trim();
        if (!rest.contains(" /from ") || !rest.contains(" /to ")) {
            throw new GregException("Events must follow the format: event [name] /from [start] /to [end]");
        }
        String[] firstSplit = rest.split(" /from ", 2);
        String[] secondSplit = firstSplit[1].split(" /to ", 2);

        Task newEvent = new Event(firstSplit[0].trim(), secondSplit[0].trim(), secondSplit[1].trim());
        tasks.add(newEvent);
        printTaskAddedConfirmation(newEvent, tasks.size(), line);
    }

    private static void printTaskAddedConfirmation(Task task, int total, String line) {
        System.out.println(line);
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + total + " tasks in the list.");
        System.out.println(line);
    }
}