package greg.task;

/**
 * Represents a basic task without any specific date or time constraints.
 * Extends the Task class to provide a specialized representation for simple to-do items.
 */
public class Todo extends Task {

    /**
     * Constructs a Todo task with the specified description.
     *
     * @param description The description of the todo task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns a string representation of the Todo task.
     * Includes the task type identifier [T], the status icon, and the description.
     *
     * @return A string representing the todo task for display in the UI.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns a string formatted for saving the Todo task to a data file.
     * The format used is "T | status | description".
     *
     * @return A pipe-separated string representing the Todo task for file storage.
     */
    @Override
    public String toFileFormat() {
        return "T | " + (isDone ? "1" : "0") + " | " + description;
    }
}