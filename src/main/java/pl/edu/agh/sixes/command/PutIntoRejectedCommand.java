package pl.edu.agh.sixes.command;

import pl.edu.agh.sixes.model.Board;
import pl.edu.agh.sixes.model.CardContainer;

public class PutIntoRejectedCommand implements Command {
    private final Board board;
    private final CardContainer first;

    public PutIntoRejectedCommand(Board board, CardContainer first) {
        this.board = board;
        this.first = first;
    }

    @Override
    public void execute() {

    }

    @Override
    public void undo() {

    }

    @Override
    public void redo() {

    }
}
