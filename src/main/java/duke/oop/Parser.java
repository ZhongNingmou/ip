package duke.oop;

public class Parser {
    private static String command;
    private static final String BYE = "bye";
    private static final String LIST = "list";
    private static final String TODO = "todo";
    private static final String DEADLINE = "deadline";
    private static final String EVENT = "event";
    private static final String DELETE = "delete";
    private static final String DONE = "done";
    private static final String FIND = "find";

    /**
     * Constructor of parser
     * @param command get command from user
     */
    public Parser(String command){
        this.command = command;
    }

    /**
     * return different types of tasks according to the command
     * @param command get command details
     * @return type of task
     */
    public static String getCommand(String command) {
        if (command.equals(LIST)) {
            return LIST;
        } else if (command.equals(BYE)) {
            return BYE;
        } else if (command.startsWith(TODO)) {
            return TODO;
        } else if (command.startsWith(DEADLINE)) {
            return DEADLINE;
        } else if (command.startsWith(EVENT)) {
            return EVENT;
        } else if (command.startsWith(DELETE)) {
            return DELETE;
        } else if (command.startsWith(DONE)) {
            return DONE;
        } else if (command.startsWith(FIND)) {
            return FIND;
        } else
            return command;
    }
}