package greg.task;

/**
 * Represents a task that occurs within a specific time frame.
 * Extends the Task class by adding start and end times to the task description.
 */
public class Event extends Task {

    /** The start time or date of the event. */
    protected String start;

    /** The end time or date of the event. */
    protected String end;

    /**
     * Constructs an Event task with the specified description, start time, and end time.
     *
     * @param description The description of the event.
     * @param start The start time of the event.
     * @param end The end time of the event.
     */
    public Event(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    /**
     * Returns a string representation of the Event task.
     * Includes the task type identifier [E], the status icon, description, and the time range.
     *
     * @return A string representing the event task for display in the UI.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + start + " to: " + end + ")";
    }

    /**
     * Returns a string formatted for saving the Event task to a data file.
     * The format used is "E | status | description | start | end".
     *
     * @return A pipe-separated string representing the Event task.
     */
    @Override
    public String toFileFormat() {
        return "E | " + (isDone ? "1" : "0") + " | " + description + " | " + start + " | " + end;
    }
}