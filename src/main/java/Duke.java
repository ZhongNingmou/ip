import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class Duke {
    public static ArrayList<Task> tasks = new ArrayList<>();
    public static final String FILE_PATH = "duke.txt";
    static String lineCutOff = "_______________________";
    static int TODO_TASK_INDEX = 5;
    static int DEADLINE_TASK_INDEX = 9;
    static int EVENT_TASK_INDEX = 6;
    static int DONE_TASK_INDEX = 5;
    static int DELETE_TASK_INDEX = 7;

    //save changes
    public static void writeToFile(String FILE_PATH){
        try{
            File f = new File(FILE_PATH);
            FileWriter fw = new FileWriter(FILE_PATH);
            for (Task task: tasks){
                if(task != null)
                fw.write(task.writeToFile());
            }
            fw.close();
        } catch (IOException e){
            System.out.println("Something went wrong with IO stream.\n");
        }
    }
    
    public static void readFromFile(String FILE_PATH){
        try{
            File f = new File(FILE_PATH);
            Scanner s = new Scanner(f);
            Task task;
            while(s.hasNext()){
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

                if (descriptions[1].equals("1")){
                    task.setIsDone(true);
                }
                tasks.add(task);
            }
        } catch (FileNotFoundException e){
            System.out.println("File not found");
        }
    }

    public static Task taskType(String task) {
        try {
            if (task.startsWith("todo")) {
                if (task.equals("todo")) {
                    throw new DukeException();
                }
                return new ToDo(task.substring(TODO_TASK_INDEX));
            } else if (task.startsWith("deadline")) {
                int indexBy = task.indexOf("/");
                if (task.equals("deadline")) {
                    throw new DukeException();
                } else if (indexBy== -1) {
                    throw new DukeException();
                }
                return new Deadline(task.substring(DEADLINE_TASK_INDEX, indexBy - 1), task.substring(indexBy + 3));
            } else if (task.startsWith("event")) {
                int indexAt = task.indexOf("/");
                if (task.equals("event")) {
                    throw new DukeException();
                } else if (indexAt == -1) {
                    throw new DukeException();
                }
                return new Event(task.substring(EVENT_TASK_INDEX, indexAt - 1), task.substring(indexAt + 3));
            } else {
                System.out.println(lineCutOff);
                System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                System.out.println(lineCutOff);
                return null;
            }
        } catch (DukeException e) {
            System.out.println(lineCutOff);
            System.out.println("☹ OOPS!!! The description of a " + task + " cannot be empty.");
            System.out.println(lineCutOff);
            return null;
        }
    }

    public static void printTask(Task task) {
        System.out.println(lineCutOff + "\n" + "Got it. I've added this task: ");
        System.out.println("  " + task.toString());
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        System.out.println(lineCutOff + "\n");
    }

    public static void printDone(String line) {
        try {
            if (line.equals("done")) {
                throw new DukeException();
            }
            int listIndex = Integer.parseInt(line.substring(DONE_TASK_INDEX)) - 1;
            if (listIndex >= tasks.size()) {
                throw new DukeException();
            } else if (tasks.get(listIndex) == null){
                throw new DukeException();
            }
            else {

                tasks.get(listIndex).setIsDone(true);
                System.out.println(lineCutOff);
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(tasks.get(listIndex).toString());
                System.out.println(lineCutOff);
            }
        } catch (DukeException e) {
            System.out.println(lineCutOff);
            System.out.println("☹ OOPS!!! There is no such task.");
            System.out.println(lineCutOff);
        }
    }

    public static void printDelete(String line) {
        try {
            if (line.equals("delete")) {
                throw new DukeException();
            }
            int listIndex = Integer.parseInt(line.substring(DELETE_TASK_INDEX)) - 1;
            if (listIndex >= tasks.size()) {
                throw new DukeException();
            } else if (tasks.get(listIndex) == null){
                throw new DukeException();
            }
            else {
                System.out.println(lineCutOff);
                System.out.println("Noted. I've removed this task: ");
                System.out.println(tasks.get(listIndex).toString());
                System.out.println(lineCutOff);
                tasks.remove(tasks.get(listIndex));
                System.out.println("Now you have " + tasks.size() + " items in the list.");
                System.out.println(lineCutOff);
            }
        } catch (DukeException e) {
            System.out.println(lineCutOff);
            System.out.println("☹ OOPS!!! There is no such task.");
            System.out.println(lineCutOff);
        }
    }

    public static void printList(ArrayList<Task> tasks) {
        System.out.println(lineCutOff);
        if (tasks.size() == 0) {
            tasks.add(null);
        }
        try {
            if (tasks.get(0) == null) {
                throw new DukeException();
            } else {
                System.out.println("Here are the tasks in your list:");
                int i = 1;
                for (Task task: tasks) {
                    System.out.println(i + ". " + task.toString());
                    if (i == tasks.size()) {
                        break;
                    }
                    i++;
                }
            }
        } catch (DukeException e) {
            System.out.println("☹ OOPS!!! Your list seems empty");
        }
        System.out.println(lineCutOff);
    }

    public static void setTasks(String line) {
        if(tasks.size() == 0)
            tasks.add(null);
        if (tasks.get(tasks.size() - 1) != null) {
            tasks.add(taskType(line));
        } else if (tasks.get(tasks.size() - 1) == null) {
            tasks.set(tasks.size() - 1, taskType(line));
        }
        if (tasks.get(tasks.size() - 1) != null) {
            printTask(tasks.get(tasks.size() - 1));
        }
    }


    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println(lineCutOff + "\nHello! I'm Duke\nWhat can I do for you?" + "\n" + lineCutOff);


        //tasks.add(null);
        readFromFile(FILE_PATH);
        Scanner in = new Scanner(System.in);
        String line = in.nextLine();

        while (!(line.matches("Bye") ||line.matches("bye"))) {
            if (line.startsWith("done")) {
                printDone(line);
                writeToFile(FILE_PATH);
            } else if (line.startsWith("delete")) {
                printDelete(line);
                writeToFile(FILE_PATH);
            } else if (!line.matches("list")) {
                setTasks(line);
                writeToFile(FILE_PATH);
            } else if (line.matches("list")) {
                printList(tasks);
            }
            line = in.nextLine();
        }
        System.out.println(lineCutOff + "\nBye. Hope to see you again soon!" + "\n" + lineCutOff);
    }
}