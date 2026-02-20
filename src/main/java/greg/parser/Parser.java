package greg.parser;

import greg.exception.GregException;
import greg.task.Deadline;
import greg.task.Event;
import greg.task.Task;
import greg.task.Todo;

public class Parser {

    /**
     * Parses the user input and executes the corresponding command.
     * * @param input The raw string from the user.
     * @param tasks The array where tasks are stored.
     * @param taskCount The current number of tasks (this will need to be updated in Greg.java).
     * @return The updated taskCount.
     * @throws GregException If the command is invalid or parameters are missing.
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

        return taskCount; // Return the new count back to Greg.java
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
            if (index < 0 || index >= taskCount) {
                throw new GregException("I can't mark task #" + (index + 1) + ". You only have " + taskCount + " tasks!");
            }
            tasks[index].setDone(true);
            System.out.println(line);
            System.out.println("GOOD JOB!!! I have marked this task as completed for you:");
            System.out.println("  " + tasks[index]);
            System.out.println(line);
        } catch (NumberFormatException e) {
            throw new GregException("That's not a valid number! Please use 'mark [number]'.");
        }
    }
}