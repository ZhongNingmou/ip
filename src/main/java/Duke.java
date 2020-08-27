import java.util.Scanner;
import java.util.Arrays;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        String lineCutOff = "_______________________";
        System.out.println(lineCutOff + "\nHello! I'm Duke\nWhat can I do for you?" + "\n" + lineCutOff);

        //String[] Task = new String[100];
        Task[] tasks = new Task[100];
        int tasksCount = 0;
        int listFlag = 0;
        int listNum = 1; //to print out the 1.

        Scanner in = new Scanner(System.in);
        Task line = new Task(in.nextLine());


        while(!(line.description.matches("Bye") ||line.description.matches("bye"))) {
            if (!line.description.matches("list")) {
                System.out.println(lineCutOff + "\n" + "added: " + line.description + "\n" + lineCutOff);
                tasks[listFlag] = line;
                listFlag++;
            }

            if (line.description.matches("list")) {
                System.out.println(lineCutOff);
                System.out.println("Here are the tasks in your list:");
                for (listNum = 0; tasks[tasksCount].description != null; ) {
                    System.out.println(listNum + ". [" + tasks[tasksCount].getStatusIcon() +"]" + tasks[tasksCount].description);
                    if (listNum == listFlag) {
                        break;
                    }
                    listNum++;
                }
                System.out.println(lineCutOff);
            }

            if (line.description.startsWith("done")) {
                int listIndex = Integer.parseInt(line.description.substring(5)) - 1;
                tasks[listIndex].setIsDone(true);
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("  [" + tasks[listIndex].getStatusIcon() + "] " + tasks[listIndex].description);
                System.out.println(lineCutOff);
            }

            line = new Task(in.nextLine());
        }
        System.out.println(lineCutOff + "\nBye. Hope to see you again soon!" + "\n" + lineCutOff);
    }
}
