package greg.tasklist;

import java.util.ArrayList;
import greg.task.Task;

/**
 * Encapsulates the list of tasks and provides operations to modify it.
 * This class serves as a wrapper around an ArrayList of Task objects to manage
 * task operations within the Greg chatbot.
 */
public class TaskList {
    /** The internal list used to store Task objects. */
    private ArrayList<Task> tasks;

    /**
     * Constructs a TaskList with an existing list of tasks.
     *
     * @param tasks An ArrayList of Task objects to initialize the list.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Adds a task to the list.
     *
     * @param task The Task object to be added.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Removes a task from the list at the specified index.
     *
     * @param index The position of the task to be removed.
     * @return The Task object that was removed.
     */
    public Task deleteTask(int index) {
        return tasks.remove(index);
    }

    /**
     * Returns the internal list of all tasks.
     * This is primarily used by the Storage class for saving data.
     *
     * @return The ArrayList containing all Task objects.
     */
    public ArrayList<Task> getAllTasks() {
        return tasks;
    }

    /**
     * Returns the number of tasks currently in the list.
     *
     * @return The size of the task list.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Retrieves a task from the list at the specified index.
     *
     * @param index The position of the task to retrieve.
     * @return The Task object at the given index.
     */
    public Task get(int index) {
        return tasks.get(index);
    }

    /**
     * Checks if the task list is empty.
     *
     * @return true if there are no tasks, false otherwise.
     */
    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    /**
     * Searches for tasks containing the keyword in their description.
     * The search is case-insensitive.
     *
     * @param keyword The search term.
     * @return A new TaskList containing only the filtered results.
     */
    public TaskList findTasks(String keyword) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        String lowerKeyword = keyword.toLowerCase();

        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            if (task.getDescription().toLowerCase().contains(lowerKeyword)) {
                matchingTasks.add(task);
            }
        }
        return new TaskList(matchingTasks);
    }
}