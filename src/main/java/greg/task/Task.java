package greg.task;

/**
 * Represents a general task with a description and a completion status.
 * Acts as the base class for specialized task types such as Todo, Deadline, and Event.
 */
public class Task {

    /** The description of the task. */
    protected String description;

    /** Tracks whether the task has been marked as completed. */
    protected boolean isDone;

    /**
     * Constructs a Task with the specified description.
     * The task is initialized as not done by default.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the completion status of the task.
     *
     * @return true if the task is completed, false otherwise.
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Sets the completion status of the task.
     *
     * @param done true to mark the task as completed, false to mark it as incomplete.
     */
    public void setDone(boolean done) {
        isDone = done;
    }

    /**
     * Returns a string representation of the task, including its completion status icon.
     *
     * @return A string formatted as "[X] description" if done, or "[ ] description" otherwise.
     */
    @Override
    public String toString() {
        String status = isDone ? "[X]" : "[ ]";
        return status + " " + description;
    }

    /**
     * Retrieves the description of the task.
     *
     * @return The task description string.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns a string formatted for storage in a plaintext file.
     * This method is intended to be overridden by subclasses to include specialized fields.
     *
     * @return A pipe-separated string representing the task's state for file storage.
     */
    public String toFileFormat() {
        return "Task | " + (isDone ? "1" : "0") + " | " + description;
    }
}