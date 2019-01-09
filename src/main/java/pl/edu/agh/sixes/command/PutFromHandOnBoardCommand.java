package pl.edu.agh.sixes.command;

import pl.edu.agh.sixes.model.Board;
import pl.edu.agh.sixes.model.Card;
import pl.edu.agh.sixes.model.CardContainer;

public class PutFromHandOnBoardCommand implements Command {

    private final Board board;
    private final CardContainer first;
    private final CardContainer second;

    public PutFromHandOnBoardCommand(Board board, CardContainer first, CardContainer second) {
        this.board = board;
        this.first = first;
        this.second = second;
    }

    @Override
    public void execute() {
        if (second.getContent().isPresent()) {
            throw new IllegalStateException("Critical error. PutFromHandOnBoard to nonempty place: " + second);
        }
        Card card = board.getDeck().pop();
        second.setContent(card);
    }

    @Override
    public void undo() {
        if (!second.getContent().isPresent()) {
            throw new IllegalStateException("Critical error. Undo PutFromHandOnBoard from nonempty place: " + second);
        }
        Card card = second.getContent().get();
        board.getDeck().push(card);
    }

    @Override
    public void redo() {
        execute();
    }
}
