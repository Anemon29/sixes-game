package pl.edu.agh.sixes.command;

import pl.edu.agh.sixes.model.Board;
import pl.edu.agh.sixes.model.Card;
import pl.edu.agh.sixes.model.CardContainer;

public class PutHighCardIntoTrashCommand implements Command {

    private final Board board;
    private CardContainer second;

    public PutHighCardIntoTrashCommand(Board board, CardContainer second) {
        this.board = board;
        this.second = second;
    }

    @Override
    public void execute() {
        Card card;
        switch (second.getPlace()) {
            case DECK:
                card = board.getDeck().pop();
                break;
            case REJECTED:
                card = board.getRejectedCards().pop();
                break;
            case FIELD:
                if (!second.getContent().isPresent()) {
                    throw new IllegalStateException("Can't push empty fields for now.");
                }
                card = second.getContent().get();
                second.setContent(null);
                break;
            default:
                throw new IllegalArgumentException("Place must be: DECK or REJECTED or FIELD.");
        }
        board.getTrash().push(card);
    }

    @Override
    public void undo() {
        Card card = board.getTrash().pop();
        switch (second.getPlace()) {
            case DECK:
                board.getDeck().push(card);
                break;
            case REJECTED:
                board.getRejectedCards().push(card);
                break;
            case FIELD:
                second.setContent(card);
                break;
            default:
                throw new IllegalArgumentException("Cannot undo this move");
        }
    }

    @Override
    public void redo() {
        execute();
    }
}
