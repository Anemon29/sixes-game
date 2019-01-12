package pl.edu.agh.sixes.command;

import pl.edu.agh.sixes.engine.validation.chain.LowCardValidationChain;
import pl.edu.agh.sixes.model.Board;
import pl.edu.agh.sixes.model.Card;
import pl.edu.agh.sixes.model.CardContainer;

public class PutLowCardsIntoTrashCommand implements Command {

    private final Board board;
    private final CardContainer first;
    private final CardContainer second;
    private final LowCardValidationChain validationChain;

    public PutLowCardsIntoTrashCommand(Board board, CardContainer first, CardContainer second) {
        this.board = board;
        this.first = first;
        this.second = second;
        this.validationChain = new LowCardValidationChain();
    }

    @Override
    public void execute() {
        if (validationChain.validate(first, second)) {
            Card card1 = throwOutCard(first);
            Card card2 = throwOutCard(second);
            board.getTrash().push(card1);
            board.getTrash().push(card2);
            System.out.println("two same cards chosen");
        }
        else {
            throw new IllegalArgumentException("Cards aren't the same, can't remove them");
        }
    }

    private Card throwOutCard(CardContainer container) {
        Card card;
        switch (container.getPlace()) {
            case DECK:
                return board.getDeck().pop();
            case REJECTED:
                return board.getRejectedCards().pop();
            case FIELD:
                if (!container.getContent().isPresent()) {
                    throw new IllegalArgumentException("Can't push empty fields");
                }
                card = container.getContent().get();
                container.setContent(null);
                return card;
            default:
                throw new IllegalArgumentException("Place must be DECK, REJECTED or FIELD");
        }
    }

    @Override
    public void undo() {
        Card card2 = board.getTrash().pop();
        Card card1 = board.getTrash().pop();
        putCardBack(first, card1);
        putCardBack(second, card2);
    }

    private void putCardBack(CardContainer cardContainer, Card card){
        switch (cardContainer.getPlace()) {
            case DECK:
                board.getDeck().push(card);
                break;
            case REJECTED:
                board.getRejectedCards().push(card);
                break;
            case FIELD:
                cardContainer.setContent(card);
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
