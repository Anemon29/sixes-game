package pl.edu.agh.sixes.command;

import java.util.Stack;

public class CommandRegistry {

    private Stack<Command> commandStack = new Stack<>();
    private Stack<Command> undoCommandStack = new Stack<>();

    public void executeCommand(Command command) {
        undoCommandStack.clear();
        command.execute();
        commandStack.push(command);
    }

    public void redo() {
        if (!undoCommandStack.isEmpty()) {
            Command command = undoCommandStack.pop();
            command.redo();
            commandStack.push(command);
        }
    }

    public void undo() {
        if (!commandStack.isEmpty()) {
            Command command = commandStack.pop();
            command.undo();
            undoCommandStack.push(command);
        }
    }

    public Stack<Command> getCommandStack() {
        return commandStack;
    }
}
