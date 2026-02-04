/**
 * The Deadline class represents a task with a specific deadline.
 * It extends the Task class and adds a "by" property to indicate when the task must be completed.
 */
public class Deadline extends Task {

    // The "by" field stores the deadline's date/time by which the task must be completed.
    protected String by;

    /**
     * Constructor for the Deadline class.
     * Initialises the task with the description and deadline.
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
     * Overrides the toString() method in the Task class to include the deadline.
     *
     * @return A string representing the Deadline task, including the description and deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";  // Returns the task with the "[D]" tag and the deadline.
    }
}
