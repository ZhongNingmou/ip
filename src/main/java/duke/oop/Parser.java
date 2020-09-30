package duke.oop;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {
    static String lineCutOff = "_______________________";
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
     * A constructor to initialise a parser with command.
     *
     * @param command command of the parser
     */
    public Parser(String command){
        this.command = command;
    }

    /**
     * Parse command to get the behavior.
     *
     * @param command user command
     * @return corresponding type of command
     */
    public static String getBehavior(String command) {
        String[] infoEntered = command.split(" ");
        try {
            if (command.equals(LIST)) {
                return LIST;
            } else if (command.equals(BYE)) {
                return BYE;
            } else if (command.startsWith(TODO)) {
                if (infoEntered.length == 1) {
                    throw new DukeException();
                }
                return TODO;
            } else if (command.startsWith(DEADLINE)){
                if (infoEntered.length == 1 || !command.contains("/by")) {
                    throw new DukeException();
                }
                return DEADLINE;
            } else if (command.startsWith(EVENT)){
                if (infoEntered.length == 1 || !command.contains("/at")) {
                    throw new DukeException();
                }
                return EVENT;
            } else if (command.startsWith(DELETE)){
                if (infoEntered.length == 1) {
                    throw new DukeException();
                }
                return DELETE;
            } else if (command.startsWith(DONE)){
                if (infoEntered.length == 1) {
                    throw new DukeException();
                }
                return DONE;
            } else if (command.startsWith(FIND)){
                if (infoEntered.length == 1) {
                    throw new DukeException();
                }
                return FIND;
            } else {
                System.out.println(lineCutOff);
                System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(\n");
                System.out.println(lineCutOff);
                return null;
            }
        } catch (DukeException e){
            System.out.println(lineCutOff);
            System.out.println("OOPS!!! The description cannot be empty or invalid.\n");
            System.out.println(lineCutOff);
            return null;
        }
    }

    /**
     * Set the user input date from a string to a meaningful date.
     *
     * @param date string from user input
     * @return string of a meaningful date if the input is in "MMM d yyyy" format,
     * or the original input otherwise
     */
    /*public static String setDateFormat(String date){
        LocalDate d;
        String dateFormatted;
        try {
            d = LocalDate.parse(date);
            dateFormatted = d.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        } catch (DateTimeParseException e){
            dateFormatted = date;
        }
        return dateFormatted;
    }*/
}