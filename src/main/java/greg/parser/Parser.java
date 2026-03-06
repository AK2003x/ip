package greg.parser;

import greg.ui.Ui;
import greg.exception.GregException;
import greg.storage.Storage;
import greg.task.Deadline;
import greg.task.Event;
import greg.task.Task;
import greg.task.Todo;
import greg.tasklist.TaskList;

/**
 * Handles the interpretation and execution of user commands for the Greg chatbot.
 * It coordinates interactions between the TaskList, Ui, and Storage components.
 */
public class Parser {

    /**
     * Parses the user input and executes the corresponding logic.
     *
     * @param input   The raw input string from the user.
     * @param tasks   The TaskList containing the tasks.
     * @param ui      The Ui component for user interaction.
     * @param storage The Storage component for saving tasks.
     * @throws GregException If input is invalid or formatted incorrectly.
     */
    public static void parseAndExecute(String input, TaskList tasks, Ui ui, Storage storage) throws GregException {
        if (input.equals("list")) {
            handleList(tasks, ui);
        } else if (input.startsWith("mark")) {
            handleMark(input, tasks, ui, storage);
        } else if (input.startsWith("todo")) {
            handleTodo(input, tasks, ui, storage);
        } else if (input.startsWith("deadline")) {
            handleDeadline(input, tasks, ui, storage);
        } else if (input.startsWith("event")) {
            handleEvent(input, tasks, ui, storage);
        } else if (input.startsWith("find")) {
            handleFind(input, tasks, ui);
        } else if (input.startsWith("delete")) {
            handleDelete(input, tasks, ui, storage);
        } else {
            throw new GregException("I'm sorry, but I don't know what '" + input + "' means :-( Try using todo, deadline, or event!");
        }
    }

    /**
     * Displays all tasks currently stored in the task list.
     *
     * @param tasks The list of tasks to be displayed.
     * @param ui    The Ui component for formatting output.
     */
    private static void handleList(TaskList tasks, Ui ui) {
        ui.showLine();
        if (tasks.isEmpty()) {
            ui.showMessage("Your list is currently empty!");
        } else {
            ui.showMessage("Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                ui.showMessage((i + 1) + ". " + tasks.get(i));
            }
        }
        ui.showLine();
    }

    /**
     * Marks a specific task as completed based on its index in the list.
     *
     * @param input   The raw input string containing the index.
     * @param tasks   The list of tasks.
     * @param ui      The Ui component for formatting output.
     * @param storage The Storage component to save the updated state.
     * @throws GregException If the index is missing, invalid, or out of bounds.
     */
    private static void handleMark(String input, TaskList tasks, Ui ui, Storage storage) throws GregException {
        if (input.length() <= 5) {
            throw new GregException("I need a task number to mark! Try 'mark 1'.");
        }
        try {
            int index = Integer.parseInt(input.substring(5).trim()) - 1;

            if (index >= 0 && index < tasks.size()) {
                tasks.get(index).setDone(true);
                ui.showLine();
                ui.showMessage("GOOD JOB!!! I have marked this task as completed for you:");
                ui.showMessage("  " + tasks.get(index));
                ui.showLine();
                storage.save(tasks);
            } else {
                throw new GregException("I can't mark task #" + (index + 1) + ". You only have " + tasks.size() + " tasks!");
            }
        } catch (NumberFormatException e) {
            throw new GregException("That's not a valid number! Please use 'mark [number]'.");
        }
    }

    /**
     * Creates and adds a new Todo task to the list.
     *
     * @param input   The raw input string containing the todo description.
     * @param tasks   The list of tasks.
     * @param ui      The Ui component for formatting output.
     * @param storage The Storage component to save the new task.
     * @throws GregException If the description is empty.
     */
    private static void handleTodo(String input, TaskList tasks, Ui ui, Storage storage) throws GregException {
        if (input.length() <= 5) {
            throw new GregException("The description of a todo cannot be empty.");
        }
        String description = input.substring(5).trim();
        Task newTodo = new Todo(description);
        tasks.addTask(newTodo);
        printTaskAddedConfirmation(newTodo, tasks.size(), ui);
        storage.save(tasks);
    }

    /**
     * Creates and adds a new Deadline task to the list.
     *
     * @param input   The raw input string containing description and deadline time.
     * @param tasks   The list of tasks.
     * @param ui      The Ui component for formatting output.
     * @param storage The Storage component to save the new task.
     * @throws GregException If formatting is incorrect or the '/by' keyword is missing.
     */
    private static void handleDeadline(String input, TaskList tasks, Ui ui, Storage storage) throws GregException {
        if (input.length() <= 9) {
            throw new GregException("A deadline needs a description and time.");
        }
        String rest = input.substring(9).trim();
        if (!rest.contains(" /by ")) {
            throw new GregException("I need a '/by' to know when the deadline is!");
        }
        String[] parts = rest.split(" /by ", 2);
        Task newDeadline = new Deadline(parts[0].trim(), parts[1].trim());
        tasks.addTask(newDeadline);
        printTaskAddedConfirmation(newDeadline, tasks.size(), ui);
        storage.save(tasks);
    }

    /**
     * Removes a task from the list based on its index.
     *
     * @param input   The raw input string containing the index.
     * @param tasks   The list of tasks.
     * @param ui      The Ui component for formatting output.
     * @param storage The Storage component to save the updated list.
     * @throws GregException If the index is missing, invalid, or out of bounds.
     */
    private static void handleDelete(String input, TaskList tasks, Ui ui, Storage storage) throws GregException {
        if (input.length() <= 7) {
            throw new GregException("I need a task number to delete! Try 'delete 1'.");
        }
        try {
            int index = Integer.parseInt(input.substring(7).trim()) - 1;

            if (index >= 0 && index < tasks.size()) {
                Task removedTask = tasks.deleteTask(index);
                ui.showLine();
                ui.showMessage(" Gotcha. I've removed this task:");
                ui.showMessage("   " + removedTask);
                ui.showMessage(" Congrats!!! Now you only have " + tasks.size() + " tasks in the list.");
                ui.showLine();
                storage.save(tasks);
            } else {
                throw new GregException("I can't delete task #" + (index + 1) + ". You only have " + tasks.size() + " tasks!");
            }
        } catch (NumberFormatException e) {
            throw new GregException("That's not a valid number! Please use 'delete [number]'.");
        }
    }

    /**
     * Creates and adds a new Event task to the list.
     *
     * @param input   The raw input string containing description, start time, and end time.
     * @param tasks   The list of tasks.
     * @param ui      The Ui component for formatting output.
     * @param storage The Storage component to save the new task.
     * @throws GregException If formatting is incorrect or '/from'/'/to' keywords are missing.
     */
    private static void handleEvent(String input, TaskList tasks, Ui ui, Storage storage) throws GregException {
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
        tasks.addTask(newEvent);
        printTaskAddedConfirmation(newEvent, tasks.size(), ui);
        storage.save(tasks);
    }

    /**
     * Handles the find command by searching for tasks that match the user's keyword.
     *
     * @param input   The raw input string.
     * @param tasks   The TaskList to search.
     * @param ui      The Ui component for output.
     * @throws GregException If the keyword is missing.
     */
    private static void handleFind(String input, TaskList tasks, Ui ui) throws GregException {
        if (input.length() <= 5) {
            throw new GregException("I need a keyword to search for what you're looking for! Try 'find [task] i.e. find Do Homework'.");
        }

        String keyword = input.substring(5).trim();
        TaskList results = tasks.findTasks(keyword);

        ui.showLine();
        if (results.isEmpty()) {
            ui.showMessage(" Im sorry but I couldn't find any tasks matching: " + keyword);
        } else {
            ui.showMessage(" Here are the matching tasks in your list:");
            for (int i = 0; i < results.size(); i++) {
                ui.showMessage(" " + (i + 1) + "." + results.get(i));
            }
        }
        ui.showLine();
    }

    /**
     * Prints a confirmation message to the console after a task is successfully added.
     *
     * @param task  The task that was added.
     * @param total The total number of tasks now in the list.
     * @param ui    The Ui component for formatting output.
     */
    private static void printTaskAddedConfirmation(Task task, int total, Ui ui) {
        ui.showLine();
        ui.showMessage("Got it. I've added this task:");
        ui.showMessage("  " + task);
        ui.showMessage("Now you have " + total + " tasks in the list.");
        ui.showLine();
    }
}