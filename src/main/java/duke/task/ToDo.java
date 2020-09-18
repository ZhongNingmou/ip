package duke.task;

public class ToDo extends Task{
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String writeToFile(){
        return "T|" + val + "|" + this.description + "\n";
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
