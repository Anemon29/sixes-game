package pl.edu.agh.sixes.command;

import pl.edu.agh.sixes.model.Board;
import pl.edu.agh.sixes.model.Card;
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
        if (second.getContent().isPresent()) {
            throw new IllegalStateException("Critical error. TakeFromRejected to nonempty place: " + second);
        }
        Card card = board.getRejectedCards().pop();
        second.setContent(card);
    }

    @Override
    public void undo() {
        if (!second.getContent().isPresent()) {
            throw new IllegalStateException("Critical error. Undo TakeFromRejected from nonempty place: " + second);
        }
        Card card = second.getContent().get();
        board.getRejectedCards().push(card);
    }

    @Override
    public void redo() {
        execute();
    }
}
