/**
 * The Task class represents a general task with a description.
 * It is also a base class for similar Classes to inherit from such as Deadline, Todo & event.
 */
public class Task {

    // The description of the task.
    // This is a protected field, allowing subclasses to access it.
    protected String description;

    /**
     * Constructor for the Task class.
     * Initialises a task with a specific description.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
    }

    /**
     * Returns a string representation of the task.
     * By default, it returns the description of the task.
     *
     * @return A string representation of the task.
     */
    @Override
    public String toString() {
        return description; // Returns the description of the task as a string.
    }
}
