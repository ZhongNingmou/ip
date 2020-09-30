package duke.oop;
import duke.task.Task;

import java.util.Scanner;

public class Ui {
    static String lineCutOff = "_______________________";
    public static final String FILE_PATH = "duke.txt";

    public static void printWelcomeMessage() {
        System.out.println(lineCutOff + "\nHello! I'm Duke\nWhat can I do for you?" + "\n" + lineCutOff);

    }

    /**
     * Say goodbye to users before exits when the program ends.
     */
    /*public static void printByeMessage() {
        System.out.println(lineCutOff + "\nBye. Hope to see you again soon!" + "\n" + lineCutOff);
    }*/

    public void dealWithInput(TaskList tasks){
        Scanner in = new Scanner(System.in);
        String line = in.nextLine();

        while (!(line.toLowerCase().matches("bye") )) {
            if (line.startsWith("done")) {
                TaskList.printDone(line);
                Storage.writeToFile(tasks);
            } else if (line.startsWith("delete")) {
                TaskList.printDelete(line);
                Storage.writeToFile(tasks);
            } else if (!line.matches("list")) {
                TaskList.setTasks(line);
                Storage.writeToFile(tasks);
            } else if (line.matches("list")) {
                TaskList.printList();
            }
            line = in.nextLine();
        }
        System.out.println(lineCutOff + "\nBye. Hope to see you again soon!" + "\n" + lineCutOff);
    }
}
