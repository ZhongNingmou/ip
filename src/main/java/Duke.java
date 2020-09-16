import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.util.Scanner;

public class Duke {
    static String lineCutOff = "_______________________";
    static int TODO_TASK_INDEX = 5;
    static int DEADLINE_TASK_INDEX = 9;
    static int EVENT_TASK_INDEX = 6;

    public static Task taskType(String task) {
        try {
            if (task.startsWith("todo")) {
                if (task.equals("todo")) {
                    throw new DukeException();
                }
                return new ToDo(task.substring(TODO_TASK_INDEX));
            } else if (task.startsWith("deadline")) {
                if (task.equals("deadline")) {
                    throw new DukeException();
                }
                int indexBy = task.indexOf("/");
                return new Deadline(task.substring(DEADLINE_TASK_INDEX, indexBy - 1), task.substring(indexBy + 3));
            } else if (task.startsWith("event")) {
                if (task.equals("event")) {
                    throw new DukeException();
                }
                int indexAt = task.indexOf("/");
                return new Event(task.substring(EVENT_TASK_INDEX, indexAt - 1), task.substring(indexAt + 3));
            } else {
                System.out.println(lineCutOff);
                System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                System.out.println(lineCutOff);
            }
        } catch (DukeException e) {
            System.out.println(lineCutOff);
            System.out.println("☹ OOPS!!! The description of a " + task + " cannot be empty.");
            System.out.println(lineCutOff);
        }
        return null;
    }

    public static void printTask(Task task, int listNum) {
        System.out.println(lineCutOff + "\n" + "Got it. I've added this task: ");
        System.out.println("  " + task.toString());
        System.out.println("Now you have " + (listNum + 1) + " tasks in the list.");
        System.out.println(lineCutOff + "\n");
    }

    public static void printDone(Task task) {
        try {
            if (task == null) {
                throw new DukeException();
            }
            else {
                task.setIsDone(true);
                System.out.println(lineCutOff);
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(task.toString());
                System.out.println(lineCutOff);
            }
        } catch (DukeException e) {
            System.out.println(lineCutOff);
            System.out.println("☹ OOPS!!! There is no such task.");
            System.out.println(lineCutOff);
        }
    }

    public static void printList(Task[] tasks, int listNum) {
        System.out.println(lineCutOff);
        try {
            if (tasks[0] == null) {
                throw new DukeException();

            } else {
                System.out.println("Here are the tasks in your list:");
                int i = 0;
                for (Task task: tasks) {
                    System.out.println(i + 1 + ". " + task.toString());
                    if (i + 1 == listNum) {
                        break;
                    }
                }
            }
        } catch (DukeException e) {
            System.out.println("☹ OOPS!!! Your list seems empty");
        }
        System.out.println(lineCutOff);
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println(lineCutOff + "\nHello! I'm Duke\nWhat can I do for you?" + "\n" + lineCutOff);

        Task[] tasks = new Task[100];
        int listNum = 0;

        Scanner in = new Scanner(System.in);
        String line = new String(in.nextLine());


        while (!(line.matches("Bye") ||line.matches("bye"))) {
            if (line.startsWith("done")) {
                int listIndex = Integer.parseInt(line.substring(5)) - 1;
                //tasks[listIndex].setIsDone(true);
                printDone(tasks[listIndex]);
            } else if (!line.matches("list")) {
                tasks[listNum] = taskType(line);
                if (tasks[listNum] != null) {
                    printTask(tasks[listNum], listNum);
                    listNum++;
                }
            } else if (line.matches("list")) {
                printList(tasks,listNum);
            }

            line = new String(in.nextLine());
        }
        System.out.println(lineCutOff + "\nBye. Hope to see you again soon!" + "\n" + lineCutOff);
    }
}