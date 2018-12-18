package pl.edu.agh.sixes.command;

import pl.edu.agh.sixes.model.Board;
import pl.edu.agh.sixes.model.Card;
import pl.edu.agh.sixes.model.CardContainer;

public class PutHighCardIntoTrashCommand implements Command {

    private final Board board;
    private final CardContainer first;

    public PutHighCardIntoTrashCommand(Board board, CardContainer first) {
        this.board = board;
        this.first = first;
    }

    @Override
    public void execute() {
        Card card;
        switch (first.getPlace()) {
            case DECK:
                card = board.getDeck().pop();
                break;
            case REJECTED:
                card = board.getRejectedCards().pop();
                break;
            case FIELD:
                if (!first.getContent().isPresent()) {
                    throw new IllegalStateException("Can't push empty fields for now.");
                }
                card = first.getContent().get();
                first.setContent(null);
                break;
            default:
                throw new IllegalArgumentException("Place must be: DECK or REJECTED or FIELD.");
        }
        board.getTrash().push(card);
    }

    @Override
    public void undo() {
        Card card = board.getTrash().pop();
        switch (first.getPlace()) {
            case DECK:
                board.getDeck().push(card);
                break;
            case REJECTED:
                board.getRejectedCards().push(card);
                break;
            case FIELD:
                first.setContent(card);
                break;
            default:
                throw new IllegalArgumentException("Place must be: DECK or REJECTED or FIELD.");
        }
    }

    @Override
    public void redo() {
        execute();
    }
}
