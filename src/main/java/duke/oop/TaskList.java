package duke.oop;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class TaskList {
    private static ArrayList<Task> tasks;
    static String lineCutOff = "_______________________";
    static int TODO_TASK_INDEX = 5;
    static int DEADLINE_TASK_INDEX = 9;
    static int EVENT_TASK_INDEX = 6;
    static int DONE_TASK_INDEX = 5;
    static int DELETE_TASK_INDEX = 7;
    static int FIND_TASK_INDEX = 4;

    public TaskList(){
        tasks = new ArrayList<>();
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
                if (tasks.size() == 0) {
                    System.out.println("Now you have 0 item in the list.");
                } else if (tasks.get(tasks.size()-1) == null) {
                    int size = tasks.size()-1;
                    System.out.println("Now you have " + size + " items in the list.");
                } else {
                    System.out.println("Now you have " + tasks.size() + " items in the list.");
                }
                System.out.println(lineCutOff);
            }
        } catch (DukeException e) {
            System.out.println(lineCutOff);
            System.out.println("☹ OOPS!!! There is no such task.");
            System.out.println(lineCutOff);
        }
    }

    public static void printList() {
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
                    if (task == null) {
                        break;
                    }
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

    public static void printFind(String line){
        String description = line.substring(FIND_TASK_INDEX + 1);
        if (tasks.size() != 0) {
            if (tasks.get(tasks.size() - 1) == null)
                tasks.remove(tasks.size() - 1);
        }
        ArrayList<Task> findTasks = (ArrayList<Task>) tasks.stream()
                .filter(t -> t.getDescription().contains(description))
                .collect(Collectors.toList());
        System.out.println(lineCutOff);
        System.out.println("Here are the matching tasks in your list:");
        int index = 1;
        for (Task task : findTasks) {
            System.out.println(index + "." + task);
            index++;
        }
        System.out.println(lineCutOff);
    }

    public ArrayList<Task> getTasksList() {
        return tasks;
    }

    public void add(Task task) {
        tasks.add(task);
    }
}
