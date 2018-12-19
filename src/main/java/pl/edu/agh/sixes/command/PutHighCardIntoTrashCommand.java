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
            case Deck:
                card = board.getDeck().pop();
                break;
            case Rejected:
                card = board.getRejectedCards().pop();
                break;
            case Field:
                if (!first.getContent().isPresent()) {
                    throw new IllegalStateException("Can't push empty fields for now.");
                }
                card = first.getContent().get();
                first.setContent(null);
                break;
            default:
                throw new IllegalArgumentException("Place must be: Deck or Rejected or Field.");
        }
        board.getTrash().push(card);
    }

    @Override
    public void undo() {
        Card card = board.getTrash().pop();
        switch (first.getPlace()) {
            case Deck:
                board.getDeck().push(card);
                break;
            case Rejected:
                board.getRejectedCards().push(card);
                break;
            case Field:
                first.setContent(card);
                break;
            default:
                throw new IllegalArgumentException("Place must be: Deck or Rejected or Field.");
        }
    }

    @Override
    public void redo() {
        execute();
    }
}
