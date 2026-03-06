package greg.task;

/**
 * The greg.task.Todo class represents a task without any date or time attached.
 */
public class Todo extends Task {

    /**
     * Constructor for the greg.task.Todo Class.
     * Initialises the task with the description.
     *
     * @param description The description of the todo task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns a string representation of the greg.task.Todo task.
     * Overrides the greg.task.Task's toString() method to add the [T] tag.
     *
     * @return A string representing the greg.task.Todo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toFileFormat() {
        return "T | " + (isDone ? "1" : "0") + " | " + description;
    }
}
