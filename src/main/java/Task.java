/**
 * The Task class represents a general task with a description.
 * It is also a base class for similar Classes to inherit from such as Deadline, Todo & event.
 */
public class Task {

    // The description of the task.
    protected String description;

    // Tracks whether the task is completed.
    protected boolean isDone;

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
     * Returns whether this task is completed.
     *
     * @return True if task is done, false otherwise.
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Sets the completion status of this task.
     *
     * @param done True if task should be marked done, false otherwise.
     */
    public void setDone(boolean done) {
        isDone = done;
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
