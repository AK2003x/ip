package greg.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import greg.task.Task;
import greg.task.Todo;
import greg.task.Deadline;
import greg.task.Event;
import greg.tasklist.TaskList;

/**
 * Handles loading and saving tasks to a plaintext file on the hard disk.
 */
public class Storage {
    /** The path to the file where tasks are stored. */
    private final String filePath;

    /**
     * Constructs a Storage object with a specified file path.
     *
     * @param filePath The relative or absolute path to the data file.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the hard disk. Handles missing files or directories by returning an empty list.
     *
     * @return A TaskList containing tasks loaded from the file.
     */
    public TaskList load() {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(filePath);

        if (!file.exists()) {
            return new TaskList(tasks);
        }

        try (Scanner sc = new Scanner(file)) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                Task task = parseLine(line);
                if (task != null) {
                    tasks.add(task);
                }
            }
        } catch (IOException e) {
            System.out.println(" [ERROR] Could not read from file: " + e.getMessage());
        }
        return new TaskList(tasks);
    }

    /**
     * Saves the current task list to the hard disk in a formatted plaintext style.
     * Creates the parent directory if it does not exist.
     *
     * @param tasks The TaskList object containing tasks to be saved.
     */
    public void save(TaskList tasks) {
        File file = new File(filePath);

        File parentDir = file.getParentFile();
        if (parentDir != null && !parentDir.exists()) {
            parentDir.mkdirs();
        }

        try (FileWriter fw = new FileWriter(file)) {
            for (Task t : tasks.getAllTasks()) {
                fw.write(t.toFileFormat() + System.lineSeparator());
            }
        } catch (IOException e) {
            System.out.println(" [ERROR] Could not save to file: " + e.getMessage());
        }
    }

    /**
     * Parses a single line of text from the data file into a Task object.
     *
     * @param line The raw string line from the data file.
     * @return The corresponding Task object, or null if the line is corrupted.
     */
    private Task parseLine(String line) {
        try {
            String[] p = line.split(" \\| ");
            String type = p[0];
            boolean isDone = p[1].equals("1");
            String desc = p[2];

            Task task = null;
            switch (type) {
                case "T":
                    task = new Todo(desc);
                    break;
                case "D":
                    task = new Deadline(desc, p[3]);
                    break;
                case "E":
                    task = new Event(desc, p[3], p[4]);
                    break;
            }

            if (task != null && isDone) {
                task.setDone(true);
            }
            return task;
        } catch (Exception e) {
            return null;
        }
    }
}