package greg.task;

/**
 * Represents a task with a specific deadline.
 * Extends the Task class to include a date or time by which the task must be completed.
 */
public class Deadline extends Task {

    /** The date or time by which the task must be completed. */
    protected String by;

    /**
     * Constructs a Deadline task with the specified description and deadline time.
     *
     * @param description The description of the task.
     * @param by The deadline by which the task must be completed.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns a string representation of the Deadline task.
     * Includes the task type identifier [D], the status icon, description, and deadline.
     *
     * @return A string representing the Deadline task for display in the UI.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    /**
     * Returns a string formatted for saving the Deadline task to a data file.
     * The format used is "D | status | description | by".
     *
     * @return A pipe-separated string representing the Deadline task.
     */
    @Override
    public String toFileFormat() {
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + by;
    }
}