import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static ArrayList<Task> tasks = new ArrayList<>();
    static String lineCutOff = "_______________________";
    static int TODO_TASK_INDEX = 5;
    static int DEADLINE_TASK_INDEX = 9;
    static int EVENT_TASK_INDEX = 6;
    static int DONE_TASK_INDEX = 5;
    static int DELETE_TASK_INDEX = 7;

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

    public static void printTask(Task task, int listNum) {
        System.out.println(lineCutOff + "\n" + "Got it. I've added this task: ");
        System.out.println("  " + task.toString());
        System.out.println("Now you have " + (listNum + 1) + " tasks in the list.");
        System.out.println(lineCutOff + "\n");
    }

    public static void printDone(String task) {
        try {
            if (task.equals("done")) {
                throw new DukeException();
            }
            int listIndex = Integer.parseInt(task.substring(DONE_TASK_INDEX)) - 1;
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

    public static void printDelete(String task) {
        try {
            if (task.equals("delete")) {
                throw new DukeException();
            }
            int listIndex = Integer.parseInt(task.substring(DELETE_TASK_INDEX)) - 1;
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

    public static int setTasks(int listNum, String task) {
        if (listNum >= tasks.size()) {
            tasks.add(taskType(task));
        } else if(tasks.get(listNum) == null) {
            tasks.set(listNum,taskType(task));
        }
        if (tasks.get(listNum) != null) {
            printTask(tasks.get(listNum), listNum);
            listNum++;
        }
        return listNum;
    }


    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println(lineCutOff + "\nHello! I'm Duke\nWhat can I do for you?" + "\n" + lineCutOff);

        int listNum = 0;

        tasks.add(null);
        Scanner in = new Scanner(System.in);
        String task = in.nextLine();


        while (!(task.matches("Bye") ||task.matches("bye"))) {
            if (task.startsWith("done")) {
                printDone(task);
            } else if (task.startsWith("delete")) {
                printDelete(task);
            } else if (!task.matches("list")) {
                listNum = setTasks(listNum,task);
            } else if (task.matches("list")) {
                printList(tasks);
            }
            task = in.nextLine();
        }
        System.out.println(lineCutOff + "\nBye. Hope to see you again soon!" + "\n" + lineCutOff);
    }
}