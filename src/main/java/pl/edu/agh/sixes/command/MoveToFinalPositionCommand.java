package pl.edu.agh.sixes.command;

import pl.edu.agh.sixes.model.Board;
import pl.edu.agh.sixes.model.CardContainer;

public class MoveToFinalPositionCommand implements Command {

    private final Board board;
    private final CardContainer first;
    private final CardContainer second;

    public MoveToFinalPositionCommand(Board board, CardContainer first, CardContainer second) {
        this.board = board;
        this.first = first;
        this.second = second;
    }

    @Override
    public void execute() {
        if (second.getContent().isPresent()) {
            throw new IllegalStateException("Final position is not empty.");
        }
        if (first.getContent().isPresent()) {
            second.setContent(first.getContent().get());
        } else {
            throw new IllegalStateException("Move empty container to final position.");
        }
    }

    @Override
    public void undo() {
        if (first.getContent().isPresent()) {
            throw new IllegalStateException("Critical error. Undo final position not empty.");
        }
        if (second.getContent().isPresent()) {
            first.setContent(second.getContent().get());
        } else {
            throw new IllegalStateException("Move empty container to final position.");
        }
    }

    @Override
    public void redo() {
        execute();
    }

}
