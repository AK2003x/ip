/**
 * The Todo class represents a task without any date or time attached.
 */
public class Todo extends Task {

    /**
     * Constructor for the Todo Class.
     * Initialises the task with the description.
     *
     * @param description The description of the todo task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns a string representation of the Todo task.
     * Overrides the Task's toString() method to add the [T] tag.
     *
     * @return A string representing the Todo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
