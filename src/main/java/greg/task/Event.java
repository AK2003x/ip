package greg.task;

/**
 * The greg.task.Event class represents a task that starts at a specific time and ends at a specific time.
 * It extends the greg.task.Task class by adding a start ("start") and end ("end") time.
 */
public class Event extends Task {

    // The start time of the event.
    protected String start;

    // The end time of the event.
    protected String end;

    /**
     * Constructs an greg.task.Event task with the given description, start time, and end time.
     *
     * @param description The description of the event task.
     * @param start The start time of the event.
     * @param end The end time of the event.
     */
    public Event(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    /**
     * Returns a string representation of the greg.task.Event task.
     * Overrides greg.task.Task's toString() method to add the [E] tag and event time range.
     *
     * @return A string representing the event task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + start + " to: " + end + ")";
    }

    @Override
    public String toFileFormat() {
        return "E | " + (isDone ? "1" : "0") + " | " + description + " | " + start + " | " + end;
    }
}
