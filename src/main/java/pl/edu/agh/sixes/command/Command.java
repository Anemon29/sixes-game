package pl.edu.agh.sixes.command;

public interface Command {

    void execute();

    void undo();

    void redo();
}
