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
 * Handles loading and saving tasks to a plaintext file.
 */
public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the hard disk. Handles missing files/directories.
     * @return List of tasks; empty if file doesn't exist or is empty.
     */
    public ArrayList<Task> load() {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(filePath);

        if (!file.exists()) {
            return tasks; // Simply return empty list if file isn't there yet
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
        return tasks;
    }

    /**
     * Saves the current task list to the hard disk.
     * @param tasks The current task list.
     */
    public void save(TaskList tasks) {
        File file = new File(filePath);

        // Ensure the directory (e.g., /data/) exists
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
     * Logic to convert a file line back into a Task object.
     * Stretch Goal: Validates format and ignores corrupted data.
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
            // If the line is corrupted or missing parts, skip it
            return null;
        }
    }
}