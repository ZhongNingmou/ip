import java.util.Scanner;

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

        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();
        while(!(line.matches("Bye") ||line.matches("bye") ) ){
            System.out.println(lineCutOff + "\n" + line + "\n" + lineCutOff);
            line = in.nextLine();
        }
        System.out.println(lineCutOff + "\n" + line);
        System.out.println(lineCutOff + "\nBye. Hope to see you again soon!" + "\n" + lineCutOff);
    }
}

