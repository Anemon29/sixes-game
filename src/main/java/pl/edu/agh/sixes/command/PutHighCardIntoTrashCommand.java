package pl.edu.agh.sixes.command;

import pl.edu.agh.sixes.engine.validation.chain.HighCardValidationChain;
import pl.edu.agh.sixes.model.Board;
import pl.edu.agh.sixes.model.Card;
import pl.edu.agh.sixes.model.CardContainer;

public class PutHighCardIntoTrashCommand implements Command {

    private final Board board;
    private  CardContainer first;
    private  CardContainer second;
    private final HighCardValidationChain validationChain;

    public PutHighCardIntoTrashCommand(Board board, CardContainer first, CardContainer second) {
        this.board = board;
        this.first = first;
        this.second = second;
        this.validationChain = new HighCardValidationChain();
    }

    @Override
    public void execute() {
        HighCardValidationChain.Result res = validationChain.validate(board, first, second);
        if (res.equals(HighCardValidationChain.Result.NONE))
            return;
        if (res.equals(HighCardValidationChain.Result.FIRST)){
            CardContainer temp = first;
            first = second;
            second = temp;
        }
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
