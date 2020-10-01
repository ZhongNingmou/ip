package duke.oop;

import java.util.Scanner;

public class Ui {
    static String lineCutOff = "_______________________";

    public static void printWelcomeMessage() {
        System.out.println(lineCutOff + "\nHello! I'm Duke\nWhat can I do for you?" + "\n" + lineCutOff);

    }

    public void dealWithInput(TaskList tasks){
        Scanner in = new Scanner(System.in);
        String line = in.nextLine();
        String head = Parser.getCommand(line);
        while (!(head.toLowerCase().matches("bye") )) {
            if (head.matches("done")) {
                TaskList.printDone(line);
                Storage.writeToFile(tasks);
            } else if (head.matches("find")) {
                TaskList.printFind(line);
                Storage.writeToFile(tasks);
            } else if (head.matches("delete")) {
                TaskList.printDelete(line);
                Storage.writeToFile(tasks);
            } else if (!head.matches("list")) {
                TaskList.setTasks(line);
                Storage.writeToFile(tasks);
            } else if (head.matches("list")) {
                TaskList.printList();
            }
            line = in.nextLine();
            head = Parser.getCommand(line);
        }
        System.out.println(lineCutOff + "\nBye. Hope to see you again soon!" + "\n" + lineCutOff);
    }
}
