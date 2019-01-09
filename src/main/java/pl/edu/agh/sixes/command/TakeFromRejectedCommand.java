package pl.edu.agh.sixes.command;

import pl.edu.agh.sixes.model.Board;
import pl.edu.agh.sixes.model.CardContainer;

public class TakeFromRejectedCommand implements Command {

    private final Board board;
    private final CardContainer first;
    private final CardContainer second;

    public TakeFromRejectedCommand(Board board, CardContainer first, CardContainer second) {
        this.board = board;
        this.first = first;
        this.second = second;
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
