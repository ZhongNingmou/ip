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

    public Storage(String filepath) {
        this.filepath = filepath;
        f = new File(filepath);
    }

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

    public static void readFromFile(TaskList tasks) {
        try {
            File f = new File(filepath);
            Scanner s = new Scanner(f);
            Task task;
            while (s.hasNext()) {
                String[] descriptions = s.nextLine().split("\\|");
                if (descriptions[0].equals("T")) {
                    task = new ToDo(descriptions[2]);
                } else if (descriptions[0].equals("D")) {
                    task = new Deadline(descriptions[2], descriptions[3]);
                } else if (descriptions[0].equals("E")) {
                    task = new Event(descriptions[2], descriptions[3]);
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
