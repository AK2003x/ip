package greg.parser;

import greg.exception.GregException;
import greg.task.Deadline;
import greg.task.Event;
import greg.task.Task;
import greg.task.Todo;

/**
 * Handles the interpretation and execution of user commands for the Greg chatbot.
 */
public class Parser {

    /**
     * Parses the user input and executes the corresponding logic.
     * @param input The raw input string from the user.
     * @param tasks The array containing the tasks.
     * @param taskCount The current number of tasks in the array.
     * @param line The decorative line for formatting output.
     * @return The updated taskCount after command execution.
     * @throws GregException If input is invalid or formatted incorrectly.
     */
    public static int parseAndExecute(String input, Task[] tasks, int taskCount, String line) throws GregException {
        if (input.equals("list")) {
            handleList(tasks, taskCount, line);
        } else if (input.startsWith("mark")) {
            handleMark(input, tasks, taskCount, line);
        } else if (input.startsWith("todo")) {
            taskCount = handleTodo(input, tasks, taskCount, line);
        } else if (input.startsWith("deadline")) {
            taskCount = handleDeadline(input, tasks, taskCount, line);
        } else if (input.startsWith("event")) {
            taskCount = handleEvent(input, tasks, taskCount, line);
        } else {
            throw new GregException("I'm sorry, but I don't know what '" + input + "' means :-( Try using todo, deadline, or event!");
        }
        return taskCount;
    }

    private static void handleList(Task[] tasks, int taskCount, String line) {
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
    }

    private static void handleMark(String input, Task[] tasks, int taskCount, String line) throws GregException {
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
    }

    private static int handleTodo(String input, Task[] tasks, int taskCount, String line) throws GregException {
        if (input.length() <= 5) {
            throw new GregException("The description of a todo cannot be empty. What are we planning?");
        }
        String description = input.substring(5).trim();
        tasks[taskCount] = new Todo(description);
        taskCount++;
        printTaskAddedConfirmation(tasks[taskCount - 1], taskCount, line);
        return taskCount;
    }

    private static int handleDeadline(String input, Task[] tasks, int taskCount, String line) throws GregException {
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
        return taskCount;
    }

    private static int handleEvent(String input, Task[] tasks, int taskCount, String line) throws GregException {
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
        return taskCount;
    }

    private static void printTaskAddedConfirmation(Task task, int total, String line) {
        System.out.println(line);
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + total + " tasks in the list.");
        System.out.println(line);
    }
}