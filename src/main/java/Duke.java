import java.util.Scanner;

public class Duke {
    static String lineCutOff = "_______________________";
    static int TODO_TASK_INDEX = 5;
    static int DEADLINE_TASK_INDEX = 9;
    static int EVENT_TASK_INDEX = 6;

    public static Task taskType(String task) {
        if (task.startsWith("todo")){
            return new ToDo(task.substring(TODO_TASK_INDEX ));
        }else if (task.startsWith("deadline")){
            int indexBy = task.indexOf("/");
            return new Deadline(task.substring(DEADLINE_TASK_INDEX,indexBy - 1), task.substring(indexBy + 3));
        }else if (task.startsWith("event")){
            int indexAt = task.indexOf("/");
            return new Event(task.substring(EVENT_TASK_INDEX, indexAt - 1), task.substring(indexAt + 3));
        }
        return null;
    }

    public static void printTask(Task task, int listNum) {
        System.out.println(lineCutOff + "\n" + "Got it. I've added this task: ");
        System.out.println("  " + task.toString());
        System.out.println("Now you have " + (listNum + 1) + " tasks in the list.");
        System.out.println(lineCutOff + "\n");
    }


    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        //String lineCutOff = "_______________________";
        System.out.println(lineCutOff + "\nHello! I'm Duke\nWhat can I do for you?" + "\n" + lineCutOff);

        //String[] Task = new String[100];
        Task[] tasks = new Task[100];
        int tasksCount;
        int listFlag = 1;//to print out the 1.
        int listNum = 0;

        Scanner in = new Scanner(System.in);
        Task line = new Task(in.nextLine());


        while(!(line.description.matches("Bye") ||line.description.matches("bye"))) {
            if (line.description.startsWith("done")) {
                int listIndex = Integer.parseInt(line.description.substring(5)) - 1;
                tasks[listIndex].setIsDone(true);
                System.out.println(lineCutOff);
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("  [" + tasks[listIndex].getStatusIcon() + "] " + tasks[listIndex].description);
                System.out.println(lineCutOff);
            } else if (!line.description.matches("list")) {
                tasks[listNum] = taskType(line.description);
                assert tasks[listNum] != null;
                printTask(tasks[listNum],listNum);
                listNum++;
            } else if (line.description.matches("list")) {
                System.out.println(lineCutOff);
                System.out.println("Here are the tasks in your list:");
                for (tasksCount = 0; tasks[tasksCount].description != null; ) {
                    System.out.println(listFlag + ". " + tasks[tasksCount].toString());
                    if (listFlag == listNum) {
                        listFlag = 1;
                        break;
                    }
                    listFlag++;
                    tasksCount++;
                }
                System.out.println(lineCutOff);
            }

            line = new Task(in.nextLine());
        }
        System.out.println(lineCutOff + "\nBye. Hope to see you again soon!" + "\n" + lineCutOff);
    }
}