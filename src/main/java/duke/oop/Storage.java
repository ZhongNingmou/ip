package duke.oop;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private static String filepath;
    private static File f;
    static int TASK_TYPE_INDEX = 0;
    static int TASK_DESCRIPTION_INDEX = 3;
    static int TASK_STATUS_INDEX = 2;

    /**
     * constructor of storage
     * @param filepath get the filepath
     */
    public Storage(String filepath) {
        this.filepath = filepath;
        f = new File(filepath);
    }

    /**
     * write tasks data to the txt file according to the file path
     * @param tasks get tasks description
     */
    public static void writeToFile(TaskList tasks) {
        try {
            File f = new File(filepath);
            ArrayList<Task> tasksList = tasks.getTasksList();
            FileWriter fw = new FileWriter(filepath);
            for (Task task : tasksList) {
                if (task != null)
                    fw.write(task.writeToFile());
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Something went wrong with IO stream.\n");
        }
    }

    /**
     * read tasks data from the txt file according to the file path
     * @param tasks get tasks description
     */
    public static void readFromFile(TaskList tasks) {
        try {
            File f = new File(filepath);
            Scanner s = new Scanner(f);
            Task task;
            while (s.hasNext()) {
                String[] descriptions = s.nextLine().split("\\|");
                if (descriptions[TASK_TYPE_INDEX].equals("T")) {
                    task = new ToDo(descriptions[TASK_STATUS_INDEX]);
                } else if (descriptions[TASK_TYPE_INDEX].equals("D")) {
                    task = new Deadline(descriptions[TASK_STATUS_INDEX], descriptions[TASK_DESCRIPTION_INDEX]);
                } else if (descriptions[TASK_TYPE_INDEX].equals("E")) {
                    task = new Event(descriptions[TASK_STATUS_INDEX], descriptions[TASK_DESCRIPTION_INDEX]);
                } else {
                    tasks.add(null);
                    break;
                }

                if (descriptions[1].equals("1")) {
                    task.setIsDone(true);
                }
                tasks.add(task);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }
}
