package duke.task;

public class Task {
    protected String description;
    protected boolean isDone;
    int val = 0;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public String getDescription(){ return description; }

    public void setIsDone(boolean isDone) {
        this.isDone = isDone;
        val = 1;
    }

    public boolean isDone() { return isDone; }

    public String writeToFile(){
        return "T|" + val + "|" + this.description + "\n";
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "]" + this.description;
    }
}