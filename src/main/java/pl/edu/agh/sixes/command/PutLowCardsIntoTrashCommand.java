package pl.edu.agh.sixes.command;

import pl.edu.agh.sixes.model.Board;
import pl.edu.agh.sixes.model.CardContainer;

public class PutLowCardsIntoTrashCommand implements Command {

    private final Board board;
    private final CardContainer first;
    private final CardContainer second;

    public PutLowCardsIntoTrashCommand(Board board, CardContainer first, CardContainer second) {
        this.board = board;
        this.first = first;
        this.second = second;
    }

    @Override
    public void execute() {
        System.out.println("two cards chosen");
    }

    @Override
    public void undo() {

    }

    @Override
    public void redo() {

    }
}
