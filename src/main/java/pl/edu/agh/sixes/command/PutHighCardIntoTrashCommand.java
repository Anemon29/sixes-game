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
            throw new IllegalArgumentException("You should pick card on final position");
        else if (res.equals(HighCardValidationChain.Result.SECOND)){
            throw new IllegalArgumentException("You should pick card on its right position first");
        }
        else {
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
