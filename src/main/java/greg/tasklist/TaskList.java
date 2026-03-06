package greg.tasklist;

import java.util.ArrayList;
import greg.task.Task;

/**
 * Encapsulates the list of tasks and providing operations to modify it.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public Task deleteTask(int index) {
        return tasks.remove(index);
    }

    public ArrayList<Task> getAllTasks() {
        return tasks;
    }

    public int size() {
        return tasks.size();
    }

    public Task get(int index) {
        return tasks.get(index);
    }

    /**
     * Checks if the task list is empty.
     * @return true if there are no tasks, false otherwise.
     */
    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    /**
     * Searches for tasks containing the keyword in their description.
     *
     * @param keyword The search term.
     * @return A new TaskList containing the filtered results.
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